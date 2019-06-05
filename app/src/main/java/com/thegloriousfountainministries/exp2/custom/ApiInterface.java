package com.thegloriousfountainministries.exp2.custom;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by My HP on 6/21/2018.
 */

public interface ApiInterface {
    @GET("converse.php")
    Call<ChatClas> authenticate(
                                 @Query("user1") String user_id1,
                                 @Query("user2") String user_id,
                                 @Query("message") String message);

    @GET("converse2.php")
    Call<List<ChatClass>> authenticate(@Query("user1") String user_id1,
                                       @Query("user2") String user_id);

    @GET("reg.php")
    Call<Login> authenticate2(@Query("fullname") String name,
                              @Query("email") String email,
                              @Query("username") String username,
                              @Query("password") String pass,
                              @Query("token") String token);
    @GET("log.php")
    Call<Login> authenticate3(@Query("username") String username,
                              @Query("password") String pass);
    @GET("blog.php")
    Call<List<BlogModel>> blog();

    @GET("store/albums.php")
    Call<List<BooksModel>> albums();

    @GET("store/add.php")
    Call<AddCart> add(@Query("user_id") String id,
                      @Query("title") String title,
                      @Query("type") String type,
                      @Query("price") String price,
                      @Query("image") String image,
                      @Query("dataurl") String url);
    @GET("store/getcart.php")
    Call<AddCart> order(@Query("user_id") String id,
                      @Query("trans_id") String tid,
                      @Query("status") String status);

    @GET("store/paid.php")
    Call<List<PaidModel>> paid(@Query("user_id") String id
                                            );

    @GET("store/delete.php")
    Call<DeleteCart> add(@Query("user_id") String id,
                      @Query("title") String title);

    @GET("store/tracks.php")
    Call<List<TrackModel>> track(@Query("title") String title);

    @GET("store/explore.php")
    Call<List<Imagess>> getImages();

    @GET("event.php")
    Call<List<EventModel>> event();
}


