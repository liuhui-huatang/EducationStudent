package com.htcompany.education.studentforgansu.recruitstudent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.recruitstudent.adapter.ZsItemSelectorAdapter;
import com.htcompany.education.studentforgansu.recruitstudent.entity.ZSSHEntity;

import java.util.List;

/**
 * 招生选项
 * Created by wrb on 2017/1/22.
 */
public class ZsItemSelectorActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout rebakc_btn;
    private ListView zsitem_lv;
    private ZsItemSelectorAdapter selectorAdapter;
    private List<ZSSHEntity> datas;
    private String flag="";
    private String strtitle="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zsttemselector_activity);
        initDatas();
        initViews();
    }
    public void initDatas(){
        datas = (List<ZSSHEntity>) getIntent().getSerializableExtra("datas");
        flag = getIntent().getStringExtra("flag");
        strtitle=getIntent().getStringExtra("title");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        rebakc_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        zsitem_lv = (ListView)this.findViewById(R.id.zsitem_lv);
        if(datas!=null&&datas.size()>0){
            selectorAdapter = new ZsItemSelectorAdapter(this,datas);
            zsitem_lv.setAdapter(selectorAdapter);
        }
        zsitem_lv.setOnItemClickListener(itemClickListener);
        title.setText(strtitle);
        rebakc_btn.setOnClickListener(this);
    }
    public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent();
            intent.putExtra("flag",flag);
            intent.putExtra("entity",(ZSSHEntity)selectorAdapter.getItem(position));
            setResult(100,intent);
            finish();
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }
}
