package com.sarunassarakojis.totrackerdo.issuemanagement.sqlaccess;

import android.provider.BaseColumns;

/**
 * Created by Sarunas on 11/30/2016
 */
public class IssueContract {

    private IssueContract() {
    }

    public static class IssueEntry implements BaseColumns {
        public static final String ISSUES_TABLE_NAME = "issues";
        public static final String ISSUE_SUMMARY = "summary";
        public static final String ISSUE_DESCRIPTION = "description";
    }
}
