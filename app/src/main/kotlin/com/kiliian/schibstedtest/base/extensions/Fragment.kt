package com.kiliian.schibstedtest.base.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> Fragment.viewModel(factory: ViewModelProvider.Factory) =
    ViewModelProviders.of(this, factory)[T::class.java]