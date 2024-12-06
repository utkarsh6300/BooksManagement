class Book(name: String,author: String,isbn: Int,id: String){  //should be a data class in kotlin
    private var _name: String=name
    private var _author: String=author
    private var _isbn: Int=isbn
    private var _id: String=id
    fun getName(): String {
        return _name
    }
    fun getAuthor(): String {
        return _author
    }
    fun getIsbn(): Int {
        return _isbn
    }
    fun getId(): String {
        return _id
    }
}