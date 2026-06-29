package vallab.practice

import android.app.Application
import vallab.practice.data.di.AppContainer

class App : Application() {

    val appContainer = AppContainer()
}