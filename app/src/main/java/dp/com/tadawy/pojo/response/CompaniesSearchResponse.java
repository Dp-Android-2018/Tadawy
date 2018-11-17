package dp.com.tadawy.pojo.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import dp.com.tadawy.pojo.model.Links;
import dp.com.tadawy.pojo.model.LoginResponseContent;

public class CompaniesSearchResponse {

    @SerializedName("data")
    private ArrayList<LoginResponseContent> searchResponses;


    @SerializedName("links")
    private Links links;


    public ArrayList<LoginResponseContent> getSearchResponses() {
        return searchResponses;
    }

    public Links getLinks() {
        return links;
    }
}
