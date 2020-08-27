package com.example.task1.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.Model.Contact
import com.example.task1.R

class ContactsAdapter(private val contacts: List<Contact>, private var onClick: ()->Unit) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.nameTextView.setText(contacts.get(position).name)
        viewHolder.setOnClickListener(View.OnClickListener { onClick() })

    }

    override fun getItemCount(): Int = contacts.size

    inner class ViewHolder(private val listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.contact_name)
        fun setOnClickListener(onClickListener: View.OnClickListener) {
            listItemView.setOnClickListener(onClickListener)
        }
    }
}