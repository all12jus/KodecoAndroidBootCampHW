package com.kodeco.android.countryinfo.types

import android.os.Parcel
import android.os.Parcelable

//@Parcelize
class CountryInfoState() : Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountryInfoState> {
        override fun createFromParcel(parcel: Parcel): CountryInfoState {
            return CountryInfoState(parcel)
        }

        override fun newArray(size: Int): Array<CountryInfoState?> {
            return arrayOfNulls(size)
        }
    }
}