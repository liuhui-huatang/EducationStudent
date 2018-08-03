package com.htcompany.education.studentforgansu.recruitstudent.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;

/**
 *
 * Created by wrb on 2017/2/6.
 */
public class PleseYQMDailog extends Dialog{
    private Context context;
    private EditText yqmedt;
    private TextView sure_btn;
    private Handler handler;
    public PleseYQMDailog(Context context) {
        super(context);
        this.context = context;
    }

    public PleseYQMDailog(Context context, int theme,Handler handler) {
        super(context, theme);
        this.context = context;
        this.handler = handler;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plese_yqm_dailog);
        initViews();
    }
    public void initViews(){
        yqmedt = (EditText)this.findViewById(R.id.plese_yqm_edt);
        sure_btn=(TextView)this.findViewById(R.id.plese_sure_tv);
        sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!"".equals(yqmedt.getText().toString().trim())){
                    Message message = new Message();
                    message.what=1000;
                    message.obj = yqmedt.getText().toString().trim();
                    handler.sendMessage(message);
                }else{
                    ToastUtil.showToast("请输入邀请码",context);
                }
            }
        });
    }
}
