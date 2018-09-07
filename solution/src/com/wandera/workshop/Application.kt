package com.wandera.workshop

import com.fasterxml.jackson.databind.SerializationFeature
import com.wandera.workshop.resource.kweets
import com.wandera.workshop.resource.users
import com.wandera.workshop.service.KweetService
import com.wandera.workshop.service.UserService
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.html.respondHtml
import io.ktor.jackson.jackson
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.netty.DevelopmentEngine
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.title

fun main(args: Array<String>): Unit = DevelopmentEngine.main(args)

private val userService = UserService()
private val kweetService = KweetService()

@Suppress("unused") // Referenced in application.conf
fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        get("/") {
            call.respondHtml {
                head {
                    title("Kwitter")
                }
                body {
                    h1 { +"Welcome to Kwitter" }
                }
            }
        }
        kweets(kweetService)
        users(userService)
    }
}




