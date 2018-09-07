package com.wandera.workshop.resource

import com.wandera.workshop.model.Kweet
import com.wandera.workshop.service.KweetService
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*

fun Route.kweets(service: KweetService) {
    route("/kweets") {

        get {
            val userId = call.request.queryParameters["userId"]
            when {
                userId != null -> call.respond(service.getKweetsForUser(userId))
                else -> call.respond(service.listKweets())
            }
        }

        post {
            call.respond(service.postKweet(call.receive(Kweet::class)))
        }

        delete("/{id}") {
            call.respond(mapOf("deleted_records" to service.deleteKweet(call.parameters["id"] ?: "")))
        }

    }
}