package data.database

import java.sql.Connection
import java.sql.DriverManager

class MySQLConnection: SQLDatabaseConnection {
    companion object {
        private const val JDBC_URL = "jdbc:mariadb://localhost:3306/brainchallenge"
        private const val JDBC_USER = "root"
        private const val JDBC_PASSWORD = "root"
    }
    override fun getConnection(): Connection =
        DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)
}