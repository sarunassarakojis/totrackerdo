package com.sarunassarakojis.totrackerdo.issuemanagement.issueAccess;

/**
 * Defines common queries for the
 * {@link com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue issues}
 * table, such as create table, or drop it.
 *
 * @author Sarunas Sarakojis
 */
public class CommonIssueQueries {
    private static String TEXT_TYPE = " TEXT";
    /**
     * Creates a
     * {@link com.sarunassarakojis.totrackerdo.issuemanagement.issueAccess.IssueContract.IssueEntry#ISSUES_TABLE_NAME Issues table}
     * with <strong>3</strong> columns, that is : id, summary (text) and description (text)
     */
    public static String SQL_CREATE_ISSUES_TABLE =
            "CREATE TABLE " + IssueContract.IssueEntry.ISSUES_TABLE_NAME + " (" +
                    IssueContract.IssueEntry._ID + " INTEGER PRIMARY KEY, " +
                    IssueContract.IssueEntry.ISSUE_DESCRIPTION + TEXT_TYPE + ", " +
                    IssueContract.IssueEntry.ISSUE_SUMMARY + TEXT_TYPE + ")";
    /**
     * Drops currently Issues table if it exists
     */
    public static String SQL_DELETE_ISSUES_TABLE =
            "DROP TABLE IF EXISTS " + IssueContract.IssueEntry.ISSUES_TABLE_NAME;
}
