package com.sarunassarakojis.totrackerdo.issuemanagement.issueAccess;

import android.content.ContentProvider;
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
 * Helper <code>class</code> that provides utility methods in order to minimize
 * the efforts needed to add, update or remove particular {@link Issue}.
 *
 * @author Sarunas Sarakojis
 */
public class IssuesTableUtilities {

    private static final String[] ISSUE_COLUMNS = {
            IssueContract.IssueEntry._ID,
            IssueContract.IssueEntry.ISSUE_DESCRIPTION,
            IssueContract.IssueEntry.ISSUE_SUMMARY};

    private IssuesTableUtilities() {
    }

    /**
     * Obtains a writable database.
     * <p>
     * <strong>NOTE: </strong> Database upgrade may take a
     * long time, you should not call this method from the application
     * main thread, including from the {@link ContentProvider#onCreate()}
     *
     * @param activityContext the context for which the database is going to opened
     * @return writable database
     */
    public static SQLiteDatabase getWritableDatabase(Context activityContext) {
        IssuesDatabaseHelper databaseHelper = new IssuesDatabaseHelper(activityContext);

        return databaseHelper.getWritableDatabase();
    }

    /**
     * Obtains a readable database.
     * <p>
     * <strong>NOTE: </strong> Database upgrade may take a
     * long time, you should not call this method from the application
     * main thread, including from the {@link ContentProvider#onCreate()}
     *
     * @param activityContext the context for which the database is going to opened
     * @return readable database
     */
    public static SQLiteDatabase getReadableDatabase(Context activityContext) {
        IssuesDatabaseHelper databaseHelper = new IssuesDatabaseHelper(activityContext);

        return databaseHelper.getReadableDatabase();
    }

    /**
     * Adds a new {@link Issue} to the database.
     *
     * @param issueToInsert    an implementation of the {@link Issue} that should be added
     * @param writableDatabase a reference to the database as to which the issue should be added
     * @return the row <em>ID</em> of the newly inserted row, or {@code -1} if an error has occurred
     * @see #getWritableDatabase(Context)
     */
    public static long insertNewIssue(Issue issueToInsert, SQLiteDatabase writableDatabase) {
        ContentValues newIssueValues = new ContentValues();

        newIssueValues.put(IssueContract.IssueEntry.ISSUE_SUMMARY, issueToInsert.getSummary());
        newIssueValues.put(IssueContract.IssueEntry.ISSUE_DESCRIPTION, issueToInsert.getDescription());

        return writableDatabase.insert(IssueContract.IssueEntry.ISSUES_TABLE_NAME, null, newIssueValues);
    }

    /**
     * Returns the count of currently existing issues in the database.
     *
     * @param readableDatabase database to read
     * @return count of total {@link Issue issues}
     */
    public static int getTotalCountOfIssues(SQLiteDatabase readableDatabase) {
        return getCursorPointingToAllIssues(readableDatabase).getCount();
    }

    /**
     * Returns a {@link List} of all existing {@link Issue issues} in the database.
     *
     * @param readableDatabase database to write
     * @return all available {@link Issue issues}
     */
    public static List<Issue> readAllIssues(SQLiteDatabase readableDatabase) {
        return mapCursorToListOfIssues(getCursorPointingToAllIssues(readableDatabase));
    }

    /**
     * Deletes specified {@link Issue issueToRemove} from the writable database
     *
     * @param writableDatabase database to execute query on
     * @param issueToRemove issue that should be removed
     * @return the number of rows affected. {@code 1} stands for successful removal
     */
    public static int deleteSpecifiedIssue(SQLiteDatabase writableDatabase, Issue issueToRemove) {
        String whereClause = IssueContract.IssueEntry._ID + "=?";
        String[] arguments = {String.valueOf(issueToRemove.getUniqueIdentifier())};

        return writableDatabase.delete(IssueContract.IssueEntry.ISSUES_TABLE_NAME, whereClause, arguments);
    }

    /**
     * Updates specified {@link Issue issueToUpdate} from the writable database
     *
     * @param writableDatabase database to execute query on
     * @param issueToUpdate issue that should be updated
     * @return the number of rows affected. {@code 1} stands for successful update
     */
    public static int updateSpecifiedIssue(SQLiteDatabase writableDatabase, Issue issueToUpdate) {
        ContentValues contentValues = new ContentValues();
        String whereClause = IssueContract.IssueEntry._ID + "=?";
        String[] arguments = {String.valueOf(issueToUpdate.getUniqueIdentifier())};

        contentValues.put(IssueContract.IssueEntry.ISSUE_SUMMARY, issueToUpdate.getSummary());
        contentValues.put(IssueContract.IssueEntry.ISSUE_DESCRIPTION, issueToUpdate.getDescription());

        return writableDatabase.update(IssueContract.IssueEntry.ISSUES_TABLE_NAME, contentValues, whereClause, arguments);
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
