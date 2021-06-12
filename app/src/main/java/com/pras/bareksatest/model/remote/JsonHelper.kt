package com.pras.bareksatest.model.remote

import android.content.Context
import android.util.Log
import com.pras.bareksatest.model.remote.response.DataResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import kotlin.math.min

class JsonHelper(private val context: Context) {
    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadData(): List<DataResponse> {
        val list = ArrayList<DataResponse>()
        try {
            val responseObject =
                JSONObject(parsingFileToString("BareksaTestDummyData.json").toString())
            val listArray = responseObject.getJSONArray("items")
            for (i in 0 until listArray.length()) {
                val dataList = listArray.getJSONObject(i)

                val name = dataList.getString("name")
                val jenis = dataList.getString("jenis")
                val imbalHasil = dataList.getString("imbalHasil")
                val danaKelolaan = dataList.getString("danaKelolaan")
                val minPembelian = dataList.getString("minPembelian")
                val jangkaWaktu = dataList.getString("jangkaWaktu")
                val tingkatRisiko = dataList.getString("tingkatRisiko")
                val peluncuran = dataList.getString("peluncuran")
                val graph = dataList.getString("graph")
//                val arr = dataList.getJSONArray("graph") )

//                var graph = arrayOf(null)
//                var graph: Array<Double>? = null
//                for (n in 0 until arr.length()) {
//                    graph?.set(n, arr.getDouble(n))
//                }

                val dataResponse = DataResponse(
                    name,
                    jenis,
                    imbalHasil,
                    danaKelolaan,
                    minPembelian,
                    jangkaWaktu,
                    tingkatRisiko,
                    peluncuran,
                    graph
                )
                Log.d("graphArray", dataResponse.toString())
                list.add(dataResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }
}