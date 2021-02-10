package com.example.contactsapplication

import android.Manifest

import android.content.pm.PackageManager

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapplication.databinding.ActivityMainBinding
import com.example.contactsapplication.view.ContactAdapter
import com.example.contactsapplication.viewModel.ContactViewModel


class MainActivity : AppCompatActivity() {
    private var contactViewModel: ContactViewModel? = null
    val REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        contactViewModel = ContactViewModel(applicationContext)
        binding.setViewModel(contactViewModel)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPhoneContactsPermission(Manifest.permission.READ_CONTACTS)) requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                REQUEST_CODE
            )
        } else {
            initRecyclerView()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> initRecyclerView()
        }
    }

    private fun hasPhoneContactsPermission(permission: String): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val hasPermission =
                ContextCompat.checkSelfPermission(applicationContext, permission)
            hasPermission == PackageManager.PERMISSION_GRANTED
        } else true
    }


    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.contact_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        val adapter = ContactAdapter(this@MainActivity)
        adapter.setContacts(contactViewModel!!.getContacts())
        recyclerView.adapter = adapter
    }
}