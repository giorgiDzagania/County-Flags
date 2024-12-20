package com.example.flagsapp

import android.app.Application
import com.example.flagsapp.di.networkModule
import com.example.flagsapp.di.repositoryModule
import com.example.flagsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApplicationClass)
            modules(
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }

}