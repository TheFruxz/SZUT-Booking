package de.szut.booking

import de.szut.booking.api.public.publicAPI
import de.szut.booking.plugins.configureRouting
import de.szut.booking.plugins.configureSecurity
import de.szut.booking.plugins.configureSerialization
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
	embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
		configureSecurity()
		configureSerialization()
		configureRouting()
		publicAPI()
	}.start(wait = true)
}
