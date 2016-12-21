package com.sarunassarakojis.totrackerdo.issuemanagement.issueAccess;

import android.provider.BaseColumns;

/**
 * Defines a characteristics of an {@code Issue} table. Particularly - column
 * names for the table that should used whenever there is a need to access
 * specific columns.
 *
 * @author Sarunas Sarakojis
 */
public class IssueContract {

    private IssueContract() {
    }

    /**
     * Defines {@code Issues} table column names in order to
     * avoid typing errors that might occur when wrong column names are passed to
     * the database helper classes.
     */
    public static class IssueEntry implements BaseColumns {
        /**
         * Name of {@code Issues} table
         */
        public static final String ISSUES_TABLE_NAME = "issues";
        /**
         * Name of a column, which holds a summaries for each
         * {@link com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue issue}
         */
        public static final String ISSUE_SUMMARY = "summary";
        /**
         * Name of a column, which holds a descriptions for each
         * {@link com.sarunassarakojis.totrackerdo.issuemanagement.issuedefinition.Issue issue}
         */
        public static final String ISSUE_DESCRIPTION = "description";
    }
}
