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

public class CustomAdapter extends ArrayAdapter<Issue> {

    private List<Issue> list;
    private Context context;

    public CustomAdapter(Context context, List<Issue> objects) {
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
        TextView second = (TextView) rowView.findViewById(R.id.secondLine);
        TextView first = (TextView) rowView.findViewById(R.id.firstLine);

        second.setText(list.get(position).getDescription());
        first.setText(list.get(position).getSummary());

        return rowView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
