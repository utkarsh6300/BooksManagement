package com.main.controller

import io.micronaut.http.HttpResponse
import com.main.model.Book

interface BookManagementControllerInterface {
    fun addBook(book: Book) : HttpResponse<String>
    fun deleteBook(id: String) : HttpResponse<String>
    fun updateBook(id: String, book: Book) : HttpResponse<String>
    fun getBook(id: String) :  HttpResponse<Book?>
}