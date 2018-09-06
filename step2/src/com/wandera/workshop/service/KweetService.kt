package com.wandera.workshop.service

import com.wandera.workshop.model.DuplicateIdException
import com.wandera.workshop.model.Kweet
import java.util.concurrent.ConcurrentHashMap

class KweetService {
    private val kweets = ConcurrentHashMap<String, Kweet>()

    fun getKweet(id: String): Kweet? = kweets[id]

    fun postKweet(kweet: Kweet): String {
        val result = kweets.putIfAbsent(kweet.id, kweet)
        when {
            result != null -> throw DuplicateIdException()
            else -> return kweet.id
        }
    }

    fun deleteKweet(id: String): Kweet? = kweets.remove(id)
    fun listKweets(): List<Kweet> = kweets.values.toList()
    fun getKweetsForUser(userId: String): List<Kweet> = kweets.filter { it.value.userId == userId }.values.toList()
}