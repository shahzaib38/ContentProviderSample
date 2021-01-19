package image.crystalapps.contentprovidersample.entities

import android.os.Parcel
import android.os.Parcelable

class Video(var id :Long? ,
            var uriImage:String?,
            var displayName :String?,
            var date : String?,
            var size :String?,
            var width :String?,
            var height :String?,
            var path :String?,
            var thumb :String?
            ,var duration: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
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
        parcel.writeString(thumb)
        parcel.writeString(duration)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Video> {
        override fun createFromParcel(parcel: Parcel): Video {
            return Video(parcel)
        }

        override fun newArray(size: Int): Array<Video?> {
            return arrayOfNulls(size)
        }
    }
}