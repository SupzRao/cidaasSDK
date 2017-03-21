package com.cidaassdk;


import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Suprada on 08-Mar-17.
 */

public interface ICidaasAPI {


    @POST
    Observable<LoginEntity> getAccessTokenApi1(@Url String url, @Header("Content-Type") String content_type,
                                               @Query("client_id") String client_id,
                                               @Query("redirect_uri") String redirect_uri,
                                               @Query("code") String code,
                                               @Query("client_secret") String client_secret,
                                               @Query("grant_type") String grant_type);
}
