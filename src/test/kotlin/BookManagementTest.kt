import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
import kotlin.test.Test

import BookManagement as BookManagement

class BookManagementTest {
    private lateinit var bookStore: BookManagement
    private var sizeOfBooksStore: Int =10
    @BeforeEach
    fun setUp() {
        bookStore = BookManagement(sizeOfBooksStore)
    }
    @Test
    fun `test getting a non-existent book`() {
        val book = bookStore.getBook(0)
        assertNull(book, "The book at index 0 should be null as no book is added yet")
    }
    @Test
    fun `test adding a book in store`() {
        bookStore.addBook("The Concise 48 Laws Of Power","The Robert Greene Collection",893748263,"ns6ij1qn76g%$@&^@*HH0VGHh7jx2vs")
        val book = bookStore.getBook(0)
        assertNotNull(book,"book should exist")
        assertAll("book",
            { assertEquals("The Concise 48 Laws Of Power", book?.getName()) },
            { assertEquals("The Robert Greene Collection", book?.getAuthor()) },
            { assertEquals(893748263, book?.getIsbn()) },
            { assertEquals("ns6ij1qn76g%$@&^@*HH0VGHh7jx2vs", book?.getId()) }
        )
    }
    @Test
    fun `test updating a book`() {
        bookStore.addBook("The Concise 48 Laws Of Power","The Robert Greene Collection",893748263,"ns6ij1qn76g%$@&^@*HH0VGHh7jx2vs")
        val updatedBook = Book("The Concise 48 Laws Of Power","The Robert Greene Collection",123456789,"rextcvgns6ij1qn76")

        bookStore.updateBook(0, updatedBook)
        val book = bookStore.getBook(0)

        assertNotNull(book, "The book should exist after update")
        assertAll("book",
            { assertEquals("The Concise 48 Laws Of Power", book?.getName()) },
            { assertEquals("The Robert Greene Collection", book?.getAuthor()) },
            { assertEquals(123456789, book?.getIsbn()) },
            { assertEquals("rextcvgns6ij1qn76", book?.getId()) }
        )
    }
    @Test
    fun `test updating a book in store by attributes`() {
        bookStore.addBook("The Concise 48 Laws Of Power","The Robert Greene Collection",893748263,"ns6ij1qn76g%$@&^@*HH0VGHh7jx2vs")
        bookStore.updateBook(0,"The Concise 48 Laws Of Power","The Robert Greene Collection",123456789,"rextcvgns6ij1qn76")
        val book = bookStore.getBook(0)
        assertNotNull(book,"book should exist")
        assertAll("book",
            { assertEquals(123456789, book?.getIsbn()) },
            { assertEquals("rextcvgns6ij1qn76", book?.getId()) }
        )
    }
    fun `test deleting a book`() {
        bookStore.addBook("The Great Gatsby", "F. Scott Fitzgerald", 123456789, "1")
        bookStore.addBook("1984", "George Orwell", 987654321, "2")

        assertNotNull(bookStore.getBook(0), "Book at index 0 should exist")
        assertNotNull(bookStore.getBook(1), "Book at index 1 should exist")

        bookStore.deleteBook(0)

        // After deletion, index 1 should be null as the array will shift
        assertNull(bookStore.getBook(1), "Book at index 1 should be null after deletion")
        assertNotNull(bookStore.getBook(0), "Book at index 0 should still exist")

    }
    fun `test operations on out of bounds index`() {
        val book = bookStore.getBook(-1)
        assertNull(book, "The book at invalid index -1 should be null")

        val book2 = bookStore.getBook(sizeOfBooksStore*100)
        assertNull(book2, "The book at invalid index should be null")

        // needs to be added for delete and update
    }
}