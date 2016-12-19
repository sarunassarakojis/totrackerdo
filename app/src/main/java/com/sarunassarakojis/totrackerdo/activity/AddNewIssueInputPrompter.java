package com.sarunassarakojis.totrackerdo.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.sarunassarakojis.totrackerdo.R;

/**
 * Created by Sarunas on 12/1/2016
 */
public class AddNewIssueInputPrompter {

    public static void createNewIssueFromUserInputData(final Context context) {
        final String[] issueSummary = {""};
        final String[] issueDescription = {""};
        AlertDialog.Builder inputDialogBuilder = new AlertDialog.Builder(context);
        View inflatedView = LayoutInflater.from(context).inflate(R.layout.new_issue_input, null);
        final EditText summaryInput = (EditText) inflatedView.findViewById(R.id.issue_summary_input);
        final EditText descriptionInput = (EditText) inflatedView.findViewById(R.id.issue_description_input);

        inputDialogBuilder.setTitle("Title");
        inputDialogBuilder.setView(inflatedView);
        inputDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                issueSummary[0] = summaryInput.getText().toString();
                if (!issueSummary[0].isEmpty()) {
                    issueDescription[0] = descriptionInput.getText().toString();
                    dialog.dismiss();
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
}
