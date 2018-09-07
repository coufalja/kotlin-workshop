package com.wandera.workshop.service

import com.wandera.workshop.model.User
import com.wandera.workshop.model.Users
import com.wandera.workshop.service.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class UserService {

    suspend fun getUser(id: String): User? = dbQuery {
        Users.select {
            Users.id eq id
        }.map { User(it) }.firstOrNull()
    }

    suspend fun createOrUpdateUser(user: User): String = dbQuery {
        if (Users.slice(Users.id).select { Users.id eq user.id }.empty()) {
            Users.insert {
                it[id] = user.id
                it[name] = user.name
            }
        } else {
            Users.update({ Users.id eq user.id }) {
                it[id] = user.id
                it[name] = user.name
            }
        }
        user.id
    }

    suspend fun listUsers(): List<User> = dbQuery {
        Users.selectAll().map { User(it) }
    }
}