package com.pratiksha.quizgame.classes

import android.os.Parcelable
import android.os.Parcel

data class PreviousLevels(
    val correctAnswer : Int ,
    val levelNo : String?
): Parcelable {

    constructor(parcel : Parcel):this (
        parcel.readInt(),
        parcel.readString(),

    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(correctAnswer)
        parcel.writeString(levelNo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PreviousLevels> {
        override fun createFromParcel(parcel: Parcel): PreviousLevels {
            return PreviousLevels(parcel)
        }

        override fun newArray(size: Int): Array<PreviousLevels?> {
            return arrayOfNulls(size)
        }
    }
}
