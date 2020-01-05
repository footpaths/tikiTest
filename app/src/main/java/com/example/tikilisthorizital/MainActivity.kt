package com.example.tikilisthorizital

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tikilisthorizital.ApiService.ApiClient
import com.example.tikilisthorizital.adapter.AdapterHotkey
import com.example.tikilisthorizital.myUtils.TikiUtils
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var data: ArrayList<String>? = ArrayList()
    lateinit var progerssProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appThemeList = this.resources.getStringArray(R.array.listcolors)
        rcvHotKey.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rcvHotKey.adapter = AdapterHotkey(data!!, appThemeList)
        progerssProgressDialog = ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()
        if (TikiUtils.isOnline(this)) {
            getData()
        } else {
            getLocalData()
        }
    }

    private fun getLocalData() {
        val json: String = TikiUtils.AssetJSONFile("keywords.json", this)
        val jsonArray = JSONArray(json)
        data!!.addAll(TikiUtils.mappingDataLocal(jsonArray))
        rcvHotKey.adapter!!.notifyDataSetChanged()
        progerssProgressDialog.dismiss()
    }

    private fun getData() {
        val call: Call<List<String>> = ApiClient.getClient.getPhotos()
        call.enqueue(object : Callback<List<String>> {

            override fun onResponse(call: Call<List<String>>?, response: Response<List<String>>?) {
                progerssProgressDialog.dismiss()
                data!!.addAll(TikiUtils.mappingData(response!!.body()))

                rcvHotKey.adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<String>>?, t: Throwable?) {
                progerssProgressDialog.dismiss()

            }

        })
    }
}
