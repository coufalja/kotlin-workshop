package com.wandera.workshop.model

import com.wandera.workshop.randomId
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import java.time.Instant

object Kweets : Table() {
    val id = varchar("id", 255).primaryKey()
    val userId = varchar("userId", 255)
    val text = text("text")
    val date = long("date")
}

data class Kweet(val id: String = randomId(), val userId: String, val text: String = "", val date: Instant = Instant.now()) {
    constructor(row: ResultRow) : this(
            id = row[Kweets.id],
            userId = row[Kweets.userId],
            text = row[Kweets.text],
            date = Instant.ofEpochMilli(row[Kweets.date])
    )
}