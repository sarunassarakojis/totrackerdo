package com.sarunassarakojis.totrackerdo.activity.issuesactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sarunassarakojis.totrackerdo.R;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Issue> {

    private List<Issue> containedIssues;
    private Context context;

    public ListViewAdapter(Context context, List<Issue> objects) {
        super(context, R.layout.issue_layout, objects);

        containedIssues = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.issue_layout, parent, false);
        TextView summaryView = (TextView) rowView.findViewById(R.id.issue_summary);
        TextView descriptionView = (TextView) rowView.findViewById(R.id.issue_description);

        summaryView.setText(containedIssues.get(position).getSummary());
        descriptionView.setText(containedIssues.get(position).getDescription());

        return rowView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    public void setContainedIssues(List<Issue> containedIssues) {
        this.containedIssues = containedIssues;
    }
}
