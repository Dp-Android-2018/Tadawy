package dp.com.tadawy.pojo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class Country implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("cities")
    private List<City> cities;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<City> getCities() {
        return cities;
    }
}
