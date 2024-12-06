package DB
import java.sql.*

class ConnectDB {
    val jdbcUrl = "jdbc:postgresql://localhost:5432/books"  // process.env
    val userName = "postgres"  // os process
    val password = "utkarsh"   // os process
    // get the connection
    val connection = DriverManager.getConnection(jdbcUrl, userName, password)
    // prints true if the connection is valid
//    println(connection)
}
//?user=postgres&password=utkarsh&ssl=true