/*
 * Copyright (C) 2015 The Android Open Source Project 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package cn.six.sup.rv.sort;
 
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager; 
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import cn.six.sup.R;

/**
 * A sample activity that uses {@link SortedList} in combination with RecyclerView.
 */
public class SortedListActivity extends AppCompatActivity {
    private SortedListAdapter adapter;
    private EditText etAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_list);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rvSortedList);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SortedListAdapter(getLayoutInflater(),
                new TaskStatus("buy milk"), new TaskStatus("wash the car"),
                new TaskStatus("wash the dishes"),
                new TaskStatus("write a blog"), new TaskStatus("cock curry"));
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);

        etAddTask = (EditText) findViewById(R.id.etSortedList);
    }

    public void onAddNewTask(View v){
        final String text = etAddTask.getText().toString().trim();
        if (text.length() > 0) {
            adapter.addItem(new TaskStatus(text));
        }
        etAddTask.setText("");
    }

}