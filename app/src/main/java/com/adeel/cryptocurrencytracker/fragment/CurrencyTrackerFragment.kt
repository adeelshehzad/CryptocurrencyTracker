package com.adeel.cryptocurrencytracker.fragment

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adeel.cryptocurrencytracker.R
import com.adeel.cryptocurrencytracker.adapter.CryptoCurrencyRVAdapter
import com.adeel.cryptocurrencytracker.mvvm.model.CryptoCurrency
import com.adeel.cryptocurrencytracker.mvvm.viewmodel.CryptoCurrencyVM
import kotlinx.android.synthetic.main.fragment_currency_tracker.*

class CurrencyTrackerFragment : Fragment() {
    private var cryptoCurrencyViewModel: CryptoCurrencyVM? = null
    private var adapter: CryptoCurrencyRVAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_currency_tracker, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cryptoCurrencyViewModel =
            ViewModelProvider.AndroidViewModelFactory(Application()).create(CryptoCurrencyVM::class.java)

        getCryptoCurrencyData()

        swipeRefresh.setOnRefreshListener {
            getCryptoCurrencyData()
        }
    }

    private fun getCryptoCurrencyData() {
        swipeRefresh.isRefreshing = false
        cryptoCurrencyViewModel!!.cryptoCurrencyData.observe(this,
            Observer<CryptoCurrency> { t -> prepareCryptoCurrencyAdapter(t.data) })
    }

    private fun prepareCryptoCurrencyAdapter(data: List<CryptoCurrency.Data?>?) {
        adapter = CryptoCurrencyRVAdapter(data, View.OnClickListener {
            val viewHolder = it.tag as CryptoCurrencyRVAdapter.CryptoCurrencyVH
            val cryptoCurrency = viewHolder.getCurrency(viewHolder.adapterPosition)

            Toast.makeText(
                activity,
                "${cryptoCurrency.name} has a value of ${cryptoCurrency.quote?.uSD?.price} USD",
                Toast.LENGTH_LONG
            ).show()
        })

        rvCryptocurrency.layoutManager = LinearLayoutManager(activity)
        rvCryptocurrency.adapter = adapter
    }
}