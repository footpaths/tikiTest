package com.example.tikilisthorizital.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.R.attr.name
import android.text.TextUtils
import com.example.tikilisthorizital.R


class AdapterHotkey(private val data: ArrayList<String>, private val appThemeList: Array<String>) :
    RecyclerView.Adapter<AdapterHotkey.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_hotkey, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var name: String
        var parts = data[position].split(" ").toMutableList()
        val firstCharacter = parts.firstOrNull()
        parts.removeAt(0)
        val lastCharacter = parts.joinToString(" ")
        if (!TextUtils.isEmpty(firstCharacter)) {
            if (TextUtils.isEmpty(lastCharacter)) {
                name = data[position]
            } else {
                name = firstCharacter + "\n" + lastCharacter
            }
            holder.txtKeyName.text = name
            holder.cvContainer.setCardBackgroundColor(Color.parseColor(appThemeList[position]))
        }else{
            holder.cvContainer.visibility = View.GONE
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtKeyName: TextView = itemView.findViewById(R.id.txtKeyName)
        val cvContainer: CardView = itemView.findViewById(R.id.cvContainer)
    }
}