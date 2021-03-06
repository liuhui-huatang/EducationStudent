package com.htcompany.education.studentforgansu.mainpart.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.views.photoviews.Bimp;
import com.htcompany.education.studentforgansu.commonpart.views.photoviews.FileUtils;
import com.htcompany.education.studentforgansu.commonpart.views.photoviews.GetPhotoPopwindow;
import com.htcompany.education.studentforgansu.commonpart.views.photoviews.ImageItem;

/**
 * 添加评论界面
 * Created by wrb on 2016/11/24.
 */
public class ShoolsForumAddCommentActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_text_btn;
    private LinearLayout forumaddcommentbottom_ll,shoolforumaddcomment_activity;//拍照
    private GetPhotoPopwindow photoPopwindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoolsforumaddcomment_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }

    public void initDatas(){

    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        right_btn_tv= (TextView)this.findViewById(R.id.right_btn_tv);
        reback_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
        right_text_btn=(RelativeLayout)this.findViewById(R.id.right_text_btn);
        forumaddcommentbottom_ll = (LinearLayout)this.findViewById(R.id.forumaddcommentbottom_ll);
        shoolforumaddcomment_activity= (LinearLayout)this.findViewById(R.id.shoolforumaddcomment_activity);
    }
    public void initViewValues(){
        title.setText("评论");
        right_text_btn.setVisibility(View.VISIBLE);
        right_btn_tv.setText("发布");
    }

    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        forumaddcommentbottom_ll.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                finish();
                break;
            case R.id.forumaddcommentbottom_ll:
                //拍照
                photoPopwindow = new GetPhotoPopwindow(ShoolsForumAddCommentActivity.this,myHandler);
                photoPopwindow.showPopupWindowBottom(shoolforumaddcomment_activity);
                break;
        }
    }
    //=======================================拍照部分===============================================
    /**
     * 拍摄照片handler
     */
    public Handler myHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 10000:
                    //拍照
                    photo();
                    photoPopwindow.dismiss();
                    break;

                case 20000:
                    //本地相册
                    getPhotoFromXC();
                    photoPopwindow.dismiss();
                    break;
            }
        };
    };

    protected void onRestart() {
        if(Bimp.tempSelectBitmap.size()>0){
//            usermsg_showphoto_img.setImageBitmap(Bimp.tempSelectBitmap.get(0).getBitmap());
//            waitDialog.show();
//            File file = new File(Bimp.tempSelectBitmap.get(0).getImagePath());
//            if(file.exists()){
//                personInternetModel.uploadImagePhoto(Bimp.tempSelectBitmap.get(0).getImagePath(),Bimp.tempSelectBitmap.get(0).getFilename(),200);
//            }
        }
        super.onRestart();
    }
    public void photo() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }
    private static final int TAKE_PICTURE = 0x000001;
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                //图片名称
                if(data!=null&&data.getExtras()!=null&&data.getExtras().get("data")!=null){
                    String fileName = String.valueOf(System.currentTimeMillis());
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    String imagepath=FileUtils.saveBitmap(bm, fileName+".png");
//                    BaseUtils.deletePhotoImage();//删除以前照片
                    ImageItem takePhoto = new ImageItem();
                    takePhoto.setBitmap(bm);
                    takePhoto.setImagePath(imagepath);
                    takePhoto.setFilename(fileName+".png");
                    Bimp.tempSelectBitmap.add(takePhoto);
                }else{
                    System.out.print("没有拍摄照片");
                }
                break;
            case Activity.RESULT_CANCELED:
                finish();
                break;
            case 12:
                if (data!= null&&data.getData()!=null) {
                    startPhotoZoom(data.getData());
                }
                break;
            case 3:
                if (data!= null) {
                    setPicToView(data);
                }
                break;
        }
    }
    /**
     * 从相册中选取图片
     */
    public void getPhotoFromXC(){
        /**
         * 从相册获取图片
         */
        Intent intent = new Intent();
 /* 开启Pictures画面Type设定为image */
        intent.setType("image/*");
	/* 使用Intent.ACTION_GET_CONTENT这个Action */
        intent.setAction(Intent.ACTION_GET_CONTENT);
	/* 取得相片后返回本画面 */
        startActivityForResult(intent, 12);
    }
    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        Bitmap photo = extras.getParcelable("data");
        if (extras!= null&&photo!=null) {
            String imagepath= FileUtils.saveBitmap(photo, String.valueOf(System.currentTimeMillis())+".png");
//            BaseUtils.deletePhotoImage();//删除以前照片
            ImageItem takePhoto = new ImageItem();
            takePhoto.setBitmap(photo);
            takePhoto.setImagePath(imagepath);
            takePhoto.setFilename(String.valueOf(System.currentTimeMillis())+".png");
            Bimp.tempSelectBitmap.add(takePhoto);
        }
    }
    /**
     * 裁剪图片方法实现
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }
}
