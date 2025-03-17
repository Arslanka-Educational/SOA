package org.example.com.ifmo.se.route.management.data.mappers

import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar
import java.time.OffsetDateTime

internal fun offsetDateTimeToXmlGregorianCalendar(offsetDateTime: OffsetDateTime): XMLGregorianCalendar {
    val instant = offsetDateTime.toInstant()
    val calendar = java.util.GregorianCalendar.from(instant.atZone(java.time.ZoneOffset.UTC))
    return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar)
}

internal fun xmlGregorianCalendarToOffsetDateTime(xmlGregorianCalendar: XMLGregorianCalendar): OffsetDateTime {
    val calendar = xmlGregorianCalendar.toGregorianCalendar()
    return calendar.toZonedDateTime().toOffsetDateTime()
}