package com.devsoldatenkov.cryptoapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devsoldatenkov.cryptoapp.databinding.FragmentFavoritesBinding
import com.devsoldatenkov.cryptoapp.view.adapters.BasicListAdapter
import com.devsoldatenkov.cryptoapp.view.viewmodels.FavoritesFragmentViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var favoritesListAdapter: BasicListAdapter
    private val compositeDisposable = CompositeDisposable()
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(FavoritesFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()
        viewModel.getFavoritesCoins()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = { Timber.e(it.localizedMessage) },
                onNext = {
                    favoritesListAdapter.addItems(it)
                }
            ).add()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun initRV() {
        binding.favoritesRecycler.apply {
            favoritesListAdapter = BasicListAdapter {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                findNavController().navigate(action)
            }
            adapter = favoritesListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun Disposable.add() {
        compositeDisposable.add(this)
    }
}