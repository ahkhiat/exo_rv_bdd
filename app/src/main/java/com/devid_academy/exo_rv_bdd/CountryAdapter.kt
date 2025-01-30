package com.devid_academy.exo_rv_bdd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.devid_academy.exo_rv_bdd.DTO.CountryDTO
import com.squareup.picasso.Picasso

class CountryAdapter(private val onItemClick: (CountryDTO) -> Unit, private val onItemDelete: (CountryDTO) -> Unit) : RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    private val countriesList : MutableList<CountryDTO> = mutableListOf()

    private var rva : RecyclerViewRemoteActivity? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryAdapter.CountryHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_country, parent, false)
            .let {
                CountryHolder(it)


            }

    }

    override fun onBindViewHolder(holder: CountryAdapter.CountryHolder, position: Int) {

        val country = countriesList.get(position)

        with(holder) {
            tvItem.text = country.nom

            if(!country.url.isNullOrEmpty()) {
                Picasso.get()
                    .load(country.url)
                    .into(ivItem)

            } else if(country.id != null) {
//            holder.ivItem.setImageResource(country.id)
            }
            itemLayout.setOnClickListener {
                onItemClick(country)
            }

            // bouton delete
            btnDelete.setOnClickListener {
                onItemDelete(country)
            }

        }

    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

    fun setCountries(countries: List<CountryDTO>) {
        with(countriesList) {
            clear()
            addAll(countries)
        }
        notifyDataSetChanged() // force l'adapter à recharger
    }

    fun setRva(rva: RecyclerViewRemoteActivity) {
        this.rva = rva
    }


    class CountryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvItem: TextView = itemView.findViewById(R.id.item_tv)
        val ivItem: ImageView = itemView.findViewById(R.id.item_iv)
        val itemLayout: View = itemView.findViewById(R.id.item_rv_country)
        val btnDelete: Button = itemView.findViewById(R.id.item_btn_delete)
    }

}

