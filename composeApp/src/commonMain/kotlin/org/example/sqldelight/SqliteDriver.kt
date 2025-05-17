package org.example.sqldelight

import app.cash.sqldelight.db.SqlDriver
import org.example.Falanfalan

expect fun createSqliteDriver(): SqlDriver

val sqliteDb by lazy {
    Falanfalan(
        createSqliteDriver()
    )
}

val sqliteQuery by lazy { sqliteDb.databaseQueries }