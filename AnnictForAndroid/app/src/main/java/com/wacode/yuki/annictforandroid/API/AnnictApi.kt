package com.wacode.yuki.annictforandroid.API

import com.wacode.yuki.annictapp.Entities.Works
import com.wacode.yuki.annictforandroid.Entities.Token
import com.wacode.yuki.annictforandroid.Helper.AnnictApiHelper
import com.wacode.yuki.annictforandroid.Helper.OAuthHelper
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable

/**
 * Created by yuki on 2016/07/25.
 */
interface AnnictApi {
    @POST("oauth/token")
    fun getToken(@Query(OAuthHelper.Q_CLIENTID) clientId: String = OAuthHelper.CLIENTID,
                 @Query(OAuthHelper.Q_CLIENTSECRET) clientSecret: String = OAuthHelper.CLIENTSECRET,
                 @Query(OAuthHelper.Q_GRANTTYPE) grantType: String = OAuthHelper.GRANTTYPE,
                 @Query(OAuthHelper.Q_REDIRECTURL) redirectUri: String = OAuthHelper.REDIRECTURL,
                 @Query(OAuthHelper.Q_CODE) code: String): Observable<Token>

    @POST("/v1/me/statuses")
    fun postStatus(@Query(AnnictApiHelper.Q_ACCESS_TOKEN) accessToken: String,
                   @Query(AnnictApiHelper.Q_WORK_ID) id:String,
                   @Query(AnnictApiHelper.Q_KIND) state:String):Observable<Unit>
}