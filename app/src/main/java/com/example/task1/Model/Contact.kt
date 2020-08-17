package com.example.task1.Model

class Contact(val name: String, val isOnline: Boolean) {

    companion object {
        fun createContactsList(numContacts: Int): ArrayList<Contact> {
            var lastContactId = 0
            val contacts = ArrayList<Contact>()
            for (i in 1..numContacts) {
                contacts.add(
                    Contact(
                        "Person " + ++lastContactId,
                        i <= numContacts / 2
                    )
                )
            }
            return contacts
        }
    }
}