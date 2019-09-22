package com.prasad.cvreader.injection.component

import dagger.Component
import com.prasad.cvreader.injection.module.NetworkModule
import com.prasad.cvreader.ui.MyCVViewModel
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified MyCVViewModel.
     * @param myCVViewModel MyCVViewModel in which to inject the dependencies
     */
    fun inject(myCVViewModel: MyCVViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}