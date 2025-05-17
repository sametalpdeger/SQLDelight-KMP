package org.example.sqldelight

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.example.Falanfalan

// One way is to have an object or class that holds the application context.
object SqliteDriverCreator {
    private lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context.applicationContext
    }

    fun createDriver(): SqlDriver {
        if (!this::appContext.isInitialized) {
            throw IllegalStateException("SqliteDriverCreator not initialized. Call init(context) first.")
        }
        return AndroidSqliteDriver(
            Falanfalan.Schema, // Replace with your actual Schema class
            appContext,
            "databaseName.db"
        )
    }
}

actual fun createSqliteDriver(): SqlDriver {
    return SqliteDriverCreator.createDriver()
}