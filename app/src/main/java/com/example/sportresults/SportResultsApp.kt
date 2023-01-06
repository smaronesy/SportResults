package com.example.sportresults

import android.app.Application
import com.example.sportresults.database.ResultsDatabase
import com.example.sportresults.repository.ResultsRepository
import com.example.sportresults.ui.results.ResultsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class SportResultsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        /**
         * use Koin Library as a service locator
         */
        val myModule = module {
            //Declare a ViewModel - be later inject into Fragment with dedicated injector using by viewModel()
            viewModel {
                ResultsViewModel(
                    get(), get()
                )
            }

            single {
                ResultsRepository(
                    get()
                )
            }

            single {
                ResultsDatabase.getInstance(get()).resultsDao
            }

        }

        startKoin {
            androidContext(this@SportResultsApp)
            modules(listOf(myModule))
        }
    }
}