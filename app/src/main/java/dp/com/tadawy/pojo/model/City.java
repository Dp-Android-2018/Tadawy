package dp.com.tadawy.pojo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
* Created by DELL on 18/07/2018.
*/
public class City implements Serializable {
   @SerializedName("id")
   private int id;

   @SerializedName("name")
   private String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
