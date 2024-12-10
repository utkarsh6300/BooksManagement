package repository

import config.Connections as Connections
import services.Book
import java.sql.SQLException


//import io.micronaut.data.repository.CrudRepository;    // for using crud of framework  // will be using later.


class BookManagementRepository : BookManagementRepositoryInterface {

    private val dbConnections = Connections()

    // can make all functions generic as there is repetition and tight coupling or can use framework provided functions for now using these.
    override fun insertBook(book: Book) : Boolean {
            val connection = dbConnections.connect()
            // add data to db
            val preparedStatement = connection.prepareStatement("INSERT INTO BOOK (id,name,author,isbn) VALUES (?,?,?,?)");
        try {
            preparedStatement.setString(1, book.getId())
            preparedStatement.setString(2, book.getName())
            preparedStatement.setString(3, book.getAuthor())
            preparedStatement.setInt(4, book.getIsbn())
            val result = preparedStatement.executeUpdate();
            return result !=0
        }
        catch (error: SQLException){
            // needs to handle duplicate key issue
            println(error)
            return false
        }
        finally {
            preparedStatement.close();
            dbConnections.close(connection)
        }
    }

    override fun deleteBook(id: String): Boolean {
        val connection = dbConnections.connect()
        // delete data in db
        val preparedStatement = connection.prepareStatement("DELETE FROM BOOK WHERE id = ?");
        preparedStatement.setString(1, id)
        val result = preparedStatement.executeUpdate();
        preparedStatement.close();
        dbConnections.close(connection)
        return result !=0
    }

    override fun updateBook(id: String, book: Book): Boolean {
        val connection = dbConnections.connect()
        // update data in db
        val preparedStatement =
            connection.prepareStatement("UPDATE BOOK SET name = ?, author = ?, isbn = ? WHERE id = ?");
        preparedStatement.setString(4, id)
        preparedStatement.setString(1, book.getName())
        preparedStatement.setString(2, book.getAuthor())
        preparedStatement.setInt(3, book.getIsbn())
        val result = preparedStatement.executeUpdate();
        preparedStatement.close();
        dbConnections.close(connection)
        return result !=0
    }

    override fun getBook(id: String): Book? {
        val connection = dbConnections.connect()
        // get data from db
        val preparedStatement = connection.prepareStatement("SELECT * FROM BOOK WHERE id = ?");
        preparedStatement.setString(1, id)
        val result = preparedStatement.executeQuery();
        var book: Book? = null
        if (result.next()) {
            val name = result.getString(2);
            val author = result.getString(3);
            val isbn = result.getInt(4);
            book = Book(name, author, isbn, id)
        }
        preparedStatement.close();
        dbConnections.close(connection)
        return book
    }
}