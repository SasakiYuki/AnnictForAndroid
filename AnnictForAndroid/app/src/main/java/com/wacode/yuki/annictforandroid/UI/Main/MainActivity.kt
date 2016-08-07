package com.wacode.yuki.annictforandroid.UI.Main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wacode.yuki.annictforandroid.Helper.AnnictPrefHelper
import com.wacode.yuki.annictforandroid.Helper.OAuthHelper
import com.wacode.yuki.annictforandroid.R
import com.wacode.yuki.annictforandroid.UI.OAuth.OAuthActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkOAuthState()
    }

    private fun checkOAuthState(){
       if(OAuthHelper.shouldDoOAuth(this)){
          // Should do OAuth
           startActivity(Intent(this, OAuthActivity::class.java))
       }else{
           //already OAuthed
       }
    }
}
