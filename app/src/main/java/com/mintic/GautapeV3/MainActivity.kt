package com.mintic.GautapeV3

import GautapeV3.R
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException
import java.util.ArrayList
import org.json.JSONArray
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private lateinit var mGuatapes: ArrayList<Guatape>
    private lateinit var mAdapter: GuatapeAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.contact_list)
        setupRecyclerView()
        generateContacts()
//        mContacts = createMockContacts()
    }

    /**
     * Sets up the RecyclerView: empty data set, item dividers, swipe to delete.
     */
    private fun setupRecyclerView() {
        mGuatapes = arrayListOf()
        recycler.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        mAdapter = GuatapeAdapter(mGuatapes, this) { contact ->
            contactOnClick(contact)
        }

        recycler.adapter = mAdapter
    }

    /* RecyclerView item is clicked. */
    private fun contactOnClick(guatape: Guatape) {
        Log.d(TAG, "Click on: $guatape")
    }

    /**
     * Generates mock contact data to populate the UI from a JSON file in the
     * assets directory, called from the options menu.
     */
    private fun generateContacts() {
        val contactsString = readContactJsonFile()
        try {
            val contactsJson = JSONArray(contactsString)
            for (i in 0 until contactsJson.length()) {
                val contactJson = contactsJson.getJSONObject(i)
                val contact = Guatape(
                    contactJson.getString("place_name"),
                    contactJson.getString("place_description"),
                    contactJson.getString("stars"),
                    contactJson.getString("imageUrl")
                )
                Log.d(TAG, "generateContacts: $contact")
                mGuatapes.add(contact)
            }

            mAdapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    /**
     * Reads a file from the assets directory and returns it as a string.
     *
     * @return The resulting string.
     */
    private fun readContactJsonFile(): String? {
        var contactsString: String? = null
        try {
            val inputStream = assets.open("guatape_places.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            contactsString = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return contactsString
    }

    private fun createMockContacts(): ArrayList<Guatape> {
        return arrayListOf(
            Guatape("Malecón", "Parque lineal en la orilla del embalse de Guatapé", "Cinco estrellas", ""),
            Guatape("Malecón", "Parque lineal en la orilla del embalse de Guatapé", "Cinco estrellas", ""),
            Guatape("Malecón", "Parque lineal en la orilla del embalse de Guatapé", "Cinco estrellas", ""),
            Guatape("Malecón", "Parque lineal en la orilla del embalse de Guatapé", "Cinco estrellas", ""),
            Guatape("Malecón", "Parque lineal en la orilla del embalse de Guatapé", "Cinco estrellas", "")
        )
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}