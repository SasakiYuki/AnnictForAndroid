package com.wacode.yuki.annictforandroid.Entities

/**
 * Created by yuki on 2016/07/25.
 */
data class Token(val access_token: String,
                 val token_type: String,
                 val expires_in: Int,
                 val scope: String,
                 val created_at: Int)
