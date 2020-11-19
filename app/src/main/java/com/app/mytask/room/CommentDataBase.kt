package com.app.mytask.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Comments::class), version = 1, exportSchema = false)
 abstract class CommentDataBase : RoomDatabase() {

    abstract fun commentDao(): CommentDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CommentDataBase? = null

        fun getDatabase(context: Context): CommentDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CommentDataBase::class.java,
                    "comment_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}