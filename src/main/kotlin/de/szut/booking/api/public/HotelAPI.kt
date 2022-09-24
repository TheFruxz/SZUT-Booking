package de.szut.booking.api.public

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.publicAPI() {

	routing {

		get("/public") {
			call.respond(mapOf(
				"getHotel" to "/public/hotel?id=%"
			))
		}

		get("/public/hotel") {
			val id = call.parameters["id"]

			when (id) {
				null -> call.respond(mapOf("missing" to "id"))
				else -> {

				}
			}

		}
	}

}