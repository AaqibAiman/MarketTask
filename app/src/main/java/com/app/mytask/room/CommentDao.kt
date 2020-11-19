package com.app.mytask.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(comments: Comments) : Long

    @Query("SELECT distinct comment from comments WHERE comment_id = :commentId")
    fun getCommentById(commentId : String) : List<String>?
}