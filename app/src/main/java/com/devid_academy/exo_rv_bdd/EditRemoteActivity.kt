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
import com.devid_academy.exo_rv_bdd.Network.MY_ID_STAGIAIRE
import com.devid_academy.exo_rv_bdd.Network.getAddCountry
import com.devid_academy.exo_rv_bdd.Network.getEditCountry

class EditRemoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_remote)

        val etCountryName: EditText = findViewById(R.id.edit_input_country_name)
        val etCountryUrlFlag: EditText = findViewById(R.id.edit_input_url)

        val country: CountryDTO? = intent.getParcelableExtra("country")

        country?.let {
            etCountryName.setText(it.nom)
            etCountryUrlFlag.setText(it.url)
        }

        findViewById<Button>(R.id.edit_button_validate).setOnClickListener {
            val countryName = etCountryName.text.toString().trim()
            val countryFlag = etCountryUrlFlag.text.toString().trim()

            val country = intent.getParcelableExtra<CountryDTO>("country")

            if(countryName.isNotEmpty() && countryFlag.isNotEmpty()) {
                val updatedCountry = CountryDTO(country!!.id, MY_ID_STAGIAIRE, countryName, countryFlag)
                getEditCountry(updatedCountry) { isSuccess ->
                    if (isSuccess) {
                        setResult(RESULT_OK)
                        finish()
                    } else {
                        Toast.makeText(this, "Échec de la modification du pays", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }
    }
}