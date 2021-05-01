package com.devsoldatenkov.cryptoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.devsoldatenkov.cryptoapp.R
import com.devsoldatenkov.cryptoapp.view.viewmodels.MainActivityViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getAssets()
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onError = { println(it.localizedMessage) },
                onNext = {
                    println( it.data.size)
                }
            )
    }
}