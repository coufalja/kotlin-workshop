package com.wandera.workshop.service

import com.wandera.workshop.model.Kweet
import java.util.concurrent.ConcurrentHashMap

class KweetService {
    private val kweets = ConcurrentHashMap<String, Kweet>()

    fun getKweet(id: String): Kweet? {
        TODO("Return Kweet if found null otherwise")
    }

    fun postKweet(kweet: Kweet): String {
        TODO("Create Kweet if not exists throws DuplicateIdException otherwise")
    }

    fun deleteKweet(id: String): Kweet? {
        TODO("Delete Kweet")
    }

    fun getKweetsForUser(userId: String): List<Kweet> {
        TODO("List Kweets that belongs to specific User")
    }
}