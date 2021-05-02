package com.devsoldatenkov.cryptoapp.view.viewmodels

import androidx.lifecycle.ViewModel
import com.devsoldatenkov.cryptoapp.di.DaggerAppComponent
import com.devsoldatenkov.cryptoapp.domain.Interactor
import javax.inject.Inject

class MainActivityViewModel : ViewModel() {
    @Inject
    lateinit var interactor: Interactor

    init {
        DaggerAppComponent.builder().build().inject(this)
    }

    fun getAssets() = interactor.getAssets()
}