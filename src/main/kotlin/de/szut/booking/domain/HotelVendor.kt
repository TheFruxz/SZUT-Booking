package de.szut.booking.domain

import de.szut.booking.structure.GlobalIdentifier
import de.szut.booking.structure.GloballyIdentifiable
import kotlinx.serialization.Serializable

@Serializable
data class HotelVendor(
	override val globalIdentifier: GlobalIdentifier,
	val name: String,
	val mail: String,
	val hotels: GlobalIdentifier,
) : GloballyIdentifiable
