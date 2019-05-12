package com.kiliian.schibstedtest.exrate.view


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.kiliian.schibstedtest.R
import com.kiliian.schibstedtest.app.SingleActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ExchangeRatesFragmentTest {

    @get:Rule
    var testRule = ActivityTestRule(SingleActivity::class.java)

    @Before
    fun setUp() {
        testRule.activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, ExchangeRatesFragment())
            .commit()
    }

    @Test
    fun start_shouldHideTodayGroup() {
        onView(withId(R.id.exchange_rates_today_rate_group))
            .check(ViewAssertions.matches(withEffectiveVisibility(Visibility.GONE)))
    }
}