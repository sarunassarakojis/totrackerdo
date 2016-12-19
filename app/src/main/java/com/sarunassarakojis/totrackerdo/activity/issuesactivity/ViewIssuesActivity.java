package com.sarunassarakojis.totrackerdo.activity.issuesactivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.sarunassarakojis.totrackerdo.R;
import com.sarunassarakojis.totrackerdo.activity.AddNewIssueInputPrompter;
import com.sarunassarakojis.totrackerdo.issuemanagement.issueAccess.IssuesTableUtilities;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue;

import java.util.List;

public class ViewIssuesActivity extends AppCompatActivity {

    private ListView issueListView;
    private ListViewAdapter issueListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_issues);
        setSupportActionBar((Toolbar) findViewById(R.id.dashboard_activity_toolbar));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        issueListView = (ListView) findViewById(R.id.issue_list_view);
        issueListViewAdapter = new ListViewAdapter(this, obtainIssuesFromTheDataSource());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_new_issue_toolbar_button:
                AddNewIssueInputPrompter.createNewIssueFromUserInputData(this);
                return true;
            case R.id.menu_refresh:
                issueListViewAdapter.setContainedIssues(obtainIssuesFromTheDataSource());
                issueListViewAdapter.notifyDataSetChanged();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        issueListView.setAdapter(issueListViewAdapter);
        issueListViewAdapter.notifyDataSetChanged();
        issueListView.setAlpha(1);
    }

    private List<Issue> obtainIssuesFromTheDataSource() {
        SQLiteDatabase database = IssuesTableUtilities.getReadableDatabase(this);
        List<Issue> issues = IssuesTableUtilities.readAllIssues(database);

        database.close();

        return issues;
    }
}
