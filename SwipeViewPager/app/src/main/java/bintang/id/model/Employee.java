/**
 * Created by bintang on 3/15/2016.
 * Model class
 */

package bintang.id.model;

public class Employee{

    private int no;
    private String name;
    private String jobTitle;

    public Employee(int no, String name, String jobTitle) {
        this.no = no;
        this.name = name;
        this.jobTitle = jobTitle;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public String getJobTitle() {
        return jobTitle;
    }
}


