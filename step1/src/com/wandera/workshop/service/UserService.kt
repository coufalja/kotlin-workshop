package com.wandera.workshop.service

import com.wandera.workshop.model.User
import java.util.concurrent.ConcurrentHashMap

class UserService {
    private val users = ConcurrentHashMap<String, User>()

    fun getUser(id: String): User? {
        TODO("Return User if found null otherwise")
    }

    fun createOrUpdateUser(user: User): String {
        TODO("Creates user if not exist, updates existing")
    }

    fun listUsers(): List<User> {
        TODO("List all users")
    }
}