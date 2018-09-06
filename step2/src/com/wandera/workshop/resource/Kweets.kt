package com.wandera.workshop.resource

import com.wandera.workshop.model.Kweet
import com.wandera.workshop.service.KweetService
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post

fun Route.kweets(service: KweetService) {
    get("/kweets") {
        val userId = call.request.queryParameters["userId"]
        when {
            userId != null -> call.respond(service.getKweetsForUser(userId))
            else -> call.respond(service.listKweets())
        }
    }

    post("/kweets") {
        call.respond(service.postKweet(call.receive(Kweet::class)))
    }

    delete("/kweets/{id}") {
        val deleted = service.deleteKweet(call.parameters["id"] ?: "")
        when (deleted) {
            null -> call.respond(mapOf("id" to "none"))
            else -> call.respond(mapOf("id" to deleted.id))
        }
    }
}