package com.pras.bareksatest.model.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "dataentity")
@Parcelize
data class DataEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "jenis")
    var jenis: String,

    @ColumnInfo(name = "imbalHasil")
    var imbalHasil: String,

    @ColumnInfo(name = "danaKelolaan")
    var danaKelolaan: String,

    @ColumnInfo(name="minPembelian")
    var minPembelian: String,

    @ColumnInfo(name = "jangkaWaktu")
    var jangkaWaktu: String,

    @ColumnInfo(name = "tingkatRisiko")
    var tingkatRisiko: String,

    @ColumnInfo(name = "peluncuran")
    val peluncuran: String,

    @ColumnInfo(name = "graph")
    val graph: String
): Parcelable
