package com.sarunassarakojis.totrackerdo.activity.issuesactivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sarunassarakojis.totrackerdo.R;
import com.sarunassarakojis.totrackerdo.issuemanagement.issueAccess.IssuesTableUtilities;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue;

import java.util.List;

/**
 * An extension of {@link ArrayAdapter<Issue>} that displays all the issues in
 * the currently running activity. In order to display a particular {@link Issue},
 * it's layout should be defined to support at least <em>2</em> {@link TextView textViews}
 * since a summary and a description of the {@link Issue should be printed}. However, this
 * adapter does not <em>refresh</em> on it's own. If there is a need to update current list
 * of {@link Issue issues} method {@link #updateWithTheLatestIssueData()} should be
 * called.
 *
 * @author Sarunas Sarakojis
 * @see #updateWithTheLatestIssueData()
 */
public class ListViewAdapter extends ArrayAdapter<Issue> {

    private List<Issue> containedIssues;
    private Context context;

    /**
     * Creates an instance of {@link ListViewAdapter} with the provided
     * {@code objects}.
     *
     * @param context the current context
     * @param objects list of {@link Issue issues} to display right after
     *                this adapter is constructed. The data is outputted once
     *                the {@link #getView(int, View, ViewGroup)} is called
     */
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
    public int getCount() {
        return containedIssues.size();
    }

    @Override
    public Issue getItem(int position) {
        return containedIssues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return containedIssues.get(position).getUniqueIdentifier();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * Method invocation causes <code>this</code> adapter to get updated with the latest
     * {@link Issue issue} data from the data source. Keep in mind, that this method accesses
     * the {@link com.sarunassarakojis.totrackerdo.issuemanagement.issueAccess.IssuesDatabaseHelper
     * database}.
     */
    public void updateWithTheLatestIssueData() {
        containedIssues.clear();
        SQLiteDatabase database = IssuesTableUtilities.getReadableDatabase(getContext());
        containedIssues = IssuesTableUtilities.readAllIssues(database);

        database.close();
        notifyDataSetChanged();
    }
}
