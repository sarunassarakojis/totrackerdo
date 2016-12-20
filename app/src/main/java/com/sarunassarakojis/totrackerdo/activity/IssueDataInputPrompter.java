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

        inputDialogBuilder.setTitle("Add new issue");
        inputDialogBuilder.setView(inflatedView);
        inputDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                issueSummary[0] = summaryInput.getText().toString();
                if (!issueSummary[0].isEmpty()) {
                    issueDescription[0] = descriptionInput.getText().toString();
                    dialog.dismiss();
                    addNewIssueToDatabase(context, issueSummary[0], issueDescription[0]);
                } else {
                    new AlertDialog.Builder(context)
                            .setTitle("Wrong input")
                            .setMessage("Summary should not be empty!")
                            .show();
                }
            }
        });
        inputDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
        inputDialogBuilder.setTitle("Modify an issue");
        inputDialogBuilder.setView(inflatedView);
        inputDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
                            .setTitle("Wrong input")
                            .setMessage("Summary should not be empty!")
                            .show();
                }
            }
        });
        inputDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        inputDialogBuilder.show();
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
}
