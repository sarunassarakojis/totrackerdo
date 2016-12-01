package com.sarunassarakojis.totrackerdo.activity.issuesactivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ListView;

import com.sarunassarakojis.totrackerdo.R;
import com.sarunassarakojis.totrackerdo.issuemanagement.issueAccess.IssuesTableUtilities;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue;

import java.util.List;

public class ViewIssuesActivity extends AppCompatActivity {

    private List<Issue> allIssues;
    private ListView issueListView;
    private ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_issues);
        setSupportActionBar((Toolbar) findViewById(R.id.dashboard_activity_toolbar));

        SQLiteDatabase database = IssuesTableUtilities.getReadableDatabase(getApplicationContext());
        issueListView = (ListView) findViewById(R.id.issue_list_view);
        allIssues = IssuesTableUtilities.readAllIssues(database);
        listViewAdapter = new ListViewAdapter(this, allIssues);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        issueListView.setAdapter(listViewAdapter);
        listViewAdapter.notifyDataSetChanged();
        issueListView.setAlpha(1);
    }
}
