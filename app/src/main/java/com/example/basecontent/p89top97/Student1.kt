package com.example.basecontent.p89top97

import android.os.Parcel
import android.os.Parcelable

data class Student1(val name: String?, val age: Int) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student1> {
        override fun createFromParcel(parcel: Parcel): Student1 {
            return Student1(parcel)
        }

        override fun newArray(size: Int): Array<Student1?> {
            return arrayOfNulls(size)
        }
    }
}