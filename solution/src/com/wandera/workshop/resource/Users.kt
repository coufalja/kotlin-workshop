package com.wandera.workshop.resource

import com.wandera.workshop.model.User
import com.wandera.workshop.service.UserService
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post

fun Route.users(service: UserService) {

    get("/users/{id?}") {
        val id = call.parameters["id"]
        when {
            id != null -> {
                val user = service.getUser(id)
                if (user != null) {
                    call.respond(user)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
            else -> call.respond(service.listUsers())
        }
    }

    get("/users") {
        call.respond(service.listUsers())
    }

    post("/users") {
        call.respond(service.createOrUpdateUser(call.receive(User::class)))
    }

}