package com.adeel.cryptocurrencytracker.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adeel.cryptocurrencytracker.R
import com.adeel.cryptocurrencytracker.fragment.CurrencyTrackerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, CurrencyTrackerFragment()).commit()
    }
}
