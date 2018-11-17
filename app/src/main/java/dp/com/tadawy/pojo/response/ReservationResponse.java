package dp.com.tadawy.pojo.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import dp.com.tadawy.pojo.model.ReservationContent;

public class ReservationResponse {
    @SerializedName("data")
    private List<ReservationContent> reservations;

    public List<ReservationContent> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationContent> reservations) {
        this.reservations = reservations;
    }
}
