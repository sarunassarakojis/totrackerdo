package com.sarunassarakojis.totrackerdo.activity.issuesactivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sarunassarakojis.totrackerdo.R;
import com.sarunassarakojis.totrackerdo.activity.IssueDataInputPrompter;
import com.sarunassarakojis.totrackerdo.activity.dashboardactivity.DashboardActivity;
import com.sarunassarakojis.totrackerdo.issuemanagement.issueAccess.IssuesTableUtilities;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue;

import java.util.List;

public class ViewIssuesActivity extends AppCompatActivity {

    private ListView issueListView;
    private int selectedIssue;
    private ListViewAdapter issueListViewAdapter;

    private class IssueSelectedFromListListener implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            PopupMenu popupMenu = new PopupMenu(ViewIssuesActivity.this, view);
            selectedIssue = position;

            popupMenu.inflate(R.menu.issue_context_menu);
            popupMenu.setOnMenuItemClickListener(new IssuePopupMenuItemListener());
            popupMenu.show();

            return true;
        }
    }

    private class IssuePopupMenuItemListener implements PopupMenu.OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.modify_issue_context_menu_item:
                    modifySelectedIssue();
                    return true;
                case R.id.remove_issue_context_menu_item:
                    removeSelectedIssue();
                    return true;
            }

            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_issues);
        setSupportActionBar((Toolbar) findViewById(R.id.dashboard_activity_toolbar));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        issueListView = (ListView) findViewById(R.id.issue_list_view);
        issueListViewAdapter = new ListViewAdapter(this, obtainIssuesFromTheDataSource());

        issueListView.setOnItemLongClickListener(new IssueSelectedFromListListener());
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
                IssueDataInputPrompter.createNewIssueFromUserInputData(this);
                return true;
            case R.id.menu_refresh:
                issueListViewAdapter.updateWithTheLatestIssueData();
                return true;
            case R.id.thanks_menu_item:
                DashboardActivity.ThanksAlertDialogPrompter.showThanksDialog(this);
                return true;
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

    private void modifySelectedIssue() {
        IssueDataInputPrompter.editProvidedIssueFromUserInputData(this, getSelectedIssue());
    }

    private void removeSelectedIssue() {
        IssueDataInputPrompter.removeProvidedIssue(this, getSelectedIssue(), issueListViewAdapter);
    }

    private Issue getSelectedIssue() {
        return issueListViewAdapter.getItem(selectedIssue);
    }
}
