package com.sarunassarakojis.totrackerdo.issuemanagement.issueAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sarunassarakojis.totrackerdo.issuemanagement.IssueProvider;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue;
import com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.IssueType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarunas on 11/30/2016
 */
public class IssuesTableUtilities {

    private static final String[] ISSUE_COLUMNS = {
            IssueContract.IssueEntry._ID,
            IssueContract.IssueEntry.ISSUE_DESCRIPTION,
            IssueContract.IssueEntry.ISSUE_SUMMARY};

    private IssuesTableUtilities() {
    }

    public static SQLiteDatabase getWritableDatabase(Context activityContext) {
        IssuesDatabaseHelper databaseHelper = new IssuesDatabaseHelper(activityContext);

        return databaseHelper.getWritableDatabase();
    }

    public static SQLiteDatabase getReadableDatabase(Context activityContext) {
        IssuesDatabaseHelper databaseHelper = new IssuesDatabaseHelper(activityContext);

        return databaseHelper.getReadableDatabase();
    }

    public static long insertNewIssue(Issue issueToInsert, SQLiteDatabase writableDatabase) {
        ContentValues newIssueValues = new ContentValues();

        newIssueValues.put(IssueContract.IssueEntry.ISSUE_SUMMARY, issueToInsert.getSummary());
        newIssueValues.put(IssueContract.IssueEntry.ISSUE_DESCRIPTION, issueToInsert.getDescription());

        return writableDatabase.insert(IssueContract.IssueEntry.ISSUES_TABLE_NAME, null, newIssueValues);
    }

    public static int getTotalCountOfIssues(SQLiteDatabase readableDatabase) {
        return getCursorPointingToAllIssues(readableDatabase).getCount();
    }

    public static List<Issue> readAllIssues(SQLiteDatabase readableDatabase) {
        return mapCursorToListOfIssues(getCursorPointingToAllIssues(readableDatabase));
    }

    public static int deleteSpecifiedIssue(SQLiteDatabase writableDatabase, Issue issueToRemove) {
        String whereClause = IssueContract.IssueEntry._ID + "=?";
        String[] arguments = {String.valueOf(issueToRemove.getUniqueIdentifier())};

        return writableDatabase.delete(IssueContract.IssueEntry.ISSUES_TABLE_NAME, whereClause, arguments);
    }

    private static Cursor getCursorPointingToAllIssues(SQLiteDatabase readableDatabase) {
        return readableDatabase.query(IssueContract.IssueEntry.ISSUES_TABLE_NAME,
                ISSUE_COLUMNS, null, null, null, null, null);
    }

    private static List<Issue> mapCursorToListOfIssues(Cursor resultCursor) {
        int idColumn = resultCursor.getColumnIndex(IssueContract.IssueEntry._ID);
        int summaryIndex = resultCursor.getColumnIndex(IssueContract.IssueEntry.ISSUE_SUMMARY);
        int descriptionIndex = resultCursor.getColumnIndex(IssueContract.IssueEntry.ISSUE_DESCRIPTION);
        IssueProvider issueProvider = IssueProvider.getConcreteIssueProvider(IssueType.TODO);
        List<Issue> allIssues = new ArrayList<>(resultCursor.getCount());

        if (resultCursor.moveToFirst()) {
            do {
                allIssues.add(issueProvider.createIssue(
                        resultCursor.getInt(idColumn),
                        resultCursor.getString(summaryIndex),
                        resultCursor.getString(descriptionIndex)));
            } while (resultCursor.moveToNext());
        }

        return allIssues;
    }
}
