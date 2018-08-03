package com.kushnir.githhubclient.view.screens.repositoryDetails.parcelable

import android.os.Parcel
import android.os.Parcelable

data class DetailsParams(val repoName: String, val userName: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(repoName)
        p0?.writeString(userName)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<DetailsParams> {
        override fun createFromParcel(parcel: Parcel): DetailsParams {
            return DetailsParams(parcel)
        }

        override fun newArray(size: Int): Array<DetailsParams?> {
            return arrayOfNulls(size)
        }
    }
}