package com.pras.bareksatest.model.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataResponse(
    val name: String,
    val jenis: String,
    val imbalHasil: String,
    val danaKelolaan: String,
    val minPembelian: String,
    val jangkaWaktu: String,
    val tingkatRisiko: String,
    val peluncuran: String,
    val graph: String
): Parcelable
