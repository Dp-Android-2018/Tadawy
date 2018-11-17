package dp.com.tadawy.pojo.response;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {
    @SerializedName("message")
    private String message;

//    @SerializedName("seconds_left")
//    private int secondsLeft;
//
//    public int getSecondsLeft() {
//        return secondsLeft;
//    }

    public String getMessage() {
        return message;
    }
}
