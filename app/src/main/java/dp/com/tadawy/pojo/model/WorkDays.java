package dp.com.tadawy.pojo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WorkDays  implements Serializable {

    @SerializedName("saturday")
    private FullDay saturday;

    @SerializedName("sunday")
    private FullDay sunday;

    @SerializedName("monday")
    private FullDay monday;

    @SerializedName("tuesday")
    private FullDay tuesday;

    @SerializedName("wednesday")
    private FullDay wednesday;

    @SerializedName("thursday")
    private FullDay thursday;

    @SerializedName("friday")
    private FullDay friday;

    public FullDay getSaturday() {
        return saturday;
    }

    public void setSaturday(FullDay saturday) {
        this.saturday = saturday;
    }

    public FullDay getSunday() {
        return sunday;
    }

    public void setSunday(FullDay sunday) {
        this.sunday = sunday;
    }

    public FullDay getMonday() {
        return monday;
    }

    public void setMonday(FullDay monday) {
        this.monday = monday;
    }

    public FullDay getTuesday() {
        return tuesday;
    }

    public void setTuesday(FullDay tuesday) {
        this.tuesday = tuesday;
    }

    public FullDay getWednesday() {
        return wednesday;
    }

    public void setWednesday(FullDay wednesday) {
        this.wednesday = wednesday;
    }

    public FullDay getThursday() {
        return thursday;
    }

    public void setThursday(FullDay thursday) {
        this.thursday = thursday;
    }

    public FullDay getFriday() {
        return friday;
    }

    public void setFriday(FullDay friday) {
        this.friday = friday;
    }
}
