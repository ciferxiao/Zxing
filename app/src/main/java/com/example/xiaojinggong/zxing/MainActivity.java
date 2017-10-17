package com.example.xiaojinggong.zxing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xiaojinggong.zxing.View.CreditScoreView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private CreditScoreView creditScoreView;
    private Button button;
    private ListView listView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        button = (Button)findViewById(R.id.button);
        creditScoreView = (CreditScoreView)findViewById(R.id.view);
        listView = (ListView)findViewById(R.id.list);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatbutton);
        button.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                List<Float> list = new ArrayList<>();
                float[] data = new float[5];
                for(int i=0;i<5;i++){
                    float y = (float)Math.random();
                    float x = (y*4+5)*20;
                    data[i]=x;
                    list.add(x);
                }
                ArrayAdapter<Float> adapter = new ArrayAdapter<Float>(this,android.R.layout.simple_list_item_1);
                listView.setAdapter(adapter);
                creditScoreView.Refreshdata(data);
                break;
            case R.id.floatbutton:
                Toast.makeText(this,"000",Toast.LENGTH_SHORT);
                Snackbar.make(view,"进入zxing界面",Snackbar.LENGTH_SHORT)
                        .setAction("yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(MainActivity.this, Zxing.class);
                                startActivity(intent);
                            }
                        }).show();
                break;
        }
    }


}
