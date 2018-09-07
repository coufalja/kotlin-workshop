package com.wandera.workshop.service

import com.wandera.workshop.model.User
import java.util.concurrent.ConcurrentHashMap

class UserService {
    private val users = ConcurrentHashMap<String, User>()

    fun getUser(id: String) = users[id]

    fun createOrUpdateUser(user: User): String {
        users[user.id] = user
        return user.id
    }

    fun listUsers(): List<User> = users.values.toList()
}