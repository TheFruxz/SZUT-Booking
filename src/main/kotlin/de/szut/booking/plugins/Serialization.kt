package de.szut.booking.plugins

import de.moltenKt.core.extension.data.jsonBase
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.configureSerialization() {
	install(ContentNegotiation) {
		json(jsonBase)
	}

	routing {
		get("/json/kotlinx-serialization") {
			call.respond(mapOf("hello" to "world", "test" to "wow", "max" to "power"))
		}
	}
}
