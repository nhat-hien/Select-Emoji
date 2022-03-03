package com.example.emoji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    GridView mygridview;
    int start_unicode = 0x1F600;
    int wrong = 0;
    TextView  textview_emoji0, textview_ann;

    private String getEmoji(int unicode){
        return new String(Character.toChars(unicode));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview_emoji0=findViewById(R.id.tv_emoji0);
        mygridview=findViewById(R.id.gv_emoji);
        runGame();
    }

    @Override
    protected  void onResume(){
        super.onResume();
        Toast.makeText(getApplicationContext(),"Restarting game...",Toast.LENGTH_SHORT).show();
        wrong = 0;
        runGame();
    }

    protected void runGame(){
        List data=new ArrayList();
        for (int i=0;i<30;i++){
            String emoji=getEmoji(start_unicode);
            data.add(emoji);
            start_unicode++;
            Collections.shuffle(data);
        }
        textview_emoji0.setText(data.get(0).toString());
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(),R.layout.items,data);
        mygridview.setAdapter(myAdapter);

        List temp = new ArrayList(data);

        mygridview.setOnItemClickListener((parent,view,position,id)->{
            TextView v = (TextView) view;
            String selected =v.getText().toString();
            String emoji_0=textview_emoji0.getText().toString();
            if(selected==emoji_0){
                temp.remove(emoji_0);
                v.setText("");
                Random rand = new Random();
                textview_emoji0.setText(temp.get(rand.nextInt(temp.size())).toString());
                if (temp.size()==0) {
                    startActivity(new Intent(getApplicationContext(),SuccessActivity.class));
                }
            }else{
                wrong++;
                textview_ann=findViewById(R.id.tv_ann);
                textview_ann.setText("Failed! Remaining: "+(3-wrong)+" lives");
                if(wrong>3){
                    startActivity(new Intent(getApplicationContext(),FailedActivity.class));
                }
            }
        });


    }


}