package com.sarunassarakojis.totrackerdo.activity.issuesactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.sarunassarakojis.totrackerdo.R;
import com.sarunassarakojis.totrackerdo.issuemanagement.FlawIssueTypeProvider;
import com.sarunassarakojis.totrackerdo.issuemanagement.IssueBuilder;
import com.sarunassarakojis.totrackerdo.issuemanagement.TODOIssueTypeProvider;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue;

import java.util.ArrayList;
import java.util.List;

public class ViewIssuesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);


        ListView listView = (ListView) findViewById(R.id.listview);
        List<Issue> issueList = new ArrayList<>();

        issueList.add(IssueBuilder.createIssue(new FlawIssueTypeProvider("Flaw issue", "flaw description")));
        issueList.add(IssueBuilder.createIssue(new TODOIssueTypeProvider("TODO issue", "todo description")));

        CustomAdapter adapter = new CustomAdapter(this, issueList);

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setAlpha(1);
    }
}
