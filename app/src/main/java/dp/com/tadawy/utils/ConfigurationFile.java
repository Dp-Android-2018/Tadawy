package dp.com.tadawy.utils;

public class ConfigurationFile {

    public static class UrlConstants{
       public static final String BASE_URL="http://dp-itc.com:8888/";
       public static final String COUNTRIES_URL ="api/utilities/countries";
       public static final String CATEGORIES_URL="api/utilities/categories";
       public static final String LOGIN_URL="api/login";
       public static final String CLIENT_REGISTER_URL="api/clients/register";
       public static final String COMPANY_REGISTER_URL="api/companies/register";
       public static final String CHANGE_PASSWORD_URL="api/change-password";
       public static final String FORGET_PASSWORD_URL="api/forget";
       public static final String UPDATE_METADATA="api/companies/company-meta-data";
       public static final String CHECK_MAIL="api/check/email";
       public static final String CHECK_PHONE="api/check/phone";
       public static final String CHECK_ACTIVATION_STATUS="api/activate/check";
       public static final String SEND_ACTIVATION_MAIL="api/activate/email/send";
       public static final String PHONE_ACTIVATION="api/activate/phone";
       public static final String SEND_ACTIVATION_CODE="api/activate/phone/send";
       public static final String CHECK_ACTIVATION_CODE="api/activate/phone/sent";
       public static final String CREATE_CLIENT_RESERVATION="api/clients/reservation";
       public static final String COMPANY_APPROVE_RESERVATION="api/companies/reservation";
       public static final String GET_RESERVATION_URL="api/reservations";
       public static final String GET_RESERVATION_DETAIL_URL="api/reservation/{reservation}";
       public static final String GET_COMPANY_DETAIL="api/company/{id}/data";
       public static final String GET_COMPANY_COMMENTS="api/company/{id}/comments";
       public static final String COMPANY_SEARCH="api/search";
       public static final String CREATE_COMMENT="api/clients/comments";
       }

    public static class Constants{

    }
    public static class SharedPrefConstants{
        public static final String SHARED_PREF_NAME="TADAWAY_SHARED_PREF";
    }
    public static class IntentConstants{

    }


}
