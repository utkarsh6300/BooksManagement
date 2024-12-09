package controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.inject.validation.RequiresValidation
import io.micronaut.http.MutableHttpResponse
import services.Book
import services.BookManagement
// exact status are not yet communicated to response.
// error handling needs to be added.
@Controller("/controller")
class bookManagementController : bookManagementControllerInterface {
    private val bookManagement = BookManagement()

    @RequiresValidation
    @Post
    override fun addBook(@Body  book: Book) : HttpResponse<String> {
        return if(bookManagement.addBook(book))
            HttpResponse.created("Book '${book.getName()}' added successfully.")
        else
            HttpResponse.serverError("something went wrong")
    }
    @Delete
    override fun deleteBook(@QueryValue id: String) : HttpResponse<String>  {
        return if (bookManagement.deleteBook(id))
            HttpResponse.ok("Book with id '${id}' deleted successfully.")
        else
            HttpResponse.serverError("something went wrong")
    }
    @Patch("/{id}")
    override fun updateBook(@PathVariable id: String,@Body book: Book) : HttpResponse<String> {
        return if (bookManagement.updateBook(id,book))
            HttpResponse.created("Book '${book.getName()}' updated successfully.")
        else
            HttpResponse.serverError("something went wrong")
    }
    @Get
    override fun getBook(@QueryValue id: String) : HttpResponse<Book?>  {
        //       faced serialization issue here added Serdeable to Book class
        val book = bookManagement.getBook(id)
        return if (book != null) {
            //  200 OK status
             HttpResponse.ok(book)
        } else {
            //  404 Not Found
            HttpResponse.notFound()
        }
    }
}