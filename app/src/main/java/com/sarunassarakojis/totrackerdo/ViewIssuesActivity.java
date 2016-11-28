package com.sarunassarakojis.totrackerdo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewIssuesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);


        ListView listView = (ListView) findViewById(R.id.listview);
        List<Issue> issueList = new ArrayList<>();

        issueList.add(new Issue("Issue1", "Description1"));
        issueList.add(new Issue("Issue2", "Description2"));
        issueList.add(new Issue("Issue3", "Description3"));
        issueList.add(new Issue("Issue4", "Description4"));

        CustomAdapter adapter = new CustomAdapter(this, issueList);

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setAlpha(1);
    }
}
