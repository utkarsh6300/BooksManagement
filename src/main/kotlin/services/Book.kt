package services

import jakarta.inject.Singleton
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Singleton
data class Book(private var name: String,private var author: String,private var isbn: Int,private var id: String){
    fun getName(): String {
        return name
    }
    fun getAuthor(): String {
        return author
    }
    fun getIsbn(): Int {
        return isbn
    }
    fun getId(): String {
        return id
    }
}