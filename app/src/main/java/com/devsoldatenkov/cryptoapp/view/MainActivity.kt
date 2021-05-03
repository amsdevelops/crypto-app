package com.devsoldatenkov.cryptoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devsoldatenkov.cryptoapp.databinding.ActivityMainBinding
import com.devsoldatenkov.cryptoapp.view.adapters.MainListAdapter
import com.devsoldatenkov.cryptoapp.view.viewmodels.MainActivityViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(MainActivityViewModel::class.java)
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainListAdapter: MainListAdapter
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRV()

        viewModel.getCoinsFromCache()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = { println(it.localizedMessage) },
                onNext = {
                    mainListAdapter.addItems(it)
                }
            ).add()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun initRV() {
        binding.mainRecycler.apply {
            mainListAdapter = MainListAdapter {
                //todo implement details screen
            }
            adapter = mainListAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun Disposable.add() {
        compositeDisposable.add(this)
    }
}