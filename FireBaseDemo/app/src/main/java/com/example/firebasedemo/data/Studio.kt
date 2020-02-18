package com.example.firebasedemo.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "studio")
data class Studio(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name="name")
    var name:String,
    @ColumnInfo(name="info")
    var info:String
){
    constructor() : this(null,"","")
}