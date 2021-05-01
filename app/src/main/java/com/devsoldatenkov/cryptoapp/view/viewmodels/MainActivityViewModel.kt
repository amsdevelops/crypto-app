package com.devsoldatenkov.cryptoapp.view.viewmodels

import androidx.lifecycle.ViewModel
import com.devsoldatenkov.cryptoapp.App
import com.devsoldatenkov.cryptoapp.domain.Interactor
import javax.inject.Inject

class MainActivityViewModel : ViewModel() {
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.daggerComponent.inject(this)
    }

    fun getAssets() = interactor.assetsList
}