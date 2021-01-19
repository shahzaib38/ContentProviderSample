package image.crystalapps.contentprovidersample.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "lockTypeTable")
data class LockType(@PrimaryKey val uId :Int
                    ,@ColumnInfo(name = "name") val  name :String
                    ,@ColumnInfo(name= "lockType") val  lockType :String
                    , @ColumnInfo(name = "status") val status :Boolean)