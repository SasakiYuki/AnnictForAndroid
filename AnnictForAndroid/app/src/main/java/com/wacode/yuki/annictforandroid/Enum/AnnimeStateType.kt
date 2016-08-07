package com.wacode.yuki.annictforandroid.Enum

import com.wacode.yuki.annictforandroid.Helper.AnnictApiHelper
import com.wacode.yuki.annictforandroid.R

/**
 * Created by yuki on 2016/07/25.
 */
enum class AnnimeStateType (val textResource:Int,val statText:String){
    INIT(R.string.spinner_init, AnnictApiHelper.no_data),
    WANNAWATCH(R.string.spinner_wanna,AnnictApiHelper.wanna_watch),
    WATCHING(R.string.spinner_watching,AnnictApiHelper.watching),
    WATCHED(R.string.spinner_watched,AnnictApiHelper.watched),
    ONHOLD(R.string.spinner_hold,AnnictApiHelper.on_hold),
    STOP(R.string.spinner_stop,AnnictApiHelper.stop_watching),
    NOSELECT(R.string.spinner_no_select,AnnictApiHelper.no_data)
}
