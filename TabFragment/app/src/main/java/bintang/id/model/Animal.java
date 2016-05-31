package bintang.id.model;

/**
 * Created by bintang on 3/10/2016.
 */

public class Animal {
    private int imageNumber;
    private String animalName;


    public Animal(int productImage, String productName) {
        this.imageNumber = productImage;
        this.animalName = productName;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public String getAnimalName() {
        return animalName;
    }

}

