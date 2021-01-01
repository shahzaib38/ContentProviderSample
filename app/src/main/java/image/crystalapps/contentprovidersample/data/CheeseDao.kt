package image.crystalapps.contentprovidersample.data

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update





@Dao
interface CheeseDao {



    @Query("SELECT COUNT(*) FROM " + Cheese.TABLE_NAME)
    fun count() :Int


    @Insert
    fun insert(cheese :Cheese) :Long

    @Insert
    fun insertAll(cheeses: Array<Cheese>): LongArray


    @Query("SELECT * FROM " + Cheese.TABLE_NAME)
    fun selectAll(): Cursor

    @Query("SELECT * FROM " + Cheese.TABLE_NAME + " WHERE " + Cheese.COLUMN_ID + " = :id")
    fun selectById(id: Long): Cursor?


    @Query("DELETE FROM " + Cheese.TABLE_NAME + " WHERE " + Cheese.COLUMN_ID + " = :id")
    fun deleteById(id: Long): Int

    @Update
    fun update(cheese: Cheese): Int

}