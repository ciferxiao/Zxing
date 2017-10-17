package com.example.xiaojinggong.zxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaojinggong.zxing.google.zxing.zxing.activity.CaptureActivity;
import com.example.xiaojinggong.zxing.google.zxing.zxing.encoding.EncodingHandler;
import com.example.xiaojinggong.zxing.utils.CommonUtil;

/**
 * Created by cifer on 10/17/17.
 */

public class Zxing extends AppCompatActivity implements View.OnClickListener{
    /*@BindView(R.id.openQrCodeScan)
    @BindView(R.id.qrCodeText)
    @BindView(R.id.text)
    @BindView(R.id.CreateQrCode)
    @BindView(R.id.QrCode)
  */
    EditText textView;
    Button Createcode;
    ImageView imageView;
    Button openorscan;
    TextView qrCodeText;
    //打开扫描界面请求码
    private int REQUEST_CODE = 0x01;
    //扫描成功返回码
    private int RESULT_OK = 0xA1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zxing);
        intiview();
        //ButterKnife.bind(this);
    }

    private void intiview(){
        openorscan = (Button)findViewById(R.id.openQrCodeScan);
        Createcode = (Button)findViewById(R.id.CreateQrCode);
        textView = (EditText) findViewById(R.id.text);
        imageView = (ImageView)findViewById(R.id.QrCode);
        qrCodeText = (TextView)findViewById(R.id.qrCodeText);
        openorscan.setOnClickListener(this);
        Createcode.setOnClickListener(this);
    }
   // @OnClick({R.id.openQrCodeScan, R.id.CreateQrCode})

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.openQrCodeScan:
                if(CommonUtil.isCameraCanUse()){
                    Intent intent =new Intent(this, CaptureActivity.class);
                    startActivityForResult(intent,REQUEST_CODE);
                }else {
                    Toast.makeText(this,"please conform your camera",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.CreateQrCode:
                String codetext = textView.getText().toString();
                String testtext = "";
                if(!codetext.isEmpty()){
                    try{
                        Bitmap bitmap= EncodingHandler.createQRCode(codetext,500);
                        if(bitmap != null){
                            imageView.setImageBitmap(bitmap);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(this,"please conform your text",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            String text = bundle.getString("qr_scan_result");
            qrCodeText.setText(text);
        }
    }
}
