package com.turkcell.ticketapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import com.turkcell.data.di.networkModule
import com.turkcell.data.di.repositoryModule
import com.turkcell.ticketapp.di.viewModelModule


// Uygulama başladığında activitylerden önce oluşturulur.
// Singleton (uygulama yaşadığı sürece , tek bir instance olarak memoryde kalır)
// Uygulama kapanana kadar yok edilmez...
class TicketAppAplication : Application() {
    override fun onCreate () {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TicketAppAplication)
            modules(
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}