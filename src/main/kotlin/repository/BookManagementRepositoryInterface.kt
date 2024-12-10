package repository

import services.Book

interface BookManagementRepositoryInterface {
    fun insertBook(book: Book) :Boolean
    fun deleteBook(id: String): Boolean
    fun updateBook(id: String, book: Book): Boolean
    fun getBook(id: String): Book?
}
