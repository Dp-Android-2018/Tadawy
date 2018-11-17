package dp.com.tadawy.pojo.response;

import com.google.gson.annotations.SerializedName;

import dp.com.tadawy.pojo.model.LoginResponseContent;

public class LoginResponse{
    @SerializedName("data")
    private LoginResponseContent loginResponseContent;

    public LoginResponseContent getLoginResponseContent() {
        return loginResponseContent;
    }

    public void setLoginResponseContent(LoginResponseContent loginResponseContent) {
        this.loginResponseContent = loginResponseContent;
    }
}
