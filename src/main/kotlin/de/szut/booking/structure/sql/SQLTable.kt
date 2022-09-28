package de.szut.booking.structure.sql

import org.jetbrains.exposed.sql.Table

object SQLTable {

	object SQLHotelTable : Table("hotels") {
		val uuid = uuid("identity")
		val vendor = uuid("vendor")
		val name = text("name")
		val address = text("address")
		val description = text("description")
		val rooms = text("rooms")
	}

}