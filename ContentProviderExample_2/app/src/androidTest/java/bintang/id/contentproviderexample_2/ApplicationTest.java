package bintang.id.contentproviderexample_2;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.test.ApplicationTestCase;

public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }


    public void testInsert_DbHelper() {
        SQLiteDatabase db = new DatabaseHelper(getContext()).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EmployeeProvider.NAME, "Luis");
        contentValues.put(EmployeeProvider.GRADE, "A");
        db.insert(DatabaseHelper.EMPLOYEE_TABLE_NAME, "", contentValues);
    }

    public void testGet_dbHelper() throws Exception {
        SQLiteDatabase db = new DatabaseHelper(getContext()).getReadableDatabase();
        String[] columns = new String[2];
        columns[0] = "name";
        columns[1] = "grade";
        Cursor cursor = db.query(DatabaseHelper.EMPLOYEE_TABLE_NAME, columns, "", null, "", "", "name");
        if (cursor == null)
            throw new Exception("Cursor is null");
        if (!cursor.isClosed())
            cursor.close();
        assertTrue(true);
    }

    public void testInsert_Provider() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EmployeeProvider.NAME, "Luis");
        contentValues.put(EmployeeProvider.GRADE, "A");

        Uri uri = getContext().getContentResolver().insert(EmployeeProvider.CONTENT_URI, contentValues);
        assertTrue(uri != null);
    }

    public void testGet_Provider() throws Exception {
        Cursor cursor = getContext().getContentResolver().query(EmployeeProvider.CONTENT_URI, null, null, null, "name");
        if (cursor == null)
            throw new Exception("Cursor is null");
        if (!cursor.isClosed())
            cursor.close();
        assertTrue(true);
    }
}