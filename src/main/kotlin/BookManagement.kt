import Book as Book
import  BookManagementInterface as BookManagementInterface

class BookManagement(size:Int): BookManagementInterface {
    private var books: Array<Book?> = arrayOfNulls(size)
    override fun addBook(name: String,author: String,isbn: Int,id: String){
        // when array is full that condition needs to be checked
        val newBook = Book(name,author,isbn,id)
        books[books.indexOfFirst { it == null }] = newBook
    }
    // indexes will change in Array
    override fun deleteBook(index:Int):Boolean{
        if (index in books.indices) {
            // Shift elements to the left to fill the gap after removal
            for (i in index until books.size - 1) {
                books[i] = books[i + 1]
            }
            books[books.size - 1] = null // Clear last element
            return true
        }
        return false
    }
    override fun updateBook(index:Int,book:Book):Boolean{
        if (index in books.indices) {
            books[index] = book
            return true
        }
        return false
    }
    override fun updateBook(index:Int,name:String,author:String,isbn:Int,id:String):Boolean{
        if (index in books.indices) {
            val updatedBook = Book(name, author, isbn, id)
            books[index] = updatedBook
            return true
        }
        return false
    }

    override fun getBook(index:Int):Book?{
        return if (index in books.indices) books[index] else null
    }
}

// if conditions are copied