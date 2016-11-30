package com.sarunassarakojis.totrackerdo.issuemanagement.issueAccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sarunas on 11/30/2016
 */
public class IssuesDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Issues.db";

    public IssuesDatabaseHelper(Context activityContext) {
        super(activityContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CommonIssueQuerries.SQL_CREATE_ISSUES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CommonIssueQuerries.SQL_DELETE_ISSUES_TABLE);
    }
}
