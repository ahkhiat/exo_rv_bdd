package com.devid_academy.exo_rv_bdd

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devid_academy.exo_rv_bdd.DTO.CountryDTO
import com.devid_academy.exo_rv_bdd.Network.getAddCountry

class CreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val etCountryName: EditText = findViewById(R.id.create_input_country_name)
        val etCountryUrlFlag: EditText = findViewById(R.id.create_input_url)

        findViewById<Button>(R.id.create_button_validate).setOnClickListener {
            val countryName = etCountryName.text.toString().trim()
            val countryFlag = etCountryUrlFlag.text.toString().trim()

            if(countryName.isNotEmpty() && countryFlag.isNotEmpty()) {
                val country = CountryDTO(0, 10, countryName, countryFlag)
                getAddCountry(country) { isSuccess ->
                    if (isSuccess) {
                        setResult(RESULT_OK)
                        finish()
                    } else {
                        Toast.makeText(this, "Échec de l'ajout du pays", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }

        }



    }
}