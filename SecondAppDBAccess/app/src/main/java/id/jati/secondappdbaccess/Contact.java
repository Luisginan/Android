package id.jati.secondappdbaccess;

/**
 * Created by jati on 22/02/2016.
 */
public class Contact {
    public int id;
    public String first_name;
    public String last_name;
    public String phone;
    public String email;

    public Contact() {

    }

    public Contact( String first_name, String last_name, String phone, String email)
    {
        this.id = id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.phone=phone;
        this.email=email;
    }
}