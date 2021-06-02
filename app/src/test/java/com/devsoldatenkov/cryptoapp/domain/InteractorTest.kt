package com.devsoldatenkov.cryptoapp.domain

import com.devsoldatenkov.cryptoapp.data.CoinRepository
import com.devsoldatenkov.cryptoapp.data.db.entity.CoinData
import com.devsoldatenkov.cryptoapp.utils.Converters
import com.devsoldatenkov.cryptoapp.utils.Converters.toCoinData
import com.devsoldatenkov.remote.CoinCapApi
import com.devsoldatenkov.remote.entity.AssetsResult
import com.devsoldatenkov.remote.entity.CoinDataDto
import io.mockk.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import java.util.*

class InteractorTest {
    private lateinit var interactor: Interactor
    private val repository: CoinRepository = mockk(relaxed = true)
    private val remote: CoinCapApi = mockk()

    @Before
    fun setUp() {
        interactor = Interactor(remote, repository)
    }

    @Test
    fun `should call toCoinData on getAssetsFromRemote`() {
        val assetsResult = mockk<AssetsResult>()
        every { remote.getAssets() } returns Observable.just(assetsResult)
        interactor.getAssetsFromRemote()
        verify {
            assetsResult.data.map {
                it.toCoinData()
            }
        }
    }

    @Test
    fun `should call putCoinsToCache on getAssetsFromRemote`() {
        val assetResult = AssetsResult (
            data = listOf(
                CoinDataDto(
                    changePercent24Hr = "null",
                    id = "null",
                    marketCapUsd = "null",
                    maxSupply = "null",
                    name = "null",
                    priceUsd = "null",
                    rank = "null",
                    supply = "null",
                    symbol = "null",
                    volumeUsd24Hr = "null",
                    vwap24Hr = "null"
                )
            ),
            timestamp = 0
        )
        every { remote.getAssets() } returns Observable.just(assetResult)
        interactor.getAssetsFromRemote()
        verify {
            repository.putCoinsToCache(any())
        }
    }

    @Test
    fun `should get coin history from remote`() {
        interactor.getCoinHistoryFromRemote("", "", 0L, 0L)
        verify {  remote.getCoinHistory(any(), any(), any(), any()) }
    }

    @Test
    fun `should get put coins to favorites`() {
        interactor.insertCoinToFavorites(mockk())
        verify { repository.insertCoinToFavorites(any()) }
    }
}