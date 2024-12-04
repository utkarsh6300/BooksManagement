import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import BookManagement as BookManagement

class BookManagementTest {
    private lateinit var bookManagement: BookManagement
    @BeforeEach
    fun setUp() {
        bookManagement = BookManagement(10)
    }
    @Test
    fun `test getting a non-existent book`() {
        val book = bookManagement.getBook(0)
        assertNull(book, "The book at index 0 should be null as no book is added yet")
    }
}