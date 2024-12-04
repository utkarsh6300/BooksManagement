import Book as Book

interface BookManagementInterface {
    fun addBook(name: String,author: String,isbn: Int,id: String)
    fun deleteBook(index:Int)
    fun updateBook(index:Int,book: Book)
    fun updateBook(index:Int,name:String,author:String,isbn:Int,id:String)
    fun getBook(index:Int):Book?
}

class BookManagement(size:Int):BookManagementInterface {
    private var books: Array<Book?> = arrayOfNulls(size)
    override fun addBook(name: String,author: String,isbn: Int,id: String){
        // when array is full that condition needs to be checked
        val newBook = Book(name,author,isbn,id)
        books[books.indexOfFirst { it == null }] = newBook
    }
    // indexes will change in Array
    override fun deleteBook(index:Int){
        if (index in books.indices) {
            // Shift elements to the left to fill the gap after removal
            for (i in index until books.size - 1) {
                books[i] = books[i + 1]
            }
            books[books.size - 1] = null // Clear last element
        }
    }
    override fun updateBook(index:Int,book:Book){
        if (index in books.indices) {
            books[index] = book
        }
    }
    override fun updateBook(index:Int,name:String,author:String,isbn:Int,id:String){
        if (index in books.indices) {
            val updatedBook = Book(name, author, isbn, id)
            books[index] = updatedBook
        }
    }

    override fun getBook(index:Int):Book?{
        return if (index in books.indices) books[index] else null
    }
}

// if conditions are copied