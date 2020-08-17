package com.example.task1.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task1.Adapter.ContactsAdapter
import com.example.task1.Model.Contact
import com.example.task1.R

class FirstFragment : Fragment() {
    lateinit var contacts: ArrayList<Contact>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_first, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val rvContacts = view.findViewById<View>(R.id.rvContacts) as RecyclerView
            contacts = Contact.createContactsList(20)
            val adapter = ContactsAdapter(contacts, this)
            rvContacts.adapter = adapter
            rvContacts.layoutManager = LinearLayoutManager(requireContext())
        }

    fun changeFragment(){
        val fragment = SecondFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}