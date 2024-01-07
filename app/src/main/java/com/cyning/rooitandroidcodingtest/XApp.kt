package com.cyning.rooitandroidcodingtest

import android.app.Application
import com.cyning.rooitandroidcodingtest.data.DataManager
import com.cyning.rooitandroidcodingtest.ui.NewsAdapter
import com.cyning.rooitandroidcodingtest.vm.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

//use koin for DI
val localModule = module {
    single { DataManager() }
    factory { MainViewModel(get()) }
    factory { NewsAdapter() }
}

class XApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@XApp)
            modules(localModule)
        }
    }
}