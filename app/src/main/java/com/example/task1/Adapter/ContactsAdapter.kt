package com.example.task1.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.Model.Contact
import com.example.task1.R
import com.example.task1.UI.FirstFragment

class ContactsAdapter(private val mContacts: List<Contact>, private val mFragment: FirstFragment) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    inner class ViewHolder(private val listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.contact_name)
        fun setOnClickListener(onClickListener: View.OnClickListener) {
            listItemView.setOnClickListener(onClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_contact, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val contact: Contact = mContacts.get(position)
        val textView = viewHolder.nameTextView
        textView.setText(contact.name)
        viewHolder.setOnClickListener(View.OnClickListener { mFragment.changeFragment() })
    }

    override fun getItemCount(): Int {
        return mContacts.size
    }
}