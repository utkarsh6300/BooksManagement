package controller

import io.micronaut.http.HttpResponse
import services.Book

interface bookManagementControllerInterface {
    fun addBook(book: Book) : HttpResponse<String>
    fun deleteBook(id: String) : HttpResponse<String>
    fun updateBook(id: String, book: Book) : HttpResponse<String>
    fun getBook(id: String) :  HttpResponse<Book?>
}