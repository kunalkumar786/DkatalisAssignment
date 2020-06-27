package com.example.trigentassignment.view_model

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dkatalisassignment.model.Userinfo
import com.example.dkatalisassignment.network.NetworkClient

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FeedViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG:String="FeedViewModel"
     var feed_data: MutableLiveData<ArrayList<Userinfo>>? = null

    fun FeedViewModel(application: Application) {
    }

    fun getFeedData(): MutableLiveData<ArrayList<Userinfo>> {
        if (feed_data == null) {
            feed_data = MutableLiveData<ArrayList<Userinfo>>()
            callFeedApi()
        }
        return feed_data as MutableLiveData<ArrayList<Userinfo>>
    }

    fun callFeedApi() {
        NetworkClient.NetworkObject.getNetworkInstance()
        val networkClient= NetworkClient.NetworkObject.getApiClient()
        networkClient?.callAPIExecutor()?.enqueue(object:Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(TAG,call.toString())
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                try {
                    val jsonObject = JSONObject(response.body()!!.string())
                    val jsonArray = jsonObject.getJSONArray("results")

                    if (jsonArray != null && jsonArray.length() > 0) {
                        val feed_result = ArrayList<Userinfo>()

                        for (i in 0 until jsonArray.length()) {

                            /*    UserContract.Columns.GENDER},${UserContract.Columns.NAME},${UserContract.Columns.EMAIL},${UserContract.Columns.USERNAME}
         ,${UserContract.Columns.DOB},${UserContract.Columns.MOBILE},${UserContract.Columns.PICTURE
         */

                        val jsonResult:JSONObject= jsonArray.get(i) as JSONObject
                            val userJson=jsonResult.getJSONObject("user")
                            val nameJson=userJson.getJSONObject("name")
                             val locatioJson=userJson.getJSONObject("location")
                            val location_enhance="${locatioJson.getString("state")}${locatioJson.getString("zip")}"
                              val location="${locatioJson.getString("street")}${locatioJson.getString("city")}"
                             val name="${nameJson.getString("title")}${nameJson.getString("first")}${nameJson.getString("last")}"

                            feed_result.add(Userinfo(userJson.getString("gender"),name,location,userJson.getString("email")
                                ,userJson.getString("username"),userJson.getString("dob"),userJson.getString("phone"),userJson.getString("picture")))

                        }
                        feed_data?.value = feed_result
                        if(feed_result.size>0) {

                        }else{
                            Toast.makeText(getApplication(),"No data received",Toast.LENGTH_LONG).show()

                        }
                        }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        })
    }


}
