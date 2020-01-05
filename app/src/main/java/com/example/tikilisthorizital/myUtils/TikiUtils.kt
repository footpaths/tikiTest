package com.example.tikilisthorizital.myUtils

import android.content.Context
import android.net.ConnectivityManager
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

object TikiUtils {
    fun mappingData(jsonArray: List<String>?): ArrayList<String> {
        var data: ArrayList<String>? = ArrayList()
        var i = 0
        val count = jsonArray!!.size
        while (i < count) {
            try {
                val jsonObject = jsonArray!![i]
                data!!.add(jsonObject)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            i++
        }
        return data!!
    }

    fun mappingDataLocal(jsonArray: JSONArray): ArrayList<String> {
        var data: ArrayList<String>? = ArrayList()
        var i = 0
        val count = jsonArray.length()
        while (i < count) {
            try {
                val jsonObject = jsonArray.get(i)
                data!!.add(jsonObject.toString())
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            i++
        }
        return data!!
    }
    /**
     * check Is online
     */
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
    /**
     * AssetJSONFile return string json keyword
     */

    @Throws(IOException::class)
    fun AssetJSONFile(filename: String, context: Context): String {
        val manager = context.assets
        val file = manager.open(filename)
        val formArray = ByteArray(file.available())
        file.read(formArray)
        file.close()

        return String(formArray)
    }
}