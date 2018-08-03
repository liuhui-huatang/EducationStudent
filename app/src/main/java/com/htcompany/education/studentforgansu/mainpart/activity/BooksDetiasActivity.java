package com.htcompany.education.studentforgansu.mainpart.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.mainpart.entity.BookEntity;

/**
 * 图书详情
 * Created by wrb on 2016/11/15.
 */
public class BooksDetiasActivity extends BaseActivity implements View.OnClickListener{
    private TextView title, right_btn_tv;
    private RelativeLayout reback_btn, right_text_btn;
    private ImageView bookdetails_photo_img;
    private TextView bookdetails_name_tv,bookdetails_auther_tv,bookdetails_type_tv,
            bookdetails_postion_tv,bookdetails_code_tv,bookdetails_gjcode_tv,bookdetails_cbs_tv,bookdetails_price_tv,
            bookdetails_allcount_tv,bookdetails_kccount_tv,bookdetails_isgc_tv,bookdetails_remark_tv;
    private BookEntity bookEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booksdetias_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
     public void initDatas(){
         bookEntity = (BookEntity) getIntent().getSerializableExtra("bookentity");
     }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        right_btn_tv = (TextView) this.findViewById(R.id.right_btn_tv);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_text_btn = (RelativeLayout) this.findViewById(R.id.right_text_btn);
        bookdetails_photo_img=(ImageView)this.findViewById(R.id.bookdetails_photo_img);
        bookdetails_name_tv= (TextView)this.findViewById(R.id.bookdetails_name_tv);
        bookdetails_auther_tv= (TextView)this.findViewById(R.id.bookdetails_auther_tv);
        bookdetails_type_tv= (TextView)this.findViewById(R.id.bookdetails_type_tv);
        bookdetails_postion_tv= (TextView)this.findViewById(R.id.bookdetails_postion_tv);
        bookdetails_code_tv= (TextView)this.findViewById(R.id.bookdetails_code_tv);
        bookdetails_gjcode_tv= (TextView)this.findViewById(R.id.bookdetails_gjcode_tv);
        bookdetails_cbs_tv= (TextView)this.findViewById(R.id.bookdetails_cbs_tv);
        bookdetails_price_tv= (TextView)this.findViewById(R.id.bookdetails_price_tv);
        bookdetails_allcount_tv= (TextView)this.findViewById(R.id.bookdetails_allcount_tv);
        bookdetails_kccount_tv= (TextView)this.findViewById(R.id.bookdetails_kccount_tv);
        bookdetails_isgc_tv= (TextView)this.findViewById(R.id.bookdetails_isgc_tv);
        bookdetails_remark_tv= (TextView)this.findViewById(R.id.bookdetails_remark_tv);
    }
    public void initViewValues(){
        title.setText("图书详情");
        if(bookEntity!=null){
            bookdetails_name_tv.setText(bookEntity.getName());
            bookdetails_auther_tv.setText(bookEntity.getZuozhe());
            bookdetails_type_tv.setText("类别:"+bookEntity.getBooktype_name());
            bookdetails_postion_tv.setText("书架:"+bookEntity.getShelf_name());
            bookdetails_code_tv.setText(bookEntity.getCode());
            bookdetails_gjcode_tv.setText(bookEntity.getCode());
            bookdetails_cbs_tv.setText(bookEntity.getChubanshe());
            bookdetails_price_tv.setText(bookEntity.getPrice()+"元");
            bookdetails_allcount_tv.setText(bookEntity.getHejishuliang()+"本");
            bookdetails_kccount_tv.setText(bookEntity.getKucun()+"本");
            bookdetails_isgc_tv.setText(bookEntity.getIsguancang_name());
            bookdetails_remark_tv.setText(bookEntity.getNote());
        }
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }
}
