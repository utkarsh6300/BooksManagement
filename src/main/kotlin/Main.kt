import BookManagement as BookManagement
fun main() {
    println("Hello World!")
    val bookStore = BookManagement(10)
    bookStore.addBook("x","y",1,"z")
    bookStore.updateBook(0,"a","b",2,"c")
    bookStore.getBook(0)
    bookStore.deleteBook(0)
}