package com.example.emoji;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Random;

public class MyAdapter extends ArrayAdapter {
    public MyAdapter(@NonNull Context context, int resourse, @NonNull List object){
        super(context,resourse,object);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
        View view =super.getView(position,convertView,parent);
        Random rand = new Random();
        view.setBackgroundColor(Color.rgb(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
        return view;
    }
}
