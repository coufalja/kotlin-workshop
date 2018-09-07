package com.wandera.workshop.model

import com.wandera.workshop.randomId
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = varchar("id", 255).primaryKey()
    val name = varchar("name", 255)
}

data class User(val id: String = randomId(), val name: String) {
    constructor(row: ResultRow) : this(
            id = row[Users.id],
            name = row[Users.name]
    )
}