package com.winklix.indu.healthcareapp.api;

import com.winklix.indu.healthcareapp.modals.Product_Modal;
import com.winklix.indu.healthcareapp.pojo.AddtoCartPojo;
import com.winklix.indu.healthcareapp.pojo.CategoryPojo;
import com.winklix.indu.healthcareapp.pojo.GetCartPojo;
import com.winklix.indu.healthcareapp.pojo.LocationServicePojo;
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
    @POST("/product.php")
    void GetProductList(@Field("category") String category, @Field("subcategory") String subcategory, Callback<ProductDataPojo> callback);

    @POST("/servicecategory.php")
    void GetServiceCategoryList(@Body Object object, Callback<ServiceCategoryPojo>callback);

    @GET("/addtocart.php")
    void AddtoCart(@Query("productid") String productid, @Query("quantity") String quantity, @Query("price") String price, @Query("size") String size,
                   @Query("session") String session, Callback<AddtoCartPojo> callback);

    @GET("/getcart.php")
    void GetCart(@Query("session") String session, Callback<GetCartPojo>callback);

    @POST("/servicelisting.php")
    void ServiceListing(@Field("category_id") String category_id, @Field("subcategory_id") String subcategory_id, Callback<ServiceListingPojo>callback);

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

}
