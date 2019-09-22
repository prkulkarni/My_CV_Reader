package com.prasad.cvreader.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.prasad.cvreader.ui.MyCVViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyCVViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyCVViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}