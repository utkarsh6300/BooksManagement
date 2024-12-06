import BookManagement as BookManagement

fun main() {
    println("Hello World!")
    val bookStore = BookManagement(10)
//    bookStore.addBook(Book("x","y",1,"z"))
    bookStore.updateBook("z", Book("a", "b", 2, "z"))
    val book = bookStore.getBook("z")
    println(book?.getId())
    println(book?.getIsbn())
    println(book?.getName())
    println(book?.getAuthor())
    bookStore.deleteBook("z")
}