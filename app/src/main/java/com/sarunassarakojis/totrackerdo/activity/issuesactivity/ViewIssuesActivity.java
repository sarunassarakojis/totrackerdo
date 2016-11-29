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

    private List<Issue> allIssues;
    private ListView issueListView;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_issues);

        issueListView = (ListView) findViewById(R.id.issue_list_view);
        allIssues = new ArrayList<>();
        customAdapter = new CustomAdapter(this, allIssues);
    }

    @Override
    protected void onStart() {
        super.onStart();

        allIssues.add(IssueBuilder.createIssue(new TODOIssueTypeProvider("TODO issue", "todo description")));
        allIssues.add(IssueBuilder.createIssue(new FlawIssueTypeProvider("Flaw issue", "flaw description")));
        issueListView.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();
        issueListView.setAlpha(1);
    }
}
