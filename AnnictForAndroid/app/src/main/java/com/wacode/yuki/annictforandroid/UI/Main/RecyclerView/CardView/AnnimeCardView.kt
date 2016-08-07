package com.wacode.yuki.annictforandroid.UI.Main.RecyclerView.CardView

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import butterknife.bindView
import com.wacode.yuki.annictapp.Entities.Work
import com.wacode.yuki.annictapp.Entities.Works
import com.wacode.yuki.annictforandroid.API.AnnictApi
import com.wacode.yuki.annictforandroid.API.BaseAnnictApi
import com.wacode.yuki.annictforandroid.Enum.AnnimeStateType
import com.wacode.yuki.annictforandroid.Enum.MaterialType
import com.wacode.yuki.annictforandroid.Helper.AnnictPrefHelper
import com.wacode.yuki.annictforandroid.R
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.util.*

/**
 * Created by yuki on 2016/07/25.
 */
class AnnimeCardView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val spinner: Spinner by bindView(R.id.spinner)
    private val textView_icon: TextView by bindView(R.id.textView_icon)
    private val textView_kana:TextView by bindView(R.id.textView_kana)
    private val textView_title:TextView by bindView(R.id.textView_title)
    private val textView_season:TextView by bindView(R.id.textView_season)
    private val textView_score:TextView by bindView(R.id.textView_score)

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun bindData(work: Work){
        setText(work)
        setSpinner(work)
    }

    private fun setText(work: Work){
        textView_kana.text = work.title_kana
        textView_title.text = work.title
        textView_season.text = work.season_name_text
        textView_score.text = work.watchers_count.toString()
        createTitleIcon(work.media)
    }

    private fun createTitleIcon(text:String){
        val title = text.substring(0,1)
        val shapeDrawable = ShapeDrawable(OvalShape())
        shapeDrawable.paint.color = context.resources.getColor(MaterialType.values()[Random().nextInt(MaterialType.values().size)].colorResource)

        textView_icon.background = shapeDrawable
        textView_icon.text = title
    }

    private fun setSpinner(work: Work){
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        for(type in AnnimeStateType.values()){
            if(type.equals(AnnimeStateType.INIT)) adapter.add(resources.getString(work.state.textResource)+resources.getString(R.string.spinner_selected))
            else adapter.add(resources.getString(type.textResource))
        }
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 0) {
                    postStatus(work,position)
                }
            }
        }
    }

    private fun postStatus(work:Work,position: Int){
        CompositeSubscription().add(BaseAnnictApi.restClient.create(AnnictApi::class.java).postStatus(accessToken = AnnictPrefHelper.getAccessToken(context),
                id = work.id,
                state = getSpinnerStat(position))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Toast.makeText(context,context.resources.getString(R.string.spinner_changed_success),Toast.LENGTH_SHORT).show()
                },{
                    Toast.makeText(context,context.resources.getString(R.string.spinner_changed_failed),Toast.LENGTH_SHORT).show()
                }))
    }

    private fun getSpinnerStat(position:Int):String{
        return when(position){
            1 -> AnnimeStateType.WANNAWATCH.statText
            2 -> AnnimeStateType.WATCHING.statText
            3 -> AnnimeStateType.WATCHED.statText
            4 -> AnnimeStateType.ONHOLD.statText
            5 -> AnnimeStateType.STOP.statText
            else -> AnnimeStateType.NOSELECT.statText
        }
    }

}