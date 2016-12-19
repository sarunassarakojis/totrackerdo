package com.sarunassarakojis.totrackerdo.issuemanagement.issueAccess;

/**
 * Created by Sarunas on 11/30/2016
 */
public class CommonIssueQueries {
    private static String TEXT_TYPE = " TEXT";
    public static String SQL_CREATE_ISSUES_TABLE =
            "CREATE TABLE " + IssueContract.IssueEntry.ISSUES_TABLE_NAME + " (" +
                    IssueContract.IssueEntry._ID + " INTEGER PRIMARY KEY, " +
                    IssueContract.IssueEntry.ISSUE_DESCRIPTION + TEXT_TYPE + ", " +
                    IssueContract.IssueEntry.ISSUE_SUMMARY + TEXT_TYPE + ")";
    public static String SQL_DELETE_ISSUES_TABLE =
            "DROP TABLE IF EXISTS " + IssueContract.IssueEntry.ISSUES_TABLE_NAME;
}
