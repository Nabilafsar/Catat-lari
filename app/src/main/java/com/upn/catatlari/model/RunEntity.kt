package com.upn.catatlari.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "run_table")
data class RunEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val runDate: String,
    val runDistance: Int,
    val runDuration: Int
) : Parcelable