package image.crystalapps.contentprovidersample.data.lockstore

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import image.crystalapps.contentprovidersample.entities.LockType

private val DATABASE_NAME = "lockDataBase"


@Database(entities = [LockType::class], version = 1, exportSchema = false)
abstract class LockDataBase : RoomDatabase() {
    abstract fun lockTypeDao(): LockType

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: LockDataBase? = null

        fun getInstance(context: Context): LockDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): LockDataBase {
            return Room.databaseBuilder(context, LockDataBase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                        }
                    }
                )
                .build()
        }
    }
}