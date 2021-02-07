package com.sthoksdevs.sthoks.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitSendInterface {
    @FormUrlEncoded
    @POST("messages")
    Call<ResponseBody> sendEmail(@Field("from") String str, @Field("to") String str2, @Field("subject") String str3, @Field("text") String str4);
}
