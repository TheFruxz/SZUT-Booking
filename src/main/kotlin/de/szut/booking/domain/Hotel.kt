package de.szut.booking.domain

import de.szut.booking.structure.GlobalIdentifier
import de.szut.booking.structure.GloballyIdentifiable
import kotlinx.serialization.Serializable

@Serializable
data class Hotel(
	override val globalIdentifier: GlobalIdentifier,
	val vendor: GlobalIdentifier,
	val name: String,
	val address: String,
	val description: String,
	val rooms: List<GlobalIdentifier>,
) : GloballyIdentifiable
