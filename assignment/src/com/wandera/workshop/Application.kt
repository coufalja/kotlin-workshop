package com.wandera.workshop

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing

fun main(args: Array<String>): Unit = io.ktor.server.netty.DevelopmentEngine.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.main() {
    routing {
        get("/*") { call.respond(HttpStatusCode.NotFound) }
    }
}


