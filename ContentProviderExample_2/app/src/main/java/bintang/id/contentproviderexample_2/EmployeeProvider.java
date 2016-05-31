package bintang.id.contentproviderexample_2;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by bintang on 1/28/2016.
 * Purpose : Provider for dbHelper, UI must call this object to access database
 */
public class EmployeeProvider extends ContentProvider {
    static final String PROVIDER_NAME = "bintang.id.contentproviderexample_2.provider";
    static final String URL = "content://" + PROVIDER_NAME+ "/EmployeeProvider";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id";
    static final String NAME = "name";
    static final String GRADE = "grade";
    static final int STUDENTS = 1;
    static final int STUDENT_ID = 2;
    static final UriMatcher uriMatcher;
    public static HashMap<String, String> STUDENTS_PROJECTION_MAP;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "EmployeeProvider", STUDENTS);
        uriMatcher.addURI(PROVIDER_NAME, "EmployeeProvider/#", STUDENT_ID);
    }

    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        db = dbHelper.getWritableDatabase();
        return (db != null);
    }


    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(DatabaseHelper.EMPLOYEE_TABLE_NAME);

        switch (uriMatcher.match(uri)){
            case STUDENTS:
                qb.setProjectionMap(STUDENTS_PROJECTION_MAP);
                break;

            case STUDENT_ID:
                qb.appendWhere(_ID + "=" + uri.getPathSegments().get(1));
                break;

            default:
                throw  new IllegalArgumentException("Unknown Uri="+uri);
        }

        if (sortOrder == null || Objects.equals(sortOrder, "")) {
            sortOrder = NAME;
        }

        Cursor cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }


    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case STUDENTS:
                return "vnd.android.cursor.dir/vnd.bintang.id.contentproviderexample_2."+ DatabaseHelper.EMPLOYEE_TABLE_NAME;
            case STUDENT_ID:
                return "vnd.android.cursor.item/vnd.bintang.id.contentproviderexample_2."+ DatabaseHelper.EMPLOYEE_TABLE_NAME;
            default:
                throw new IllegalArgumentException("Unknown URI="+uri);
        }
    }


    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        long rowID = db.insert(DatabaseHelper.EMPLOYEE_TABLE_NAME, "", values);

        if (rowID > 0)
        {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new IllegalArgumentException("Unsupported URI: " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        int count;

        switch (uriMatcher.match(uri)){
            case STUDENTS:
                count = db.delete(DatabaseHelper.EMPLOYEE_TABLE_NAME, selection, selectionArgs);
                break;
            case STUDENT_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(DatabaseHelper.EMPLOYEE_TABLE_NAME, _ID + " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI="+uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count;

        switch (uriMatcher.match(uri)){
            case STUDENTS:
                count = db.update(DatabaseHelper.EMPLOYEE_TABLE_NAME, values, selection, selectionArgs);
                break;
            case STUDENT_ID:
                count = db.update(DatabaseHelper.EMPLOYEE_TABLE_NAME, values, _ID + " = " + uri.getPathSegments().get(1)+
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')': ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI="+uri);
        }


        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
