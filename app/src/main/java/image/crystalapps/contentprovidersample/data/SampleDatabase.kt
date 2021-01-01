package image.crystalapps.contentprovidersample.data

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Cheese::class] ,version = 1)
public abstract class SampleDatabase : RoomDatabase() {


    abstract fun cheese(): CheeseDao

    /** The only instance  */

    /**
     * Gets the singleton instance of SampleDatabase.
     *
     * @param context The context.
     * @return The singleton instance of SampleDatabase.
     */

    companion object {
        private var sInstance: SampleDatabase? = null

        open fun getInstance(context: Context): SampleDatabase? {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, SampleDatabase::class.java, "ex")
                    .build()
                sInstance!!.populateInitialData()

            }
            return sInstance
        }

        /**
         * Switches the internal implementation with an empty in-memory database.
         *
         * @param context The context.
         */
        @VisibleForTesting
        open fun switchToInMemory(context: Context) {
            sInstance = Room.inMemoryDatabaseBuilder(
                context.applicationContext,
                SampleDatabase::class.java
            ).build()
        }
    }
    /**
     * Inserts the dummy data into the database if it is currently empty.
     */
    private  fun populateInitialData() {
        if (cheese().count() == 0) {
            runInTransaction {
                val cheese = Cheese()
                for (i in 0 until Cheese.CHEESES.size) {
                    cheese.name = Cheese.CHEESES[i]
                    cheese().insert(cheese)
                }
            }
        }
    }

}