package dp.com.tadawy.pojo.model;

import com.google.gson.annotations.SerializedName;

public class FullDay {
    @SerializedName("morning")
    private Shift morning;

    @SerializedName("night")
    private Shift night;

    public FullDay(Shift morning, Shift night) {
        this.morning = morning;
        this.night = night;
    }

    public Shift getMorning() {
        return morning;
    }

    public void setMorning(Shift morning) {
        this.morning = morning;
    }

    public Shift getNight() {
        return night;
    }

    public void setNight(Shift night) {
        this.night = night;
    }
}
