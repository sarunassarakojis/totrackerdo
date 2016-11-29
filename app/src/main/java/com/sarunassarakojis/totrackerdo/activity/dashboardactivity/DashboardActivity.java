package com.sarunassarakojis.totrackerdo.activity.dashboardactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sarunassarakojis.totrackerdo.R;
import com.sarunassarakojis.totrackerdo.activity.dashboardactivity.fragment.IssueStatisticsFragment;
import com.sarunassarakojis.totrackerdo.activity.issuesactivity.ViewIssuesActivity;

/**
 * Requirements for the second laboratory:
 * <p>
 * 1) Fully completed GUI
 * 2) Required functionality implemented
 *
 * @author Sarunas Sarakojis
 */
// TODO finish GUI
// TODO retrieve data from sqlite
public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new IssueStatisticsFragment())
                    .commit();
        }
    }

    public void viewIssues(View view) {
        startActivity(new Intent(this, ViewIssuesActivity.class));
    }
}
