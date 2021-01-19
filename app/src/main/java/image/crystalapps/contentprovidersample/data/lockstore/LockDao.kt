package image.crystalapps.contentprovidersample.data.lockstore

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import image.crystalapps.contentprovidersample.entities.LockType


@Dao
interface LockDao {

    @Query("SELECT * FROM lockTypeTable")
    fun getAll(): List<LockType>


    @Insert
    fun insert(users : LockType)


    @Insert
    fun insertAll(vararg users: LockType)

    @Delete
    fun delete(user: LockType)


}