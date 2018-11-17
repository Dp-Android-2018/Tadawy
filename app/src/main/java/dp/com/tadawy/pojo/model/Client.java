package dp.com.tadawy.pojo.model;

import com.google.gson.annotations.SerializedName;

public class Client {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("logo")
    private String logo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
