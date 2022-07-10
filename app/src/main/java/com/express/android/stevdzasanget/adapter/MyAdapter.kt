package com.express.android.stevdzasanget.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.express.android.stevdzasanget.R
import android.view.LayoutInflater
import com.express.android.stevdzasanget.databinding.RowLayoutBinding
import com.express.android.stevdzasanget.model.Post

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private var myList = emptyList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RowLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            bodyTxt.text = myList[position].userId.toString()
            idTxt.text = myList[position].id.toString()
            titleTxt.text = myList[position].id.toString()
            bodyTxt.text = myList[position].id.toString()
        }
    }

    inner class MyViewHolder(val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root)

    fun setData(newList: List<Post>){
        myList = newList
        notifyDataSetChanged()
    }
}