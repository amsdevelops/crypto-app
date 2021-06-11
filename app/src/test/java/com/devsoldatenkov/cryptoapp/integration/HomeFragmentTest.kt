package com.devsoldatenkov.cryptoapp.integration

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import com.devsoldatenkov.cryptoapp.R
import com.devsoldatenkov.cryptoapp.TestApp
import com.devsoldatenkov.cryptoapp.base.GSTestRunner
import com.devsoldatenkov.cryptoapp.base.RxRule
import com.devsoldatenkov.cryptoapp.view.fragments.HomeFragment
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

@RunWith(GSTestRunner::class)
@Config(application = TestApp::class, sdk = [28])
class HomeFragmentTest {
    private lateinit var scenario: FragmentScenario<HomeFragment>
    @get:Rule
    val rxRule = RxRule()
    var mockWebServer: MockWebServer = MockWebServer()

    @Before
    fun setUp() {
        System.setProperty("javax.net.ssl.trustStoreType", "JKS")
    }

    @Test
    fun `should populate movie info in views`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(RESPONSE)
            .setBodyDelay(0, TimeUnit.MILLISECONDS)
        mockWebServer.enqueue(response)
        mockWebServer.start(8080)
        scenario = launchFragmentInContainer()
        scenario.onFragment {
            val rv = it.view?.rootView?.findViewById<RecyclerView>(R.id.main_recycler)
            Assert.assertTrue(rv?.adapter?.itemCount != 0)
        }
    }

    companion object {
        private const val RESPONSE ="{\"data\":[{\"id\":\"bitcoin\",\"rank\":\"1\",\"symbol\":\"BTC\",\"name\":\"Bitcoin\",\"supply\":\"18732437.0000000000000000\",\"maxSupply\":\"21000000.0000000000000000\",\"marketCapUsd\":\"698347224138.2012524695027484\",\"volumeUsd24Hr\":\"25251248952.0740608211049362\",\"priceUsd\":\"37280.1053134838383532\",\"changePercent24Hr\":\"1.8361491228756762\",\"vwap24Hr\":\"36950.5392832112291316\",\"explorer\":\"https://blockchain.info/\"}],\"timestamp\":1623441886884}"

    }
}