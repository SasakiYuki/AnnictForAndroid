package com.wacode.yuki.annictforandroid.Helper

import android.content.Context
import com.wacode.yuki.annictforandroid.Utils.PrefUtils

/**
 * Created by yuki on 2016/07/25.
 */
object OAuthHelper {
    private val PREFKEY_SHOULDOAUTH ="pref_shouldOAuth"

    const val BASEURL = "https://annict.com/oauth/authorize"
    const val Q_CLIENTID = "client_id"
    const val Q_CLIENTSECRET = "client_secret"
    const val Q_GRANTTYPE = "grant_type"
    const val Q_CODE = "code"
    const val Q_REDIRECTURL = "redirect_uri"
    const val Q_RESPONSETYPE = "response_type"
    const val Q_SCOPE = "scope"

    const val CLIENTID = "023b69e2a524e5cecc17843b9d2dac709a0a92884805ccb85835746f4f1bd911"
    const val CLIENTSECRET = "953020d78518183622ccd55ede691a85ea7183517fd9bf488197c29074c04a87"
    const val REDIRECTURL = "urn:ietf:wg:oauth:2.0:oob"
    const val RESPONSETYPE ="code"
    const val SCOPE = "read+write"
    const val GRANTTYPE = "authorization_code"

    val builder:String
        get() = "$BASEURL?$Q_CLIENTID=$CLIENTID&$Q_REDIRECTURL=$REDIRECTURL&$Q_RESPONSETYPE=$RESPONSETYPE&$Q_SCOPE=$SCOPE"

    fun shouldDoOAuth(context: Context) = PrefUtils[context, PREFKEY_SHOULDOAUTH,true]
    fun setOAuthedState(context: Context) {
        PrefUtils.put(context, OAuthHelper.PREFKEY_SHOULDOAUTH,false)
    }
}