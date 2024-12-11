package com.main.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.inject.validation.RequiresValidation

import com.main.model.Book
import com.main.services.BookManagementService
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

// exact status are not yet communicated to response.
// error handling needs to be added.
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/book")
class BookManagementController : BookManagementControllerInterface {
    private val bookManagementService = BookManagementService()
    @RequiresValidation
    @Post
    override fun addBook(@Body  book: Book) : HttpResponse<String> {
        return if(bookManagementService.addBook(book))
            HttpResponse.created("Book '${book.getName()}' added successfully.")
        else
            HttpResponse.notFound("book with id '${book.getId()}' already exist")
    }
    @Delete
    override fun deleteBook(@QueryValue id: String) : HttpResponse<String>  {
        return if (bookManagementService.deleteBook(id))
            HttpResponse.ok("Book with id '${id}' deleted successfully.")
        else
            HttpResponse.notFound("book with id '${id}' does not exist")
    }
    @Patch("/{id}")
    override fun updateBook(@PathVariable id: String,@Body book: Book) : HttpResponse<String> {
        return if (bookManagementService.updateBook(id,book))
            HttpResponse.created("Book '${book.getName()}' updated successfully.")
        else
            HttpResponse.notFound("book with id '${id}' does not exist")
    }

    @Get
    override fun getBook(@QueryValue id: String) : HttpResponse<Book?>  {
        //       faced serialization issue here added Serdeable to Book class
        val book = bookManagementService.getBook(id)
        return if (book != null) {
            //  200 OK status
             HttpResponse.ok(book)
        } else {
            //  404 Not Found
            HttpResponse.notFound()
        }
    }
}