package com.devid_academy.exo_rv_bdd

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devid_academy.exo_rv_bdd.Network.getRemoteCountries

class RecyclerViewRemoteActivity : AppCompatActivity() {

    private lateinit var countryAdapter: CountryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_remote)

        progressBar = findViewById(R.id.progressBar)

        val registerCreateCountry =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if(result.resultCode == RESULT_OK)
                    refresh()
            }

        val registerEditCountry =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    refresh()
                }
            }

        countryAdapter = CountryAdapter { country ->
            val intent = Intent(this, EditRemoteActivity::class.java)
                .putExtra("country", country)
            registerEditCountry.launch(intent)
        }


        recyclerView = findViewById<RecyclerView>(R.id.rv_country).apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewRemoteActivity)
            adapter = countryAdapter
        }



        findViewById<Button>(R.id.rva_btn_add_country).setOnClickListener {
            registerCreateCountry.launch(Intent(this, CreateActivity::class.java))



        }


    refresh()
    }


    private fun refresh() {
        progressBar.visibility = View.VISIBLE
        getRemoteCountries {
            countryAdapter.setCountries(it)
            recyclerView.scrollToPosition(countryAdapter.itemCount - 1)
            progressBar.visibility = View.GONE
            Log.d("DEBUG", "Nombre de pays récupérés : ${countryAdapter.itemCount}")
        }

    }
}