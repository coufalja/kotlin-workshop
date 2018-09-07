package com.wandera.workshop.resource

import com.wandera.workshop.service.UserService
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.users(service: UserService) {

    route("/users") {

        get("/{id?}") {
            TODO("""
                List all users if parameter id is missing, return specific one if present and 404 if not found
                use: call.parameters["id"] to get the id parameter value
                """.trimIndent())
        }

        post {
            TODO("""
                Create the User
                use: call.receive(User::class) to obtain parsed POST body
                """.trimIndent())
        }
    }


}