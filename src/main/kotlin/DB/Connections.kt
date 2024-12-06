package DB

import java.sql.DriverManager
import java.sql.Connection

// error handling missing

class Connections {
    private val jdbcUrl = "jdbc:postgresql://localhost:5432/books";  // put in environment variables
    private val userName = "postgres";  // put in environment variables
    private val password = "utkarsh";   // put in environment variables
    fun connect():Connection {
        // get the connection
        return DriverManager.getConnection(jdbcUrl, userName, password);
    }
    fun close(connection:Connection) {
        connection.close()
    }
}