package com.zhifu.findviewbyid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zhifu.findbyrapid.FindContenview;
import com.zhifu.findbyrapid.FindViewByid;
import com.zhifu.findbyrapid.FindViewByidd;
import com.zhifu.findbyrapid.FindViewOnclick;

@FindContenview(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @FindViewByidd(R.id.button)
    Button button;
    @FindViewByidd(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FindViewByid.inject(this);
    }
    @FindViewOnclick({R.id.button,R.id.button2,R.id.button3,R.id.button4})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button:
                Toast.makeText(this, "事件1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Toast.makeText(this, "事件2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                Toast.makeText(this, "事件3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button4:
                Toast.makeText(this, "事件4", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
