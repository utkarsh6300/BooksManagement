package com.main.services

import com.main.model.Book


interface BookManagementServiceInterface {
    fun addBook(book: Book) : Boolean
    fun deleteBook(id:String) : Boolean
    fun updateBook(id:String,book: Book) : Boolean
    fun getBook(id:String) : Book?
}
