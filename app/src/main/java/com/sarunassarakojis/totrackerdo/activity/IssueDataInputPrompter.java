package com.sarunassarakojis.totrackerdo.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sarunassarakojis.totrackerdo.R;
import com.sarunassarakojis.totrackerdo.activity.issuesactivity.ListViewAdapter;
import com.sarunassarakojis.totrackerdo.issuemanagement.issueAccess.IssuesTableUtilities;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.TODOIssue;

/**
 * Created by Sarunas on 12/1/2016
 */
public class IssueDataInputPrompter {

    public static void createNewIssueFromUserInputData(final Context context) {
        final String[] issueSummary = {""};
        final String[] issueDescription = {""};
        AlertDialog.Builder inputDialogBuilder = new AlertDialog.Builder(context);
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.new_issue_input, null);
        final EditText summaryInput = (EditText) inflatedView.findViewById(R.id.issue_summary_input);
        final EditText descriptionInput = (EditText) inflatedView.findViewById(R.id.issue_description_input);

        inputDialogBuilder.setTitle(R.string.add_new_issue_toolbar_action);
        inputDialogBuilder.setView(inflatedView);
        inputDialogBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                issueSummary[0] = summaryInput.getText().toString();
                if (!issueSummary[0].isEmpty()) {
                    issueDescription[0] = descriptionInput.getText().toString();
                    dialog.dismiss();
                    addNewIssueToDatabase(context, issueSummary[0], issueDescription[0]);
                } else {
                    new AlertDialog.Builder(context)
                            .setTitle(R.string.wrong_input)
                            .setMessage(R.string.summary_should_not_be_empty_message)
                            .show();
                }
            }
        });
        inputDialogBuilder.setNegativeButton(R.string.cancel_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        inputDialogBuilder.show();
    }

    public static void editProvidedIssueFromUserInputData(final Context context, final Issue editableIssue) {
        final String[] issueSummary = {""};
        final String[] issueDescription = {""};
        AlertDialog.Builder inputDialogBuilder = new AlertDialog.Builder(context);
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.new_issue_input, null);
        final EditText summaryInput = (EditText) inflatedView.findViewById(R.id.issue_summary_input);
        final EditText descriptionInput = (EditText) inflatedView.findViewById(R.id.issue_description_input);

        summaryInput.setText(editableIssue.getSummary());
        descriptionInput.setText(editableIssue.getDescription());
        inputDialogBuilder.setTitle(R.string.modify_issue_context_menu_title);
        inputDialogBuilder.setView(inflatedView);
        inputDialogBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                issueSummary[0] = summaryInput.getText().toString();
                if (!issueSummary[0].isEmpty()) {
                    issueDescription[0] = descriptionInput.getText().toString();
                    dialog.dismiss();
                    editableIssue.setSummary(issueSummary[0]);
                    editableIssue.setDescription(issueDescription[0]);
                    updateIssueData(context, editableIssue);
                } else {
                    new AlertDialog.Builder(context)
                            .setTitle(R.string.wrong_input)
                            .setMessage(R.string.summary_should_not_be_empty_message)
                            .show();
                }
            }
        });
        inputDialogBuilder.setNegativeButton(R.string.cancel_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        inputDialogBuilder.show();
    }

    public static void removeProvidedIssue(final Context context, final Issue issueToRemove, final ListViewAdapter adapter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(R.string.confirmation_message);

        builder.setNegativeButton(R.string.cancel_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                removeIssueData(context, issueToRemove);
                adapter.updateWithTheLatestIssueData();
            }
        });
        builder.show();
    }

    private static void removeIssueData(final Context context, Issue issueToRemove) {
        SQLiteDatabase database = IssuesTableUtilities.getWritableDatabase(context);

        if (IssuesTableUtilities.deleteSpecifiedIssue(database, issueToRemove) > 0) {
            displayIssueRemovedToast(context);
        }
    }

    private static void updateIssueData(final Context context, Issue editedIssue) {
        SQLiteDatabase database = IssuesTableUtilities.getWritableDatabase(context);

        if (IssuesTableUtilities.updateSpecifiedIssue(database, editedIssue) > 0) {
            displayIssueEditedToast(context);
        }
    }

    private static void addNewIssueToDatabase(final Context context, String issueSummary, String issueDescription) {
        SQLiteDatabase database = IssuesTableUtilities.getWritableDatabase(context);

        IssuesTableUtilities.insertNewIssue(new TODOIssue(issueSummary, issueDescription), database);
        displayIssueAddedToast(context);
        database.close();
    }

    private static void displayIssueAddedToast(final Context context) {
        Toast.makeText(context, R.string.issue_added_toast, Toast.LENGTH_SHORT).show();
    }

    private static void displayIssueEditedToast(final Context context) {
        Toast.makeText(context, R.string.issue_modified_toast, Toast.LENGTH_SHORT).show();
    }

    private static void displayIssueRemovedToast(final Context context) {
        Toast.makeText(context, R.string.issue_removed_toast, Toast.LENGTH_SHORT).show();
    }
}
