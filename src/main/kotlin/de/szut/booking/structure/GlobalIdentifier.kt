package de.szut.booking.structure

import kotlinx.serialization.Serializable

@Serializable
data class GlobalIdentifier(
	val type: Type,
	val uuid: String,
) {

	enum class Type {
		VENDOR, HOTEL, ROOM, BOOKING, USER;
	}

}
