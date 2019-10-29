package com.adeel.cryptocurrencytracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adeel.cryptocurrencytracker.R
import com.adeel.cryptocurrencytracker.mvvm.model.CryptoCurrency

class CryptoCurrencyRVAdapter(
    private val data: List<CryptoCurrency.Data?>?,
    val onClickListener: View.OnClickListener
) : RecyclerView.Adapter<CryptoCurrencyRVAdapter.CryptoCurrencyVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoCurrencyVH {
        val contentView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cryptocurrency, parent, false)
        return CryptoCurrencyVH(contentView)
    }

    override fun getItemCount() = data!!.size

    override fun onBindViewHolder(holder: CryptoCurrencyVH, position: Int) {
        val cryptoCurrency = data!![position]
        holder.bindData(cryptoCurrency!!)
    }

    inner class CryptoCurrencyVH(view: View) : RecyclerView.ViewHolder(view) {
        val tvCryptocurrencyName: TextView by lazy { itemView.findViewById<TextView>(R.id.tvCryptocurrencyName) }
        val tvCryptocurrencyPrice: TextView by lazy { itemView.findViewById<TextView>(R.id.tvCryptocurrencyPrice) }

        fun bindData(data: CryptoCurrency.Data) {
            tvCryptocurrencyName.text = data.name
            tvCryptocurrencyPrice.text = "$" + data.quote?.uSD?.price.toString() + " USD"

            itemView.tag = this
            itemView.setOnClickListener(onClickListener)

        }

        fun getCurrency(position: Int): CryptoCurrency.Data {
            return data!![position]!!
        }

    }
}