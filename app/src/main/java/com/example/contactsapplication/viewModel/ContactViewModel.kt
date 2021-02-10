package com.example.contactsapplication.viewModel

import android.content.Context
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableArrayList
import com.example.contactsapplication.model.Contact
import com.example.contactsapplication.model.ContactRepository


class ContactViewModel(context: Context?) : BaseObservable() {
    private val contacts: ObservableArrayList<Contact> = ObservableArrayList()
    private val repository: ContactRepository = ContactRepository(context!!)
    fun getContacts(): List<Contact> {
        contacts.addAll(repository.fetchContacts())
        return contacts
    }

}