package dp.com.tadawy.pojo.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import dp.com.tadawy.pojo.model.Country;

public class CountryResponse {

    @SerializedName("data")
    private List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }
}
