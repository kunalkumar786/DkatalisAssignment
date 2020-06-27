package com.example.dkatalisassignment.util


import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.dkatalisassignment.R
import com.example.dkatalisassignment.database.DkatalisDatabase
import com.example.dkatalisassignment.model.Profile
import com.example.dkatalisassignment.model.Userinfo
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.swipe.*


@Layout(com.example.dkatalisassignment.R.layout.contact_item)
class SwipeCard(context: Context, profile:Userinfo, swipeView: SwipePlaceHolderView) {

    private val TAG:String="SwipeCard"

    @View(R.id.iv_profile)
    private val profileImageView: ImageView? = null

    @View(R.id.tv_address_val)
    private val nameAgeTxt: TextView? = null

  /*  @View(R.id.locationNameTxt)
    private val locationNameTxt: TextView? = null*/
    private val mProfile: Userinfo
    private val mContext: Context
    private val mSwipeView: SwipePlaceHolderView
    private var appdatabase:DkatalisDatabase?=null

    @Resolve
    private fun onResolved() {

        if (profileImageView != null) {
        if(mProfile.getImageUrl()==null){
            Glide.with(mContext).load(mProfile.picture).into(profileImageView)
        }else {

            Glide.with(mContext).load(mProfile.getImageUrl()).into(profileImageView)
        }
        }
        if(mProfile.getName()==null&&mProfile.getAge()==null){
            nameAgeTxt?.setText(mProfile.locationVal.toString() + ", ")
        }else {
            nameAgeTxt?.setText(mProfile.getName().toString() + ", " + mProfile.getAge())
        }
            appdatabase=DkatalisDatabase.getInstance(mContext)

        Log.d(TAG,mProfile.getName().toString() + ", " + mProfile.getAge())
        //locationNameTxt.setText(mProfile.getLocation())
    }

    @SwipeOut
    private fun onSwipedOut() {
        mSwipeView.addView(this)
    }

    @SwipeCancelState
    private fun onSwipeCancelState() {



    }

    @SwipeIn
    private fun onSwipeIn() {
        appdatabase?.insertData(mProfile)
    }

    @SwipeInState
    private fun onSwipeInState() {

        Log.d("EVENT", "onSwipeInState")

    }

    @SwipeOutState
    private fun onSwipeOutState() {

        Log.d("EVENT", "onSwipeOutState")

    }

    init {
        mContext = context
        mProfile = profile
        mSwipeView = swipeView
    }
}