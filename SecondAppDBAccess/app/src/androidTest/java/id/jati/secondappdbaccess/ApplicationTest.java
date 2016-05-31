package id.jati.secondappdbaccess;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.test.ApplicationTestCase;

import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testCreateTable()
    {
        ContactDbHelper contactDbHelper = new ContactDbHelper(getContext());
    }

    public void testInsertData() throws Exception {
        ContactManager contactManager=new ContactManager(getContext());
        contactManager.insertContact(new Contact( "Andi", "Kurnia", "085711002200", "andi@info.com"));
    }

    public void testGetRowById()
    {
        ContactManager contactManager = new ContactManager(getContext());
        Contact contact = new Contact();
        contact = contactManager.getContactWithID(1);
        assertEquals(1, contact.id);
    }

    public void testGetAllRows()
    {
        ContactManager contactManager = new ContactManager(getContext());
        List<Contact> contacts;
        contacts = contactManager.getAllRows();
        assertTrue(contacts.size() > 0);
    }

    public void testUpdateWithID()
    {
        int count;
        Contact contact = new Contact();
        contact.id=1;
        contact.email="andi@andi.com";
        ContactManager contactManager = new ContactManager(getContext());
        count=contactManager.updateContactWithID(contact);
        assertEquals(1, count);
    }

    public void testDeleteWithID()
    {
        int count;
        Contact contact = new Contact();
        contact.id=1;
        ContactManager contactManager = new ContactManager(getContext());
        count = contactManager.deleteContactWithID(contact);
        assertEquals(1, count);
    }

}