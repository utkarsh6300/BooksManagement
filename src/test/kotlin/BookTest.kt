import org.junit.Assert.assertEquals
import org.junit.Test
import Book as Book
class BookTest {
    private lateinit var book: Book
    private var name: String= "The Concise 48 Laws Of Power"
    private var author: String="The Robert Greene Collection"
    private var isbn: Int= 893748263
    private var id: String= "ns6ij1qn76g%$@&^@*HH0VGHh7jx2vs"
    @Before
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
}