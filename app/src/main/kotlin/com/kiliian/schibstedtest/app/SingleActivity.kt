package com.kiliian.schibstedtest.app

import android.os.Bundle
import com.kiliian.schibstedtest.R
import com.kiliian.schibstedtest.exrate.view.ExchangeRatesFragment
import dagger.android.support.DaggerAppCompatActivity

class SingleActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, ExchangeRatesFragment())
                .commit()
        }
    }
}
