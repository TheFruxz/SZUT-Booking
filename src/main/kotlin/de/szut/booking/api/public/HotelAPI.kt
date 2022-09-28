package de.szut.booking.api.public

import de.moltenKt.core.extension.classType.UUID
import de.moltenKt.core.extension.data.toJsonString
import de.moltenKt.core.extension.tryOrNull
import de.szut.booking.structure.sql.SQL
import de.szut.booking.structure.sql.SQLAdapter
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.random.Random

fun Application.publicAPI() {

	routing {

		get("/public") {
			call.respond(mapOf(
				"getHotel" to "/public/hotel?id=%",
				"listHotels" to "/public/hotel/list",
			))
		}

		get("/public/hotel") {
			val id = tryOrNull { UUID.fromString(call.parameters["id"]) }

			when (id) {
				null -> call.respond(mapOf("missingOrInvalid" to "id"))
				else -> {
					call.respondText(SQLAdapter.getHotel(id)?.toJsonString() ?: "null")
				}
			}

		}

		get("/public/hotel/list") {
			val page = call.parameters["page"]?.toInt() ?: 1
			val format = call.parameters["format"] ?: "short"


			call.respondText {
				when (format.lowercase()) {
					"full" -> SQLAdapter.hotels(page).toJsonString()
					"short" -> SQLAdapter.hotels(page).entries.map { it.globalIdentifier.uuid }.toJsonString()
					else -> "Format '$format' is an invalid format, only 'full' and 'short' are valid formats"
				}
			}

		}

		get("/debug/addHotels") {
			repeat(20) {
				SQLAdapter.addHotel(
					UUID.randomUUID(),
					Random.nextInt().toString(),
					Random.nextInt().toString(),
					Random.nextInt().toString(),
				)
			}
			call.respondText("Successfully added 20 generic entries to the hotel database!")
		}

		get("/debug/dataInformation") {
			call.respondText(SQL.database.dialect.name.takeIf { it.isNotBlank() } ?: "nothing")
		}

	}

}