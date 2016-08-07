package com.wacode.yuki.annictforandroid.UI.OAuth

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import butterknife.bindView
import com.wacode.yuki.annictforandroid.API.AnnictApi
import com.wacode.yuki.annictforandroid.API.BaseAnnictApi
import com.wacode.yuki.annictforandroid.Helper.AnnictPrefHelper
import com.wacode.yuki.annictforandroid.Helper.OAuthHelper
import com.wacode.yuki.annictforandroid.UI.Main.MainActivity

import com.wacode.yuki.annictforandroid.R
import com.wacode.yuki.annictforandroid.Utils.PrefUtils
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class OAuthActivity : AppCompatActivity() {
    private val button_login by bindView<Button>(R.id.button_login)
    private val button_webView by bindView<Button>(R.id.button_webview)
    private val editText by bindView<EditText>(R.id.editText)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oauth)
        setOnClickListener()
        setToolbar()
    }

    private fun setToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        toolbar.title = resources.getString(R.string.app_name)
        setSupportActionBar(toolbar)
    }


    private fun setOnClickListener(){
        button_webView.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse(OAuthHelper.builder)))
        }
        button_login.setOnClickListener {
            startOAuth(editText.text.toString())
        }
    }

    private fun startOAuth(text: String){
        checkText(text)
    }

    private fun checkText(text:String){
        if(editText.text.toString().equals("")){
            Toast.makeText(this,resources.getString(R.string.oauth_nullCode),Toast.LENGTH_SHORT).show()
        }else {
            val dialog =ProgressDialog(this)
            dialog.setCancelable(false)
            dialog.show()
            startApi(dialog)
        }
    }

    private fun startApi(dialog: ProgressDialog){
        CompositeSubscription().add(BaseAnnictApi.restClient.create(AnnictApi::class.java).getToken(code = editText.text.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    dialog.dismiss()
                    AnnictPrefHelper.storeAccessToken(this,it.access_token)
                    OAuthHelper.setOAuthedState(this)
                    finish() // TODO Refresh MainActivity
                },{
                    Toast.makeText(this,resources.getString(R.string.oauth_failed),Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }))
    }
}
