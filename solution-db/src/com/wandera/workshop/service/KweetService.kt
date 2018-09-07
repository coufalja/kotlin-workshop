package com.wandera.workshop.service

import com.wandera.workshop.model.DuplicateIdException
import com.wandera.workshop.model.Kweet
import com.wandera.workshop.model.Kweets
import com.wandera.workshop.service.DatabaseFactory.dbQuery
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class KweetService {

    suspend fun getKweet(id: String): Kweet? = dbQuery {
        Kweets.select {
            Kweets.id eq id
        }.map { Kweet(it) }.firstOrNull()
    }

    suspend fun postKweet(kweet: Kweet): String = dbQuery {
        try {
            Kweets.insert {
                it[id] = kweet.id
                it[userId] = kweet.userId
                it[text] = kweet.text
                it[date] = kweet.date.toEpochMilli()
            }
            kweet.id
        } catch (ex: ExposedSQLException) {
            if ("Unique index or primary key violation" in ex.message!!) {
                throw DuplicateIdException()
            }
            throw ex
        }
    }

    suspend fun deleteKweet(id: String): Int = dbQuery {
        Kweets.deleteWhere { Kweets.id eq id }
    }

    suspend fun listKweets(): List<Kweet> = dbQuery {
        Kweets.selectAll().map { Kweet(it) }
    }

    suspend fun getKweetsForUser(userId: String): List<Kweet> = dbQuery {
        Kweets.select { Kweets.userId eq userId }.map { Kweet(it) }
    }
}