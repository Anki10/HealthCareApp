package com.winklix.indu.healthcareapp.api;

import com.winklix.indu.healthcareapp.modals.Product_Modal;
import com.winklix.indu.healthcareapp.pojo.AddtoCartPojo;
import com.winklix.indu.healthcareapp.pojo.CategoryPojo;
import com.winklix.indu.healthcareapp.pojo.GetCartPojo;
import com.winklix.indu.healthcareapp.pojo.HomeVisitDiseasesPojo;
import com.winklix.indu.healthcareapp.pojo.HomeVisitorBookingsPojo;
import com.winklix.indu.healthcareapp.pojo.HomeVisitorLeadStatusPojo;
import com.winklix.indu.healthcareapp.pojo.HomevisitTypeListingPojo;
import com.winklix.indu.healthcareapp.pojo.HomevisitcategoryPojo;
import com.winklix.indu.healthcareapp.pojo.HomevisitorlocationDataPojo;
import com.winklix.indu.healthcareapp.pojo.HomevisitorlocationPojo;
import com.winklix.indu.healthcareapp.pojo.LocationServicePojo;
import com.winklix.indu.healthcareapp.pojo.LoginResponsePojo;
import com.winklix.indu.healthcareapp.pojo.PaymentResponsePojo;
import com.winklix.indu.healthcareapp.pojo.ProductDataPojo;
import com.winklix.indu.healthcareapp.pojo.RegisterPojo;
import com.winklix.indu.healthcareapp.pojo.ServiceCategoryPojo;
import com.winklix.indu.healthcareapp.pojo.ServiceDescriptionPojo;
import com.winklix.indu.healthcareapp.pojo.ServiceListingPojo;
import com.winklix.indu.healthcareapp.pojo.ServiceNamePojo;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Administrator on 1/17/2018.
 */

public interface Api {


    @GET("/category.php")
    void category(Callback<CategoryPojo> callback);

    @FormUrlEncoded
    @POST("/service_login.php")
    void Login(@Field("email") String email, @Field("password") String password, @Field("role") String role, @Field("device_id") String device_id, Callback<LoginResponsePojo>callback);

    @FormUrlEncoded
    @POST("/product.php")
    void GetProductList(@Field("category") String category, @Field("subcategory") String subcategory, Callback<ProductDataPojo> callback);

    @POST("/servicecategory.php")
    void GetServiceCategoryList(@Body Object object, Callback<ServiceCategoryPojo>callback);

    @GET("/addtocart.php")
    void AddtoCart(@Query("productid") String productid, @Query("quantity") String quantity, @Query("price") String price, @Query("size") String size,
                   @Query("session") String session, Callback<AddtoCartPojo> callback);

    @GET("/getcart.php")
    void GetCart(@Query("session") String session, Callback<GetCartPojo>callback);

    @FormUrlEncoded
    @POST("/servicelisting.php")
    void ServiceListing(@Field("category_id") String category_id, @Field("subcategory_id") String subcategory_id,@Field("service_name") String service_name, Callback<ServiceListingPojo>callback);

    @FormUrlEncoded
    @POST("/register.php")
    void Register(@Field("name") String name, @Field("email") String email, @Field("phone") String phone, @Field("password")String password, Callback<RegisterPojo>callback);

    @FormUrlEncoded
    @POST("/serviceprice.php")
    void ServicePrice(@Field("category_id") String category_id, @Field("subcategory_id") String subcategory_id, @Field("service_type") String service_type, Callback<ServiceListingPojo>callback);

    @FormUrlEncoded
    @POST("/serviceDescription.php")
    void ServiceDescription(@Field("category_id") String category_id, @Field("subcategory_id") String subcategory_id, @Field("service_type") String service_type, Callback<ServiceDescriptionPojo> callback);

    @FormUrlEncoded
    @POST("/service_name.php")
    void ServiceName(@Field("category_id") String category_id, @Field("subcategory_id") String subcategory_id, Callback<ServiceNamePojo> callback);

    @FormUrlEncoded
    @POST("/locationServices.php")
    void LocationService(@Field("category_id") String category_id, @Field("subcategory_id") String subcategory_id, @Field("latitude") String latitude, @Field("longitude") String  longitude, Callback<LocationServicePojo> callback);

    @POST("/homevisitcategory.php")
    void homevisit(@Body Object object, Callback<HomevisitcategoryPojo> callback);

    @FormUrlEncoded
    @POST("/homeVisitDiseases.php")
    void homeVisitDiseases(@Field("category_id") String category_id, Callback<HomeVisitDiseasesPojo>callback);

    @FormUrlEncoded
    @POST("/homeVisitTypeListing.php")
    void homevisitTypeListing(@Field("category_id") String category_id, @Field("service_name") String service_name, Callback<HomevisitTypeListingPojo> callback);

    @FormUrlEncoded
    @POST("/notification.php")
    void PaymentResponse(@Field("category_id") String category_id, @Field("latitude") String latitude, @Field("longitude") String longitude, @Query("amount") String amount, @Query("patient_id") String patient_id, @Field("address") String address, Callback<PaymentResponsePojo> callback);

    @FormUrlEncoded
    @POST("/homevisitorlocation.php")
    void HomeVisitLocation(@Field("category_id") String category_id, @Field("latitude") String latitude, @Field("longitude") String longitude, Callback<HomevisitorlocationPojo> callback);

    @FormUrlEncoded
    @POST("/update_homeVisitor_leadStatus.php")
    void HomeVisitor(@Field("booking_id") String booking_id, @Field("home_visitor_id") String home_visitor_id, Callback<HomeVisitorLeadStatusPojo>callback);

    @FormUrlEncoded
    @POST("/homeVisitorBookings.php")
    void HomeVisitorBooking(@Field("home_visitor_id") String home_visitor_id, Callback<HomeVisitorBookingsPojo>callback);

}
