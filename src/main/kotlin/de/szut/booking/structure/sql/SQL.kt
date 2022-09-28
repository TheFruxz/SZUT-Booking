package de.szut.booking.structure.sql

import com.mysql.cj.jdbc.Driver
import org.jetbrains.exposed.sql.Database

object SQL {

	val database = Database.connect("jdbc:mysql://localhost:3306/bookings", driver = Driver::class.qualifiedName.also { println(it) } ?: "", user = "superadmin", password = "superadmin")

}