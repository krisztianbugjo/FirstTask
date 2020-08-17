package com.example.task1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter (private val mContacts: List<Contact>, private val mFragment: FirstFragment) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>()
{
    inner class ViewHolder(private val listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.contact_name)
        val messageButton = itemView.findViewById<Button>(R.id.message_button)

        fun setOnClickListener(onClickListener: View.OnClickListener){
            listItemView.setOnClickListener(onClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_contact, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: ContactsAdapter.ViewHolder, position: Int) {
        val contact: Contact = mContacts.get(position)
        val textView = viewHolder.nameTextView
        textView.setText(contact.name)
        val button = viewHolder.messageButton
        button.text = if (contact.isOnline) "Message" else "Offline"
        button.isEnabled = contact.isOnline
        viewHolder.setOnClickListener(View.OnClickListener { mFragment.changeFragment() })
    }

    override fun getItemCount(): Int {
        return mContacts.size
    }
}