package com.pratiksha.quizgame.db

import android.os.Parcelable
import android.os.Parcel



data class Levels(

    val level : Int,
    val category : String,
    val categoryNo: Int,

):Parcelable {
    constructor(parcel : Parcel):this (
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt()
            )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(level)
        parcel.writeString(category)
        parcel.writeInt(categoryNo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Levels> {
        override fun createFromParcel(parcel: Parcel): Levels {
            return Levels(parcel)
        }

        override fun newArray(size: Int): Array<Levels?> {
            return arrayOfNulls(size)
        }
    }
}


