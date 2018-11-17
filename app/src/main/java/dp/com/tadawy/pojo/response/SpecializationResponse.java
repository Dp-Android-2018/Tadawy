package dp.com.tadawy.pojo.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import dp.com.tadawy.pojo.model.Specialization;

public class SpecializationResponse {
    @SerializedName("data")
    private List<Specialization> specializations;

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }
}
