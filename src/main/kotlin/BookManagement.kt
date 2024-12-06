import Book as Book
import  BookManagementInterface as BookManagementInterface
import DB.Connections as Connections

// store data in DB Postgres
// skipping error handling
class BookManagement(size: Int) : BookManagementInterface {
    private val dbConnections = Connections()
    override fun addBook(book: Book) {
        val connection = dbConnections.connect()
        // add data to db
        val preparedStatement = connection.prepareStatement("INSERT INTO BOOK (id,name,author,isbn) VALUES (?,?,?,?)");
        preparedStatement.setString(1, book.getId())
        preparedStatement.setString(2, book.getName())
        preparedStatement.setString(3, book.getAuthor())
        preparedStatement.setInt(4, book.getIsbn())
        preparedStatement.executeUpdate();
        preparedStatement.close();
        dbConnections.close(connection)
    }

    override fun deleteBook(id: String): Boolean {
        val connection = dbConnections.connect()
        // delete data from db
        val preparedStatement = connection.prepareStatement("DELETE FROM BOOK WHERE id = ?");
        preparedStatement.setString(1, id)
        preparedStatement.executeUpdate();
        preparedStatement.close();
        dbConnections.close(connection)
        return true
    }

    override fun updateBook(id: String, book: Book): Boolean {
        val connection = dbConnections.connect()
        // update data from db
        val preparedStatement =
            connection.prepareStatement("UPDATE BOOK SET name = ?, author = ?, isbn = ? WHERE id = ?");
        preparedStatement.setString(4, id)
        preparedStatement.setString(1, book.getName())
        preparedStatement.setString(2, book.getAuthor())
        preparedStatement.setInt(3, book.getIsbn())
        preparedStatement.executeUpdate();
        preparedStatement.close();
        dbConnections.close(connection)
        return true
    }

    override fun getBook(id: String): Book? {
        val connection = dbConnections.connect()
        // get data from db
        val preparedStatement = connection.prepareStatement("SELECT * FROM BOOK WHERE id = ?");
        preparedStatement.setString(1, id)
        val result = preparedStatement.executeQuery();
        result.next()
        val name = result.getString(2);
        val author = result.getString(3);
        val isbn = result.getInt(4);
        val book = Book(name,author,isbn,id)
        preparedStatement.close();
        dbConnections.close(connection)
//        return data
        return book
    }
}