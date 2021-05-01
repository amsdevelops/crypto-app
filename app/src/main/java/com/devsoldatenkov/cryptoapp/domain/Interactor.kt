package com.devsoldatenkov.cryptoapp.domain

import com.devsoldatenkov.cryptoapp.remote.CoinCapApi

class Interactor(val remote: CoinCapApi) {
    val assetsList = remote.getAssets()
}