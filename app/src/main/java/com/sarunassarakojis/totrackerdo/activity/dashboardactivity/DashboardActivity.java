package com.sarunassarakojis.totrackerdo.activity.dashboardactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sarunassarakojis.totrackerdo.R;
import com.sarunassarakojis.totrackerdo.activity.IssueDataInputPrompter;
import com.sarunassarakojis.totrackerdo.activity.dashboardactivity.fragment.IssueStatisticsFragment;
import com.sarunassarakojis.totrackerdo.activity.issuesactivity.ViewIssuesActivity;

/**
 * @author Sarunas Sarakojis
 */
public class DashboardActivity extends AppCompatActivity {

    private IssueStatisticsFragment issueStatisticsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            issueStatisticsFragment = new IssueStatisticsFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, issueStatisticsFragment)
                    .commit();
        }

        setSupportActionBar((Toolbar) findViewById(R.id.dashboard_activity_toolbar));
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
                refreshFragment();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void viewIssues(View view) {
        startActivity(new Intent(this, ViewIssuesActivity.class));
    }

    private void refreshFragment() {
        issueStatisticsFragment.fetchLatestIssueStatisticsData();
    }
}
