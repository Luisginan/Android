package id.jati.secondappdbaccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by jati on 22/02/2016.
 */
public class ContactDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =2;
    public static final String DATABASE_NAME = "contact1.db";

    private static final String TEXT_TYPE = " TEXT ";
    private static final String COMMA_SEP =",";
    private static final String SQL_CREATE_ENTRIES =
            " CREATE TABLE "+ ContactContract.ContactEntry.TABLE_NAME+"("+
                    ContactContract.ContactEntry.COLUMN_NAME_ENTRY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT"+COMMA_SEP+
                    ContactContract.ContactEntry.COLUMN_NAME_FIRSTNAME+TEXT_TYPE+COMMA_SEP+
                    ContactContract.ContactEntry.COLUMN_NAME_LASTNAME+TEXT_TYPE+COMMA_SEP+
                    ContactContract.ContactEntry.COLUMN_NAME_PHONE+TEXT_TYPE+COMMA_SEP+
                    ContactContract.ContactEntry.COLUMN_NAME_EMAIL+TEXT_TYPE+
                    ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS "+ContactContract.ContactEntry.TABLE_NAME;

    public ContactDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion)
    {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db,int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }

}
