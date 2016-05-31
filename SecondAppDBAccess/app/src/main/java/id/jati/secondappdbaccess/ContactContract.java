package id.jati.secondappdbaccess;

import android.provider.BaseColumns;

/**
 * Created by jati on 22/02/2016.
 */
public final class ContactContract {
    public  ContactContract() {}

    public static abstract  class ContactEntry implements BaseColumns {
        public static final String TABLE_NAME = "contact1";
        public static final String COLUMN_NAME_ENTRY_ID = "id";
        public static final String COLUMN_NAME_FIRSTNAME= "first_name";
        public static final String COLUMN_NAME_LASTNAME= "last_name";
        public static final String COLUMN_NAME_PHONE= "phone";
        public static final String COLUMN_NAME_EMAIL= "email";

    }
}
