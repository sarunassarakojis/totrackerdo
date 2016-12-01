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

    private List<Issue> list;
    private Context context;

    public ListViewAdapter(Context context, List<Issue> objects) {
        super(context, R.layout.issue_layout, objects);

        list = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.issue_layout, parent, false);
        TextView first = (TextView) rowView.findViewById(R.id.issue_summary);
        TextView second = (TextView) rowView.findViewById(R.id.issue_description);

        first.setText(list.get(position).getSummary());
        second.setText(list.get(position).getDescription());

        return rowView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
