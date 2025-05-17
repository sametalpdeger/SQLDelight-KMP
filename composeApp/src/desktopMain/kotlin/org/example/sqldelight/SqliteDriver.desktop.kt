package org.example.sqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.example.Falanfalan

actual fun createSqliteDriver(): SqlDriver {
    return JdbcSqliteDriver(
        url = "jdbc:sqlite:databaseName.db",
        schema = Falanfalan.Schema
    )
}
