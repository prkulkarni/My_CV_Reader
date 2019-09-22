package com.prasad.cvreader.base

import android.arch.lifecycle.ViewModel
import com.prasad.cvreader.injection.component.DaggerViewModelInjector
import com.prasad.cvreader.injection.component.ViewModelInjector
import com.prasad.cvreader.injection.module.NetworkModule
import com.prasad.cvreader.ui.MyCVViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MyCVViewModel -> injector.inject(this)
        }
    }
}