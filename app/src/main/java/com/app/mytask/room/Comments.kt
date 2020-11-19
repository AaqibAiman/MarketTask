package com.app.mytask.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class Comments(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "comment_id")
    var commentId: String = "",
    @ColumnInfo(name = "comment")
    var comment: String = ""
)