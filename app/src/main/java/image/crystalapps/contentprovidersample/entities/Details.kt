package image.crystalapps.contentprovidersample.entities

import android.os.Parcel
import android.os.Parcelable

class Details(var title :String? ,var date :String? ,var resolution : String? ,var size  :String? , var path:String?) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(date)
        parcel.writeString(resolution)
        parcel.writeString(size)
        parcel.writeString(path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Details> {
        override fun createFromParcel(parcel: Parcel): Details {
            return Details(parcel)
        }

        override fun newArray(size: Int): Array<Details?> {
            return arrayOfNulls(size)
        }
    }
}