package id.jati.tabletui_second;

/**
 * Created by jati on 11/03/2016.
 * Kelas yang berisi data dan detail data.
 */
public class Workout {
    private String name;
    private String description;

    public static final Workout[] workouts = {
      new Workout("A","aaa aaa aaa \naaa aaa aaa \naaa aaa aaa"),
      new Workout("B","bbb bbb bbb \nbbb bbb bbb \nbbb bbb bbb"),
      new Workout("C","ccc ccc ccc"),
      new Workout("D","ddd ddd ddd")
    };

    private Workout(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public  String getDescription()
    {
        return description;
    }

    public String toString()
    {
        return this.name;
    }


}
