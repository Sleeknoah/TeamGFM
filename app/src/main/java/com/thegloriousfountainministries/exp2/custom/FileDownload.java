package com.thegloriousfountainministries.exp2.custom;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by My HP on 12/21/2018.
 */

public interface FileDownload {
    @Streaming
    @GET("images/events/carousel/encounter.jpg")
    Call<ResponseBody> downloadFile();
}
