package com.example.testmarvel.model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable

 class Comic():Parcelable {

    var id: Int? = null
    var title:String? = null
    var thumbnail:Thumbnail? = null
    var description:String? = null

     constructor(parcel: Parcel) : this() {
         id = parcel.readInt()
         title = parcel.readString()
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
             thumbnail = parcel.readParcelable(Thumbnail::class.java.classLoader,Thumbnail::class.java)
         }
         else{
             thumbnail = parcel.readParcelable(Thumbnail::class.java.classLoader)
         }
         description = parcel.readString()
     }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        id?.let { parcel.writeInt(it) }
        parcel.writeString(title)
        parcel.writeParcelable(thumbnail, flags)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comic> {
        override fun createFromParcel(parcel: Parcel): Comic {
            return Comic(parcel)
        }

        override fun newArray(size: Int): Array<Comic?> {
            return arrayOfNulls(size)
        }
    }
}