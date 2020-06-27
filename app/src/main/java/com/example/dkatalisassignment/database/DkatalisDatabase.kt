package com.example.dkatalisassignment.database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.dkatalisassignment.model.Userinfo

private const val TAG="DkatalisDatabase"
private const val DATABASE_NAME="dkatalis.db"
private const val DATABASE_VERSION=3
private var database: SQLiteDatabase?=null

 class DkatalisDatabase private constructor(context:Context):SQLiteOpenHelper(context, DATABASE_NAME,null,
     DATABASE_VERSION){
     override fun onCreate(db: SQLiteDatabase) {
         val sql="""CREATE table ${UserContract.TABLE_NAME}(${UserContract.Columns.ID} INTEGER PRIMARY KEY NOT NULL,${UserContract.Columns.GENDER} TEXT,${UserContract.Columns.NAME} TEXT,${UserContract.Columns.LOCATION},${UserContract.Columns.EMAIL} TEXT,${UserContract.Columns.USERNAME} TEXT,${UserContract.Columns.DOB} TEXT,${UserContract.Columns.MOBILE} TEXT,${UserContract.Columns.PICTURE} TEXT)""".trim()
            database=db
         db.execSQL(sql)
     }

     fun insertData(userinfo:Userinfo) {
         try {
             val inserQuery =
                 """insert into ${UserContract.TABLE_NAME} (${UserContract.Columns.GENDER},${UserContract.Columns.NAME},${UserContract.Columns.LOCATION},${UserContract.Columns.EMAIL},${UserContract.Columns.USERNAME},${UserContract.Columns.DOB},${UserContract.Columns.MOBILE},${UserContract.Columns.PICTURE}
|) values(${userinfo.gender},${userinfo.nameVal},${userinfo.locationVal},${userinfo.email}${userinfo.username}${userinfo.dob}${userinfo.phone}${userinfo.picture});""".trimMargin()

             database?.execSQL(inserQuery)
             Log.d(TAG, "QUERY  executed successfully")

         } catch (E: Exception) {
             E.printStackTrace()
             Log.d(TAG, "Problem in executing Query ${E.printStackTrace()}")
         }
     }

     fun getAllFavouriteData(): ArrayList<Userinfo>?  {
         val FAV_TABLE=UserContract.TABLE_NAME
         val selectQuery = "SELECT  * FROM " + FAV_TABLE;
    val db = this.readableDatabase
        val arrayQuery= arrayOf<String>(UserContract.Columns.GENDER,UserContract.Columns.NAME,UserContract.Columns.LOCATION
                ,UserContract.Columns.EMAIL,UserContract.Columns.USERNAME,UserContract.Columns.DOB,UserContract.Columns.MOBILE
        ,UserContract.Columns.PICTURE
        )
    val cursor=db.query(UserContract.TABLE_NAME,arrayQuery/*arrayQuery*/,null,null,null,null,null)

//    val cursor: Cursor = db.rawQuery(selectQuery, null, null)
    val dataModelArrayList = ArrayList<Userinfo>()
    if (cursor.moveToFirst()) {
        do {
            dataModelArrayList.add(
                Userinfo(
                    cursor.getString(cursor.getColumnIndex(UserContract.Columns.GENDER))
                    , cursor.getString(cursor.getColumnIndex(UserContract.Columns.NAME))
                    , cursor.getString(cursor.getColumnIndex(UserContract.Columns.LOCATION))
                    , cursor.getString(cursor.getColumnIndex(UserContract.Columns.EMAIL))
                    , cursor.getString(cursor.getColumnIndex(UserContract.Columns.USERNAME))
                    , cursor.getString(cursor.getColumnIndex(UserContract.Columns.DOB))
                    , cursor.getString(cursor.getColumnIndex(UserContract.Columns.MOBILE))
                    , cursor.getString(cursor.getColumnIndex(UserContract.Columns.PICTURE))
                )
            )
        } while (cursor.moveToNext())
    }
    cursor.close()
    db.close()

         return dataModelArrayList
     }

     override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
         /*when(oldVersion){

         }*/
     }
     companion object:SingletonHolder<DkatalisDatabase,Context>(::DkatalisDatabase){


     }

 }