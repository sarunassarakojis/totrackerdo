package com.sarunassarakojis.totrackerdo.issuemanagement.issueAccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This particular extension of {@link SQLiteOpenHelper} handles events
 * when database gets upgraded or downgraded.
 *
 * @author Sarunas Sarakojis
 */
public class IssuesDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Issues.db";

    /**
     * Creates an instance of <code>this</code> helper.
     *
     * @param activityContext context to use when opening/closing the database
     */
    public IssuesDatabaseHelper(Context activityContext) {
        super(activityContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CommonIssueQueries.SQL_CREATE_ISSUES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CommonIssueQueries.SQL_DELETE_ISSUES_TABLE);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
