package com.wildan.mpoop2_finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wildan.mpoop2_finalproject.model.Contact

class ContactAdapter(
    private val listItems: ArrayList<Contact>,
    private val listener: ContactListener
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = listItems[position]
        holder.textViewName.text = item.name
        holder.textViewOperator.text = item.operator
        holder.textViewNomor.text = item.nomor
        holder.itemView.setOnClickListener {
            listener.OnItemClicked(item)
        }
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName = itemView.findViewById<TextView>(R.id.text_view_name)
        var textViewOperator = itemView.findViewById<TextView>(R.id.text_view_operator)
        var textViewNomor = itemView.findViewById<TextView>(R.id.text_view_nomor)
    }

    interface ContactListener{
        fun OnItemClicked(contact: Contact)
    }
}