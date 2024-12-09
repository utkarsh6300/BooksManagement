import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
import kotlin.test.Test
import Book as Book

class BookTest {
    private lateinit var book: Book
    private var name: String= "The Concise 48 Laws Of Power"
    private var author: String="The Robert Greene Collection"
    private var isbn: Int= 893748263
    private var id: String= "ns6ij1qn76g%$@&^@*HH0VGHh7jx2vs"
    @BeforeEach
    fun setUp() {
        book = Book(name,author,isbn,id)
    }
    @Test
    fun `test setting correct name`() {
        val result = book.getName()
        assertEquals(name, result)
    }
    @Test
    fun `test setting correct author`() {
        val result = book.getAuthor()
        assertEquals(author, result)
    }
    @Test
    fun `test setting correct isbn`() {
        val result = book.getIsbn()
        assertEquals(isbn, result)
    }
    @Test
    fun `test setting correct id`() {
        val result = book.getId()
        assertEquals(id, result)
    }
    @Test
    fun `test book properties`() {
        // Test all properties at once
        assertAll("controller",
            { assertEquals("The Concise 48 Laws Of Power", book.getName()) },
            { assertEquals("The Robert Greene Collection", book.getAuthor()) },
            { assertEquals(893748263, book.getIsbn()) },
            { assertEquals("ns6ij1qn76g%$@&^@*HH0VGHh7jx2vs", book.getId()) }
        )
    }
}