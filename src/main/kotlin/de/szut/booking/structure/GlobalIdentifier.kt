package de.szut.booking.structure

import de.moltenKt.core.extension.classType.UUID
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class GlobalIdentifier(
	val type: Type,
	@Contextual val uuid: UUID,
) {

	enum class Type {
		VENDOR, HOTEL, ROOM, BOOKING, USER;
	}

}
