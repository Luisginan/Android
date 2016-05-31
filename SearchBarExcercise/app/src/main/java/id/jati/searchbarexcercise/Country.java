package id.jati.searchbarexcercise;

/**
 * Created by jati on 15/03/2016.
 */
public class Country {
    private String id;
    private String name;

    public static final Country[] countrys = {
            new Country("ID","Indonesia"),
            new Country("EN","English"),
            new Country("MY","Malaysia"),
            new Country("UN","USA"),
            new Country("TW","Taiwan")
    };

    private Country(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public String getCountry()
    {
        return name;
    }

}

