package de.szut.booking.structure.sql

import de.moltenKt.core.extension.classType.UUID
import de.moltenKt.core.extension.data.fromJsonString
import de.moltenKt.core.extension.data.toJsonString
import de.moltenKt.core.extension.tryOrNull
import de.moltenKt.core.extension.tryToPrint
import de.szut.booking.domain.Hotel
import de.szut.booking.structure.GlobalIdentifier
import de.szut.booking.structure.GlobalIdentifier.Type.*
import de.szut.booking.structure.sql.SQLTable.SQLHotelTable
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object SQLAdapter {

	const val ENTRIES_PER_PAGE = 50

	fun hotels(page: Int) = transaction(SQL.database) {
		SchemaUtils.create(SQLHotelTable)
		HotelQuery(
			page = page,
			entries = SQLHotelTable
				.selectAll()
				.limit(ENTRIES_PER_PAGE, (ENTRIES_PER_PAGE.toLong() * (page - 1)))
				.map {
					Hotel(
						globalIdentifier = GlobalIdentifier(HOTEL, it[SQLHotelTable.uuid].toString()),
						vendor = GlobalIdentifier(VENDOR, it[SQLHotelTable.vendor].toString()),
						name = it[SQLHotelTable.name],
						address = it[SQLHotelTable.address],
						description = it[SQLHotelTable.description],
						rooms = it[SQLHotelTable.rooms].fromJsonString()
					)
				}
		)
	}

	fun addHotel(
		vendor: UUID,
		name: String,
		address: String,
		description: String,
		identity: UUID = UUID.randomUUID(),
		rooms: List<UUID> = emptyList(),
	) = transaction(SQL.database) {
		try {
			SchemaUtils.create(SQLHotelTable)
			Hotel(
				globalIdentifier = GlobalIdentifier(HOTEL, identity.toString()),
				vendor = GlobalIdentifier(VENDOR, vendor.toString()),
				name = name,
				address = address,
				description = description,
				rooms = rooms.map { GlobalIdentifier(ROOM, it.toString()) }
			).let {
				SQLHotelTable
					.insert {
						it[SQLHotelTable.uuid] = identity
						it[SQLHotelTable.vendor] = vendor
						it[SQLHotelTable.name] = name
						it[SQLHotelTable.address] = address
						it[SQLHotelTable.description] = description
						it[SQLHotelTable.rooms] = rooms.toString()
					}
			}
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}

	fun getHotel(
		identity: UUID,
	) = transaction(SQL.database) {
		SchemaUtils.create(SQLHotelTable)
		SQLHotelTable
			.select({ SQLHotelTable.uuid eq identity })
			.firstOrNull()
			?.let {
				Hotel(
					globalIdentifier = GlobalIdentifier(HOTEL, it[SQLHotelTable.uuid].toString()),
					vendor = GlobalIdentifier(VENDOR, it[SQLHotelTable.vendor].toString()),
					name = it[SQLHotelTable.name],
					address = it[SQLHotelTable.address],
					description = it[SQLHotelTable.description],
					rooms = it[SQLHotelTable.rooms].fromJsonString(),
				)
			}
	}

	@Serializable
	data class HotelQuery(
		val page: Int,
		val entries: List<Hotel>,
	)

}