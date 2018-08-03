package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.PullMoreListView;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.commonpart.views.xlistview.XListView;
import com.htcompany.education.studentforgansu.internet.schoolbook.ShoolBooksInternet;
import com.htcompany.education.studentforgansu.internet.schoolbook.ShoolBooksPersener;
import com.htcompany.education.studentforgansu.mainpart.adapter.BooksReserveAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.BookEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.BookReserveEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.BooksTypeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 图书借阅
 * Created by wrb on 2016/11/12.
 */
public class SchoolBookReserveActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title, right_btn_tv;
    private RelativeLayout reback_btn, right_text_btn;
    private XListView booksmanage_lv;
    private BooksReserveAdapter manageAdapter;
    private EditText booksmanage_search_edt;
    private List<BookReserveEntity> bookEntities;
    Intent intent = null;
    //网络请求类
    private ShoolBooksInternet booksInternet;
    private ShoolBooksPersener booksPersener;
    private WaitDialog waitDialog=null;
    private int pageNum=1;
    private String bookName="";
    private String typeId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schoolbooks_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        bookEntities = new ArrayList<BookReserveEntity>();
        booksInternet = new ShoolBooksInternet(this,myHandler);
        booksPersener=new ShoolBooksPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        booksInternet.getBooks_MyReadsDatas(String.valueOf(pageNum),bookName,typeId);
    }
    public void initViews() {
        title = (TextView) this.findViewById(R.id.title);
        right_btn_tv = (TextView) this.findViewById(R.id.right_btn_tv);
        reback_btn = (RelativeLayout) this.findViewById(R.id.reback_btn);
        right_text_btn = (RelativeLayout) this.findViewById(R.id.right_text_btn);
        booksmanage_search_edt = (EditText)this.findViewById(R.id.booksmanage_search_edt);
        booksmanage_lv = (XListView) this.findViewById(R.id.booksmanage_lv);
        booksmanage_lv.setPullRefreshEnable(true);
        booksmanage_lv.setPullLoadEnable(false);
        booksmanage_lv.setXListViewListener(this);
        manageAdapter = new BooksReserveAdapter(this,bookEntities);
        booksmanage_lv.setAdapter(manageAdapter);
        booksmanage_lv.setOnItemClickListener(bookManageClicer);
    }

    public void initViewValues() {
        title.setText("借阅记录");
        right_text_btn.setVisibility(View.VISIBLE);
        right_btn_tv.setText("分类");
    }

    public void initOnclicEvents() {
        reback_btn.setOnClickListener(this);
        right_text_btn.setOnClickListener(this);
        booksmanage_lv.setOnItemClickListener(bookManageClicer);
        booksmanage_search_edt.addTextChangedListener(searchClicer);
    }
    public AdapterView.OnItemClickListener bookManageClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //调到图书详情界面
            BookReserveEntity entity = (BookReserveEntity) manageAdapter.getItem(position-1);
            intent = new Intent(SchoolBookReserveActivity.this, SchoolBookReserveDetailsActivity.class);
            intent.putExtra("bookentity",entity);
            startActivity(intent);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_text_btn:
                //调到图书分类界面
                intent = new Intent(SchoolBookReserveActivity.this, BooksTypesActivity.class);
                startActivityForResult(intent,100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 100:
                    BooksTypeEntity typeEntity = (BooksTypeEntity) data.getSerializableExtra("typeEntity");
                    if(typeEntity!=null){
                        typeId = typeEntity.getId();
                        waitDialog.show();
                        pageNum=1;
                        booksInternet.getBooks_MyReadsDatas(String.valueOf(pageNum),bookName,typeId);
                    }
                    break;
            }
        }
    }

    public TextWatcher searchClicer = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            pageNum=1;
            bookName =booksmanage_search_edt.getText().toString().trim();
            booksInternet.getBooks_MyReadsDatas(String.valueOf(pageNum),bookName,typeId);
        }
    };

    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",SchoolBookReserveActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<BookReserveEntity> datas = booksPersener.parseSchoolbooksReceverData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterValues(datas);
                    }else{
                        stopListView();
                        booksmanage_lv.setPullLoadEnable(false);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",SchoolBookReserveActivity.this);
                    break;
            }
        }
    };
    public void setAdapterValues(List<BookReserveEntity> datas){
        if(pageNum==1) {
            if (bookEntities.size() > 0) {
                bookEntities.clear();
            }
        }
        if(datas.size()>=20){
            booksmanage_lv.setPullLoadEnable(true);
        }else{
            booksmanage_lv.setPullLoadEnable(false);
        }
        for(BookReserveEntity entity:datas){
            bookEntities.add(entity);
        }
        manageAdapter.notifyDataSetChanged();
        stopListView();
    }
    @Override
    public void onRefresh() {
        pageNum=1;
        booksInternet.getBooks_MyReadsDatas(String.valueOf(pageNum),bookName,typeId);
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        booksInternet.getBooks_MyReadsDatas(String.valueOf(pageNum),bookName,typeId);

    }
    /**
     * 停止列表刷新
     */
    public void stopListView() {
        booksmanage_lv.stopRefresh();
        booksmanage_lv.stopLoadMore();
        booksmanage_lv.setRefreshTime("刚刚");
    }
}
