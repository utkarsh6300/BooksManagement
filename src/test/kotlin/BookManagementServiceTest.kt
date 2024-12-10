import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
import kotlin.test.Test

import com.main.services.BookManagementService as BookManagement
import services.Book as Book

// changed according to the databases

class BookManagementServiceTest {
    private lateinit var bookStore: BookManagement
    @BeforeEach
    fun setUp() {
        bookStore = BookManagement()
    }
    @Test
    fun `test getting a non-existent book`() {
        val book = bookStore.getBook("ns6ij1qn76g%$@&^@*HH")
        assertNull(book, "The book should be null as no book is added yet")
    }
    @Test
    fun `test adding a book in store`() {
        bookStore.addBook(Book("The Concise 48 Laws Of Power","The Robert Greene Collection",893748263,"ns6ij1qn7&^@*HH0VGHh7jx2vs"))
        val book = bookStore.getBook("ns6ij1qn7&^@*HH0VGHh7jx2vs")
        println(book)
        assertNotNull(book,"book should exist")
        assertAll("com/main/controller",
            { assertEquals("The Concise 48 Laws Of Power", book?.getName()) },
            { assertEquals("The Robert Greene Collection", book?.getAuthor()) },
            { assertEquals(893748263, book?.getIsbn()) },
            { assertEquals("ns6ij1qn7&^@*HH0VGHh7jx2vs", book?.getId()) }
        )
    }
    @Test
    fun `test updating a book`() {
        bookStore.addBook(Book("The Concise 48 Laws Of Power","The Robert Greene Collection",893748263,"ns6g%$@&^@*HH0VGHh7jx2vs"))
        val updatedBook = Book("The Concise 48 Laws Of Power","The Robert Greene Collection",123456789,"ns6g%$@&^@*HH0VGHh7jx2vs")

        bookStore.updateBook("ns6g%$@&^@*HH0VGHh7jx2vs", updatedBook)
        val book = bookStore.getBook("ns6g%$@&^@*HH0VGHh7jx2vs")

        assertNotNull(book, "The book should exist after update")
        assertAll("com/main/controller",
            { assertEquals("The Concise 48 Laws Of Power", book?.getName()) },
            { assertEquals("The Robert Greene Collection", book?.getAuthor()) },
            { assertEquals(123456789, book?.getIsbn()) }
        )
    }

    // remaining these
    @Test
    fun `test deleting a book`() {
        bookStore.addBook(Book("The Great Gatsby", "F. Scott Fitzgerald", 123456789, "1"))
        bookStore.addBook(Book("1984", "George Orwell", 987654321, "2"))

        assertNotNull(bookStore.getBook("1"), "service.Book at index 0 should exist")
        assertNotNull(bookStore.getBook("2"), "service.Book at index 1 should exist")

        bookStore.deleteBook("1")

        assertNull(bookStore.getBook("1"), "service.Book with id 1 should be null after deletion")
        assertNotNull(bookStore.getBook("2"), "service.Book with id 0 should still exist")

    }
    @Test
    fun `test operations on non existing data`() {
        assertEquals(false,bookStore.deleteBook("3"),"delete should not be successful in non existing book")
        assertEquals(false,bookStore.updateBook("3",Book("The Concise 48 Laws Of Power","The Robert Greene Collection",123456789,"rextcvgns6ij1qn76")),"update should not be successful in non existing book")
        assertEquals(false,bookStore.updateBook("3",Book("The Concise 48 Laws Of Power","The Robert Greene Collection",123456789,"rextcvgns6ij1qn76")),"update should not be successful in non existing book")
    }
}