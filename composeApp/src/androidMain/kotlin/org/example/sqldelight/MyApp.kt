package org.example.sqldelight

import android.app.Application

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SqliteDriverCreator.init(this)
    }
}