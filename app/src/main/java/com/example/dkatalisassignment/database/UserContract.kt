package com.example.dkatalisassignment.database

import android.provider.BaseColumns

object UserContract {
internal const val TABLE_NAME="favourite"
    object Columns{
    const val ID=BaseColumns._ID
        const val GENDER="gender"
    const val NAME="name"
    const val EMAIL="email"
    const val LOCATION="location"
    const val USERNAME="username"
    const val DOB="dob"
    const val MOBILE="mobile"
    const val PICTURE="picture"
    }


}