package com.portfolio.adinput;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<SampleData> newDataList;

    public String plus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.InitializeNewData();

        ListView listView = (ListView) findViewById(R.id.listView);
        final MyAdapter myAdapter = new MyAdapter(this, newDataList);

        listView.setAdapter(myAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                moveToActivityTwo();
                return true;
            }
        });
    }

    public void moveToActivityTwo() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

    public void InitializeNewData() {
        newDataList = new ArrayList<SampleData>();

        newDataList.add(new SampleData("새 데이터 추가하기"));
        newDataList.add(new SampleData("새 데이터 추가하기"));
        newDataList.add(new SampleData("새 데이터 추가하기"));
    }
}


