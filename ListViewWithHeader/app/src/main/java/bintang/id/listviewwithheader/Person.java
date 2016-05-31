package bintang.id.listviewwithheader;

/**
 * Created by bintang on 3/16/2016.
 * Purpose : Class Model
 */
public class Person {


    private int type; // 1 = header, 0 = row
    private String name;
    private String jobTitle;

    public Person(int type, String name, String address) {
        this.type = type;
        this.name = name;
        this.jobTitle = address;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getJobTitle() {
        return jobTitle;
    }
}