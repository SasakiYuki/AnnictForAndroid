package com.wacode.yuki.annictforandroid.Helper

import android.content.Context
import com.wacode.yuki.annictforandroid.Utils.PrefUtils

/**
 * Created by yuki on 2016/07/25.
 */
object  AnnictPrefHelper {
    private val PREF_ACCESSTOKEN = "pref_accessToken"

    fun getAccessToken(context: Context) = PrefUtils[context,PREF_ACCESSTOKEN,""]
    fun storeAccessToken(context: Context,accessToken:String) = PrefUtils.put(context,PREF_ACCESSTOKEN,accessToken)

}