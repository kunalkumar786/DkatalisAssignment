package com.example.dkatalisassignment.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Profile {
    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("url")
    @Expose
    private var imageUrl: String? = null

    @SerializedName("age")
    @Expose
    private var age: Int? = null

    @SerializedName("location")
    @Expose
    private var location: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getImageUrl(): String? {
        return imageUrl
    }

    fun setImageUrl(imageUrl: String?) {
        this.imageUrl = imageUrl
    }

    fun getAge(): Int? {
        return age
    }

    fun setAge(age: Int?) {
        this.age = age
    }

    fun getLocation(): String? {
        return location
    }

    fun setLocation(location: String?) {
        this.location = location
    }
}