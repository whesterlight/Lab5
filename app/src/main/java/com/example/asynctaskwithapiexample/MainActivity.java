package com.example.asynctaskwithapiexample;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asynctaskwithapiexample.utilities.DataLoader;
import com.example.asynctaskwithapiexample.utilities.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvItems;
    private TextView tvStatus;
    private ArrayAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lvItems = findViewById(R.id.lv_items);
        this.tvStatus = findViewById(R.id.tv_status);

        this.listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, new ArrayList<>());
        this.lvItems.setAdapter(this.listAdapter);

        getDataByAsyncTask();
    }

    public void onBtnGetDataClick(View view) {
        this.tvStatus.setText(R.string.loading_data);
        getDataByAsyncTask();
        Toast.makeText(this, R.string.msg_using_async_task, Toast.LENGTH_LONG).show();
    }

    public void getDataByAsyncTask() {
        DataLoader.getValuesFromApi(Constants.FLOATRATES_API_URL, new DataLoader.ApiDataListener() {
            @Override
            public void onDataDownloaded(String data) {
                listAdapter.clear();
                listAdapter.addAll();
                listAdapter.notifyDataSetChanged();

                tvStatus.setText(getString(R.string.data_loaded) + data);
            }
        });
    }
}