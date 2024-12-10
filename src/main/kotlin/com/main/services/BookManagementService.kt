package com.main.services
import repository.BookManagementRepository as BookManagementRepository


// store data in DB Postgres
// error handling
class BookManagementService : BookManagementServiceInterface {
    private val bookManagementRepository = BookManagementRepository()
    override fun addBook(book: Book) : Boolean {
       return bookManagementRepository.insertBook(book)
    }
    override fun deleteBook(id: String): Boolean {
        return bookManagementRepository.deleteBook(id)
    }
    override fun updateBook(id: String, book: Book): Boolean {
        val cleanedId = id.trim('"')
        return bookManagementRepository.updateBook(cleanedId,book)
    }
    override fun getBook(id: String): Book? {
        return bookManagementRepository.getBook(id)
    }
}