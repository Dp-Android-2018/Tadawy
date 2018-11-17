package dp.com.tadawy.network;


import java.util.function.ToDoubleBiFunction;

import dp.com.tadawy.pojo.request.ChangePasswordRequest;
import dp.com.tadawy.pojo.request.CheckMailRequest;
import dp.com.tadawy.pojo.request.CheckPhoneRequest;
import dp.com.tadawy.pojo.request.ClientRegisterRequest;
import dp.com.tadawy.pojo.request.CodeRequest;
import dp.com.tadawy.pojo.request.CommentRequest;
import dp.com.tadawy.pojo.request.CompanyRegisterRequest;
import dp.com.tadawy.pojo.request.CreateReservationRequest;
import dp.com.tadawy.pojo.request.LoginRequest;
import dp.com.tadawy.pojo.response.CommentResponse;
import dp.com.tadawy.pojo.response.CompaniesSearchResponse;
import dp.com.tadawy.pojo.response.CountryResponse;
import dp.com.tadawy.pojo.response.DefaultResponse;
import dp.com.tadawy.pojo.response.LoginResponse;
import dp.com.tadawy.pojo.response.ReservationResponse;
import dp.com.tadawy.pojo.response.SpecializationResponse;
import dp.com.tadawy.utils.ConfigurationFile;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EndPoints {

    //check mail
    @POST(ConfigurationFile.UrlConstants.CHECK_MAIL)
    Observable<Response<Integer>>checkMail(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body CheckMailRequest mailRequest);

    //login
    @POST(ConfigurationFile.UrlConstants.LOGIN_URL)
    Observable<Response<LoginResponse>> login(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body LoginRequest loginRequest);

    //get countries
    @GET(ConfigurationFile.UrlConstants.COUNTRIES_URL)
    Observable<Response<CountryResponse>>getCountries(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept);

    //client register
    @POST(ConfigurationFile.UrlConstants.CLIENT_REGISTER_URL)
    Observable<Response<LoginResponse>> clientRegister(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body ClientRegisterRequest clientRegisterRequest);

    //check phone
    @POST(ConfigurationFile.UrlConstants.CHECK_PHONE)
    Observable<Response<Integer>>checkPhone(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body CheckPhoneRequest phoneRequest);

    //company search
    @GET(ConfigurationFile.UrlConstants.COMPANY_SEARCH)
    Observable<Response<CompaniesSearchResponse>>companySearch(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Query("country") String country, @Query("city")String city, @Query("specialization") String specialization,@Query("type")String type,@Query("page")String pageId);

    //company comments
    @GET(ConfigurationFile.UrlConstants.GET_COMPANY_COMMENTS)
    Observable<Response<CommentResponse>>companyComment(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token , @Path("id")int id);

    //create comment
    @POST(ConfigurationFile.UrlConstants.CREATE_COMMENT)
    Observable<Response<DefaultResponse>>createComment(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token, @Body CommentRequest request);

    //create reservation
    @POST(ConfigurationFile.UrlConstants.CREATE_CLIENT_RESERVATION)
    Observable<Response<DefaultResponse>>createReservation(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token, @Body CreateReservationRequest request);

    //Reservations
    @GET(ConfigurationFile.UrlConstants.GET_RESERVATION_URL)
    Observable<Response<ReservationResponse>>getReservation(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token , @Path("status") String status);

    //company specialization
    // TODO: 10/27/2018  company specialization
    @GET(ConfigurationFile.UrlConstants.GET_SPECIALIZATION_URL)
    Observable<Response<SpecializationResponse>>getSpecialization(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Query("type") String type);

    // TODO: 10/29/2018 company registration
    @POST(ConfigurationFile.UrlConstants.COMPANY_REGISTER_URL)
    Observable<Response<LoginResponse>>companyRegister(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Body CompanyRegisterRequest registerRequest);

    //TODO: 10/29/2018 send activation mail
    @POST(ConfigurationFile.UrlConstants.SEND_ACTIVATION_MAIL)
    Observable<Response<DefaultResponse>>sendActivationMail(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token);

    //TODO: 10/30/2018 send activation code
    @POST(ConfigurationFile.UrlConstants.SEND_ACTIVATION_CODE)
    Observable<Response<DefaultResponse>>sendActivationCode(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token,@Body CheckPhoneRequest request);

    //TODO: 10/30/2018  activation phone
    @POST(ConfigurationFile.UrlConstants.PHONE_ACTIVATION)
    Observable<Response<DefaultResponse>>activatePhone(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token,@Body CodeRequest request);

    // TODO : 11/5/2018  forget password
    @POST(ConfigurationFile.UrlConstants.FORGET_PASSWORD_URL)
    Observable<Response<DefaultResponse>>forgetPassword(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept,@Body CheckMailRequest request);

    //TODO: 11/6/2018  change password
    @POST(ConfigurationFile.UrlConstants.CHANGE_PASSWORD_URL)
    Observable<Response<DefaultResponse>>changePassword(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token,@Body ChangePasswordRequest request);

    //TODO 11/7/2018  delete request
    @DELETE(ConfigurationFile.UrlConstants.DELETE_RESERVATION_URL)
    Observable<Response<DefaultResponse>>deleteReservation(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token,@Path("id") int id);

    // TODO: 11/10/2018  approve request
    @POST(ConfigurationFile.UrlConstants.DELETE_RESERVATION_URL)
    Observable<Response<DefaultResponse>>approveRequest(@Header("x-api-key") String key, @Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String token,@Path("id") int id,@Query("comment") String comment);
}