package image.crystalapps.contentprovidersample.entities

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


class Image(var id :Long? ,
            var uriImage:String?,
          var displayName :String?,
         var date : String?,
        var size :String?,
        var width :String?,
        var height :String?,
        var path :String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(uriImage)
        parcel.writeString(displayName)
        parcel.writeString(date)
        parcel.writeString(size)
        parcel.writeString(width)
        parcel.writeString(height)
        parcel.writeString(path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Image> {
        override fun createFromParcel(parcel: Parcel): Image {
            return Image(parcel)
        }

        override fun newArray(size: Int): Array<Image?> {
            return arrayOfNulls(size)
        }
    }


}