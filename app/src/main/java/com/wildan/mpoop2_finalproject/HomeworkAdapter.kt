package com.wildan.mpoop2_finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wildan.mpoop2_finalproject.model.Homework
import kotlinx.android.synthetic.main.item_homework.view.*

class HomeworkAdapter(val homework : ArrayList<Homework>, val onClick : OnClick) : RecyclerView.Adapter<HomeworkAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_homework, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int = homework.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(homework.get(position))
        holder.itemView.btDeleteNote.setOnClickListener {
            onClick.delete(homework.get(position).key)
        }
        holder.itemView.setOnClickListener {
            onClick.edit(homework.get(position))
        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(homework : Homework){
            itemView.tvHomeworkName.text = homework.title
            itemView.tvHomerowkDescription.text = homework.description
        }
    }

    interface OnClick {
        fun delete(key : String?)
        fun edit(homework : Homework?)
    }
}