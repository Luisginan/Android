package id.jati.secondappdbaccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jati on 22/02/2016.
 */
public class ContactManager {
    public Context context;
    public ContactManager(Context context) {
        this.context=context;
    }

    public int updateContactWithID(Contact contact)
    {
        ContactDbHelper mDbHelper = new ContactDbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactContract.ContactEntry.COLUMN_NAME_FIRSTNAME,contact.first_name);
        contentValues.put(ContactContract.ContactEntry.COLUMN_NAME_LASTNAME,contact.last_name);
        contentValues.put(ContactContract.ContactEntry.COLUMN_NAME_PHONE,contact.phone);
        contentValues.put(ContactContract.ContactEntry.COLUMN_NAME_EMAIL,contact.email);

        String selection = ContactContract.ContactEntry.COLUMN_NAME_ENTRY_ID +"=?";
        String[] selectionArgs={String.valueOf(contact.id)};

        int count = db.update(
                ContactContract.ContactEntry.TABLE_NAME,
                contentValues,
                selection,
                selectionArgs
        );
        return  count;
    }

    public int deleteContactWithID(Contact contact)
    {
        ContactDbHelper mDbHelper = new ContactDbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String selection = ContactContract.ContactEntry.COLUMN_NAME_ENTRY_ID+"=?";
        String[] selectionArgs = {String.valueOf(contact.id)};
        return db.delete(
                ContactContract.ContactEntry.TABLE_NAME,
                selection,
                selectionArgs
        );
    }

    public List<Contact> getAllRows()
    {
        List<Contact> contacs = new ArrayList<Contact>();

        ContactDbHelper mDbHelper = new ContactDbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection =
                {
                        ContactContract.ContactEntry.COLUMN_NAME_ENTRY_ID,
                        ContactContract.ContactEntry.COLUMN_NAME_FIRSTNAME,
                        ContactContract.ContactEntry.COLUMN_NAME_LASTNAME,
                        ContactContract.ContactEntry.COLUMN_NAME_PHONE,
                        ContactContract.ContactEntry.COLUMN_NAME_EMAIL
                };
        String sortOrder = ContactContract.ContactEntry.COLUMN_NAME_FIRSTNAME+" asc";
        Cursor cursor = db.query(
                ContactContract.ContactEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        cursor.moveToFirst();
        do
        {
            Contact contact= new Contact();
            contact.id=Integer.parseInt(cursor.getString((0)));
            contact.first_name=cursor.getString((1));
            contact.last_name=cursor.getString((2));
            contact.phone=cursor.getString((3));
            contact.email=cursor.getString((4));
            contacs.add(contact);

        }while (cursor.moveToNext());

        return contacs;
    }

    public Contact getContactWithID(int id)
    {
        ContactDbHelper mDbHelper = new ContactDbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection =
                {
                        ContactContract.ContactEntry.COLUMN_NAME_ENTRY_ID,
                        ContactContract.ContactEntry.COLUMN_NAME_FIRSTNAME,
                        ContactContract.ContactEntry.COLUMN_NAME_LASTNAME,
                        ContactContract.ContactEntry.COLUMN_NAME_PHONE,
                        ContactContract.ContactEntry.COLUMN_NAME_EMAIL
                };
        String sortOrder = ContactContract.ContactEntry.COLUMN_NAME_FIRSTNAME+" asc";
        String filterName =  ContactContract.ContactEntry.COLUMN_NAME_ENTRY_ID+"=?";

        Cursor cursor = db.query(
                ContactContract.ContactEntry.TABLE_NAME,
                projection,
                filterName,
                new String[] {String.valueOf(id)},
                null,
                null,
                sortOrder
        );
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Contact contact = new Contact();
        contact.id=Integer.parseInt(cursor.getString((0)));
        contact.first_name=cursor.getString((1));
        contact.last_name=cursor.getString((2));
        contact.phone=cursor.getString((3));
        contact.email=cursor.getString((4));

        return contact;
    }

    public void insertContact(Contact contact) throws Exception {
        ContactDbHelper mDbHelper = new ContactDbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ContactContract.ContactEntry.COLUMN_NAME_FIRSTNAME,contact.first_name);
        values.put(ContactContract.ContactEntry.COLUMN_NAME_LASTNAME,contact.last_name);
        values.put(ContactContract.ContactEntry.COLUMN_NAME_PHONE,contact.phone);
        values.put(ContactContract.ContactEntry.COLUMN_NAME_EMAIL,contact.email);

        long newRowid;
        newRowid = db.insert(ContactContract.ContactEntry.TABLE_NAME, null, values);
        if (newRowid == -1 )
        {
            throw new Exception("error insert");
        }
    }

}
