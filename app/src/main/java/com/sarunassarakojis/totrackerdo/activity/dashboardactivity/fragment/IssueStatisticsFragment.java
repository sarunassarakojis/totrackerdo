package com.sarunassarakojis.totrackerdo.activity.dashboardactivity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sarunassarakojis.totrackerdo.R;

/**
 * Created by Sarunas on 11/29/2016
 */
public class IssueStatisticsFragment extends Fragment {

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

        // TODO retrieve issue count from the database and set it here.
    }
}
