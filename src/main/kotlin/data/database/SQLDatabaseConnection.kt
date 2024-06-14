package data.database

import java.sql.Connection

interface SQLDatabaseConnection {
    fun getConnection(): Connection
}