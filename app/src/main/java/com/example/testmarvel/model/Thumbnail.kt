package com.example.testmarvel.model

import android.os.Parcel
import android.os.Parcelable

 class Thumbnail():Parcelable{

    var path:String? = null
    var extension:String?= null

     constructor(parcel: Parcel) : this() {
         path = parcel.readString()
         extension = parcel.readString()
     }

     override fun writeToParcel(parcel: Parcel, flags: Int) {
         parcel.writeString(path)
         parcel.writeString(extension)
     }

     override fun describeContents(): Int {
         return 0
     }

     companion object CREATOR : Parcelable.Creator<Thumbnail> {
         override fun createFromParcel(parcel: Parcel): Thumbnail {
             return Thumbnail(parcel)
         }

         override fun newArray(size: Int): Array<Thumbnail?> {
             return arrayOfNulls(size)
         }
     }
}