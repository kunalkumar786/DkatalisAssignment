package com.example.dkatalisassignment


import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dkatalisassignment.model.Userinfo
import com.example.dkatalisassignment.util.SwipeCard
import com.example.trigentassignment.view_model.FeedViewModel
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder


class MainActivity : AppCompatActivity() {
    private var mSwipView:SwipePlaceHolderView? = null
    private var mContext :Context? = null
    var feedViewModel: FeedViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        feedViewModel = ViewModelProviders.of(this)[FeedViewModel::class.java]
        mSwipView = findViewById<SwipePlaceHolderView>(R.id.swipeView)
        mContext = applicationContext
        mSwipView?.getBuilder<SwipePlaceHolderView, SwipeViewBuilder<SwipePlaceHolderView>>()
            ?.setDisplayViewCount(3)
            ?.setSwipeDecor(
                SwipeDecor()
                    .setPaddingTop(20)
                    .setRelativeScale(0.01f)
                    .setSwipeInMsgLayoutId(R.layout.accept_view)
                    .setSwipeOutMsgLayoutId(R.layout.reject_view)
            )
        feedViewModel?.getFeedData()?.observe(this,object:Observer<ArrayList<Userinfo>>{
            override fun onChanged(t: ArrayList<Userinfo>?) {
                if (t != null) {
                    if(t.size>0){
                        for (profile in t/*Utils.loadProfiles(mContext)*/!!) {
                            mSwipView?.addView(SwipeCard(mContext!!, profile, mSwipView!!))
                        }
                    }
                }
            }
        })

    findViewById<ImageButton>(R.id.rejectBtn).setOnClickListener{
        mSwipView!!.doSwipe(false)
       }
    findViewById<ImageButton>(R.id.acceptBtn).setOnClickListener{
        mSwipView!!.doSwipe(true)
      }


    }


}