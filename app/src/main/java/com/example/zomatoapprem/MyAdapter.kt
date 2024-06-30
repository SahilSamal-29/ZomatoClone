package com.example.zomatoapprem

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.recyclerview.widget.RecyclerView
class MyAdapter(private var foodArrayList:ArrayList<Food>, var context:Activity ):
RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.each_item , parent , false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return foodArrayList.size
    }
    private var count = 0
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentitem = foodArrayList[position]
        holder.image.setImageResource(currentitem.foodPic)
        holder.name.text = currentitem.itemName
        holder.prize.text = currentitem.prize
        holder.itemView.findViewById<Button>(R.id.btnAddToCart).setOnClickListener{
            count++
            Toast.makeText(context , " you clicked btn$position and yor item count is $count" , Toast.LENGTH_SHORT).show()
        }
        }
    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tvItemNameLabel)
        val image = itemView.findViewById<ImageView>(R.id.foodpic)
        val prize = itemView.findViewById<TextView>(R.id.tvPriceTag)
    }
    }
