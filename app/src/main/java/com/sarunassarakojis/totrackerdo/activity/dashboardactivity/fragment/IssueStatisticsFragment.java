package com.sarunassarakojis.totrackerdo.activity.dashboardactivity.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sarunassarakojis.totrackerdo.R;
import com.sarunassarakojis.totrackerdo.issuemanagement.issueAccess.IssuesTableUtilities;

/**
 * An extension of {@link Fragment} whose purpose is to
 * display some sort of {@link com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue}
 * statistics. Particularly, this <code>fragment</code> displays
 * the count of currently existing issues. However, the value does not
 * <em>refresh</em> on it's own. In order to work around it, the method
 * {@link #fetchLatestIssueStatisticsData()} might be called.
 *
 * @author Sarunas Sarakojis
 * @see #fetchLatestIssueStatisticsData()
 */
public class IssueStatisticsFragment extends Fragment {

    private SQLiteDatabase database;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = IssuesTableUtilities.getReadableDatabase(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.issue_statistics_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        TextView issueCount = (TextView) getActivity().findViewById(R.id.issue_count);
        Integer countOfIssues = IssuesTableUtilities.getTotalCountOfIssues(database);

        issueCount.setText(countOfIssues.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (database != null) {
            database.close();
        }
    }

    /**
     * Method invocation causes <code>this</code> {@link Fragment} to be updated.
     * If the issue count that is being displayed is outdated, calling this method
     * will cause it to refresh.
     * <p><strong>NOTE:</strong> calling this method actually causes <code>this</code>
     * {@link Fragment} to removed and added back to {@link android.support.v4.app.FragmentManager}
     * again.
     *
     * @see android.support.v4.app.FragmentManager
     */
    public void fetchLatestIssueStatisticsData() {
        getFragmentManager()
                .beginTransaction()
                .detach(this)
                .attach(this)
                .commit();
    }
}
