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
import io.ktor.routing.route

fun Route.users(service: UserService) {

    route("/users") {

        get("/{id?}") {
            val id = call.parameters["id"]
            when {
                id != null -> {
                    val user = service.getUser(id)
                    when {
                        user != null -> call.respond(user)
                        else -> call.respond(HttpStatusCode.NotFound)
                    }
                }
                else -> call.respond(service.listUsers())
            }
        }

        post {
            call.respond(service.createOrUpdateUser(call.receive(User::class)))
        }
    }


}