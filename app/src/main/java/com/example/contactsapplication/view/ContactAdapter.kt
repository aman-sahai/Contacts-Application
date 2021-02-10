package com.example.contactsapplication.view

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapplication.BR
import com.example.contactsapplication.R
import com.example.contactsapplication.databinding.ItemContactBinding
import com.example.contactsapplication.model.Contact

class ContactAdapter(private var mContext:Context) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private var contacts: List<Contact>? = null
    fun setContacts(contacts: List<Contact>) {
        this.contacts = contacts
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ContactViewHolder {
        val binding: ItemContactBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context), R.layout.item_contact, viewGroup, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(contactViewHolder: ContactViewHolder, i: Int) {
        contactViewHolder.onBind(contacts!![i])
    }

    override fun getItemCount(): Int {
        return if (contacts != null) contacts!!.size else 0
    }

    inner class ContactViewHolder(itemContactBinding: ItemContactBinding) : RecyclerView.ViewHolder(itemContactBinding.getRoot()) {
        private val binding: ItemContactBinding = itemContactBinding
        private val mCheckBox: CheckBox? = null
        fun onBind(contact: Contact?) {
            binding.setVariable(BR.contact, contact)
            binding.executePendingBindings()
            mCheckBox?.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked)
                {
                    val sharedPreferences = mContext.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("Name", contact!!.name)
                    editor.putString("Phone",contact.phoneNumber)
                    editor.apply()
                    Toast.makeText(mContext,"Contact saved successfully.",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}