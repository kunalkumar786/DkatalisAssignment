package com.example.dkatalisassignment.util

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.example.dkatalisassignment.model.Profile
import com.google.gson.GsonBuilder
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream



class Utils{
    companion object{
        private const val TAG:String="Utils"
        fun loadProfiles(context: Context?): List<Profile>? {
            return try {
                val builder = GsonBuilder()
                val gson = builder.create()
                val array = JSONArray(loadJSONFromAsset(context!!, "Profile.json"))
                val profileList: MutableList<Profile> = ArrayList()
                for (i in 0 until array.length()) {
                    val profile: Profile =
                        gson.fromJson<Profile>(array.getString(i), Profile::class.java)
                    profileList.add(profile)
                }
                profileList
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
   fun loadJSONFromAsset(context:Context,jsonFileName:String):String{
    var json: String? =null;
       var inputstream:InputStream?=null;
       try {
        val manager:AssetManager=context.assets
           Log.d(TAG,"path"+jsonFileName);
           inputstream=manager.open(jsonFileName)
           var size:Int=inputstream.available()
           val buffer = ByteArray(size)
           inputstream.read(buffer)
           inputstream.close()
           val charset=Charsets.UTF_8
           json=String(buffer,charset)
       }catch (ex:IOException){
           ex.printStackTrace()
       }
   return json!!
   }


    }
}