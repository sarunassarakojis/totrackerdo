package com.sarunassarakojis.totrackerdo.activity.dashboardactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
 * The <strong>launcher</strong> {@link android.app.Activity activity}.
 * From this activity user is able to invoke all of the main functionality
 * provided by this app, such as viewing currently existing issues, modifying
 * them and so on.
 *
 * @author Sarunas Sarakojis
 * @see ViewIssuesActivity
 */
public class DashboardActivity extends AppCompatActivity {

    private IssueStatisticsFragment issueStatisticsFragment;

    /**
     * Utility <code>class</code> that contains various utility methods for
     * prompting <em>thanks</em> dialogs.
     *
     * @author Sarunas Sarakojis
     */
    public static class ThanksAlertDialogPrompter {

        /**
         * Prompts the user with the <em>thanks</em> dialog for
         * the specified {@link Context}.
         *
         * @param context the parent {@link Context context}
         */
        public static void showThanksDialog(Context context) {
            new AlertDialog.Builder(context)
                    .setTitle(R.string.thanks_menu_item_title)
                    .setIcon(R.drawable.ic_sentiment_very_satisfied_black_24dp)
                    .setMessage(R.string.thanks_dialog_message)
                    .show();
        }
    }

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
                return true;
            case R.id.thanks_menu_item:
                ThanksAlertDialogPrompter.showThanksDialog(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Method invokes another activity that allows to view currently available
     * {@link com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue issues}.
     *
     * @param view the parent {@link View view}
     */
    public void viewIssues(View view) {
        startActivity(new Intent(this, ViewIssuesActivity.class));
    }

    private void refreshFragment() {
        issueStatisticsFragment.fetchLatestIssueStatisticsData();
    }
}
