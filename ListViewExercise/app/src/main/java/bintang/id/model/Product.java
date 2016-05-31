package bintang.id.model;

/**
 * Created by bintang on 2/16/2016.
 */
public class Product {
    private int id;
    private String name;
    private double price;

    public Product(){
        super();
    }

    public Product(int id, String name, double price){
        super();
        this.id = id;
        this.name = name;
        this.price = price;
    }

    //Id
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    //Name
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    //Price
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    @Override
    public String toString() {
        return this.id + ". " + this.name + " [$" + this.price + "]";
    }
}
