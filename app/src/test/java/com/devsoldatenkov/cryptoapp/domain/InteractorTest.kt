package com.devsoldatenkov.cryptoapp.domain

import com.devsoldatenkov.cryptoapp.data.CoinRepository
import com.devsoldatenkov.remote.CoinCapApi
import io.mockk.*
import org.junit.Before
import org.junit.Test

class InteractorTest {
    private lateinit var interactor: Interactor
    private val repository: CoinRepository = mockk(relaxed = true)
    private val remote: CoinCapApi = mockk(relaxed = true)

    @Before
    fun setUp() {
        interactor = Interactor(remote, repository)
    }

    @Test
    fun `should get assets from remote`() {
        interactor.getAssetsFromRemote()
        verify {  remote.getAssets() }
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