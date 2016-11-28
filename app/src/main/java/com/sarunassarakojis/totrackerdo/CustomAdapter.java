package com.sarunassarakojis.totrackerdo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Issue> {

    List<Issue> list;
    Context context;

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

        second.setText(list.get(position).secondLine);
        first.setText(list.get(position).firstLine);

        return rowView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
