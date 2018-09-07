package com.wandera.workshop.resource

import com.wandera.workshop.service.KweetService
import io.ktor.routing.*

fun Route.kweets(service: KweetService) {
    route("/kweets") {

        get {
            TODO("""
                List all kweets or Kweets for specific user if queryParameter is present
                use: call.request.queryParameters["userId"] to obtain queryPram value
                """.trimIndent())
        }

        post {
            TODO("""
                Create the Kweet
                use: call.receive(Kweet::class) to obtain parsed POST body
                """.trimIndent())
        }

        delete("/{id}") {
            TODO("""
                Delete the Kweet based on id
                use: call.parameters["id"] to obtain path parameters
                you can also try responding with simple Json object using e.g. call.respond(mapOf("id" to deleted.id))
                """.trimIndent())
        }

    }
}