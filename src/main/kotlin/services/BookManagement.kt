package services
import repository.BookRepository as BookRepository


// store data in DB Postgres
// skipping error handling
// temporarily used trim to fix issue in getting path and query variable needs to be fixed
class BookManagement : BookManagementInterface {
    private val bookRepository = BookRepository()
    override fun addBook(book: Book) : Boolean {
       return bookRepository.insertBook(book)
    }
    override fun deleteBook(id: String): Boolean {
        val cleanedId = id.trim('"')
        return bookRepository.deleteBook(cleanedId)
    }
    override fun updateBook(id: String, book: Book): Boolean {
        val cleanedId = id.trim('"')
        return bookRepository.updateBook(cleanedId,book)
    }
    override fun getBook(id: String): Book? {
        val cleanedId = id.trim('"')
        return bookRepository.getBook(cleanedId)
    }
}