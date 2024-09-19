package com.example.wishlistapp.ui.theme

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo("wish-title")
    var title: String= "",
    @ColumnInfo("wish-desc")
    var description:String= ""
)

object dummyData {
    val wish = listOf(
        Wish(
            id = 1, title = "HI", description = "ok"
        ), Wish(
            id = 2, title = "Good Job",
            description = "high paying"
        )
    )
}
