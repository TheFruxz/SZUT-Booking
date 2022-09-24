package de.szut.booking.domain

import de.moltenKt.core.tool.timing.calendar.Calendar
import de.szut.booking.structure.GlobalIdentifier
import de.szut.booking.structure.GloballyIdentifiable
import kotlinx.serialization.Serializable

@Serializable
data class HotelRoom(
	override val globalIdentifier: GlobalIdentifier,
	val hotel: GlobalIdentifier,
	val roomNumber: String,
	val bookedUntil: Calendar?,
	val capacity: HotelRoomCapacity,
) : GloballyIdentifiable {

	@Serializable
	data class HotelRoomCapacity(
		val minimum: Int,
		val recommended: Int,
		val maximum: Int,
	)

}
