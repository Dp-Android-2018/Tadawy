package dp.com.tadawy.pojo.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import dp.com.tadawy.pojo.model.SocialNetwork;
import dp.com.tadawy.pojo.model.WorkDays;

public class CompanyRegisterRequest implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @SerializedName("password")
    private String password;

    @SerializedName("password_confirmation")
    private String passwordConfirmation;

    @SerializedName("phone")
    private String phone;

    @SerializedName("description")
    private String description;

    @SerializedName("city_id")
    private int cityId;

    @SerializedName("specialization_id")
    private int specializationId;

    @SerializedName("type")
    private String type;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    @SerializedName("website")
    private String website;

    @SerializedName("license_image")
    private String licenseImage;

    @SerializedName("images")
    private List<String>images;

    @SerializedName("social_networks")
    private SocialNetwork socialNetwork;

    @SerializedName("logo")
    private String logo;

    @SerializedName("insurance_companies")
    private List<String> insuranceCompanies;

    @SerializedName("address")
    private String address;

    @SerializedName("work_days")
    private WorkDays workDays;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(int specializationId) {
        this.specializationId = specializationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLicenseImage() {
        return licenseImage;
    }

    public void setLicenseImage(String licenseImage) {
        this.licenseImage = licenseImage;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public SocialNetwork getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(SocialNetwork socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<String> getInsuranceCompanies() {
        return insuranceCompanies;
    }

    public void setInsuranceCompanies(List<String> insuranceCompanies) {
        this.insuranceCompanies = insuranceCompanies;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public WorkDays getWorkDays() {
        return workDays;
    }

    public void setWorkDays(WorkDays workDays) {
        this.workDays = workDays;
    }
}
