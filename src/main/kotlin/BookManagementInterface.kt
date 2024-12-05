interface BookManagementInterface {
    fun addBook(name: String,author: String,isbn: Int,id: String)
    fun deleteBook(index:Int):Boolean
    fun updateBook(index:Int,book: Book):Boolean
    fun updateBook(index:Int,name:String,author:String,isbn:Int,id:String):Boolean
    fun getBook(index:Int):Book?
}
