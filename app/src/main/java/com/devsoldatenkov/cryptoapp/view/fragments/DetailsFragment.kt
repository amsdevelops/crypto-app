package com.devsoldatenkov.cryptoapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devsoldatenkov.cryptoapp.data.db.entity.CoinData
import com.devsoldatenkov.cryptoapp.databinding.FragmentDetailsBinding
import com.devsoldatenkov.cryptoapp.view.viewmodels.DetailsFragmentViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coinData: CoinData = arguments?.get(COIN_DATA_KEY) as CoinData
        binding.textView.text = coinData.toString()

        val endTime = System.currentTimeMillis()
        val startTime = endTime - DAYS_30
        viewModel.getCoinHistory(coinData.id, "d1", startTime, endTime)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    Timber.e(it.localizedMessage)
                },
                onNext = {
                    binding.chartView.setCoinHistoryData(it.data)
                    println(it.data)
                }
            )
    }

    companion object {
        private const val COIN_DATA_KEY = "coin_data"
        private const val DAYS_30 = 2592000000L
    }
}