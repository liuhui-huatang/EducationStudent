package com.htcompany.education.studentforgansu.mainpart.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.commonpart.views.dateview.DateViewDailog;
import com.htcompany.education.studentforgansu.commonpart.views.photoviews.Bimp;
import com.htcompany.education.studentforgansu.commonpart.views.photoviews.FileUtils;
import com.htcompany.education.studentforgansu.commonpart.views.photoviews.GetPhotoPopwindow;
import com.htcompany.education.studentforgansu.commonpart.views.photoviews.ImageItem;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolThingsInternet;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolTingsPersener;

/**
 * 资产报修添加
 * Created by wrb on 2016/11/1.
 */
public class AssetRepairAddActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_text_btn;
    private ImageView right_share_img,assetrepairadd_photo_img;
    private EditText assetrepairadd_title_edt,assetrepairadd_postion_edt,
            assetrepairadd_content_edt,assetrepairadd_person_edt;
    private TextView assetrepairadd_time_tv;
    private DateViewDailog dateViewDailog;
    private GetPhotoPopwindow photoPopwindow;
    private LinearLayout assetrepairadd_activity;
    //==========网络请求类===========
    private SchoolThingsInternet thingsInternet;
    private SchoolTingsPersener tingsPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assetrepairadd_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        if(Bimp.tempSelectBitmap.size()>0){
            Bimp.tempSelectBitmap.clear();
        }
        thingsInternet = new SchoolThingsInternet(this,myHandler);
        tingsPersener = new SchoolTingsPersener(this);
        waitDialog= new WaitDialog(this,"");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
        right_text_btn=(RelativeLayout)this.findViewById(R.id.right_text_btn);
        right_btn_tv= (TextView)this.findViewById(R.id.right_btn_tv);
        assetrepairadd_photo_img= (ImageView)this.findViewById(R.id.assetrepairadd_photo_img);
        assetrepairadd_title_edt= (EditText) this.findViewById(R.id.assetrepairadd_title_edt);
        assetrepairadd_postion_edt= (EditText) this.findViewById(R.id.assetrepairadd_postion_edt);
        assetrepairadd_content_edt= (EditText) this.findViewById(R.id.assetrepairadd_content_edt);
        assetrepairadd_person_edt= (EditText) this.findViewById(R.id.assetrepairadd_person_edt);
        assetrepairadd_time_tv = (TextView)this.findViewById(R.id.assetrepairadd_time_tv);
        assetrepairadd_activity = (LinearLayout)this.findViewById(R.id.assetrepairadd_activity);
    }
    public void initViewValues(){
        title.setText("添加资产报修");

        right_text_btn.setVisibility(View.VISIBLE);
        right_btn_tv.setText("提交");
    }

    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_text_btn.setOnClickListener(this);
        assetrepairadd_time_tv.setOnClickListener(this);
        assetrepairadd_photo_img.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                finish();
                break;
            case R.id.assetrepairadd_time_tv:
                //开始时间
                dateViewDailog =new DateViewDailog(this,1000,myHandler,false);
                dateViewDailog.show();
                break;
            case R.id.assetrepairadd_photo_img:
                //拍照
                photoPopwindow = new GetPhotoPopwindow(AssetRepairAddActivity.this,myHandler);
                photoPopwindow.showPopupWindowBottom(assetrepairadd_activity);
                break;
            case R.id.right_text_btn:
                if("".equals(assetrepairadd_title_edt.getText().toString())){
                    ToastUtil.showToast("请输入标题",AssetRepairAddActivity.this);
                }else  if("".equals(assetrepairadd_postion_edt.getText().toString())){
                    ToastUtil.showToast("请输入位置",AssetRepairAddActivity.this);
                }else  if("".equals(assetrepairadd_content_edt.getText().toString())){
                    ToastUtil.showToast("请输入描述",AssetRepairAddActivity.this);
                }else {
                     waitDialog.show();
                    thingsInternet.addAsset_RepairListDatas(assetrepairadd_title_edt.getText().toString(),
                            assetrepairadd_postion_edt.getText().toString(),assetrepairadd_content_edt.getText().toString());
                }
                break;
        }
    }
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1000:
                    assetrepairadd_time_tv.setText((String)msg.obj);
                    break;
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
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",AssetRepairAddActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(tingsPersener.parserAddAssetReapir((String)msg.obj)){
                        Intent intent = new Intent();
                        setResult(100,intent);
                        finish();
                    }else{
                        ToastUtil.showToast("请重新添加",AssetRepairAddActivity.this);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",AssetRepairAddActivity.this);
                    break;
            }
        }
    };
    protected void onRestart() {
        if(Bimp.tempSelectBitmap.size()>0){
            assetrepairadd_photo_img.setImageBitmap(Bimp.tempSelectBitmap.get(0).getBitmap());
        }
        super.onRestart();
    }
    public void photo() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(AssetRepairAddActivity.this, Manifest.permission.CAMERA);
            if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(AssetRepairAddActivity.this,new String[]{Manifest.permission.CAMERA},222);
                return;
            }else{

                openCamra();//调用具体方法
            }
        } else {

            openCamra();//调用具体方法
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            //就像onActivityResult一样这个地方就是判断你是从哪来的。
            case 222:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    openCamra();
                } else {
                    // Permission Denied
                    Toast.makeText(AssetRepairAddActivity.this, "很遗憾你把相机权限禁用了。请务必开启相机权限享受我们提供的服务吧。", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    public void openCamra(){
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
                    String imagepath= FileUtils.saveBitmap(bm, fileName+".png");
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
                if (data!= null&&data.getExtras()!=null&&data.getExtras().getParcelable("data")!=null) {
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
