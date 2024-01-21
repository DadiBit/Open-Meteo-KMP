package com.openmeteo.sdk.common

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.serialization.Serializable

interface Query {

    interface Coordinates {
        val latitude: QueryList<Float>
        val longitude: QueryList<Float>
    }

    interface Elevations {
        val elevation: QueryList<Float>?
    }

    interface TimeZones {
        val timezone: QueryList<TimeZone>?
    }

    interface DateFrames {
        val start_date: QueryList<LocalDate>?
        val end_date: QueryList<LocalDate>?
    }

    interface HourFrames {
        val start_hour: QueryList<LocalDateTime>?
        val end_hour: QueryList<LocalDateTime>?
    }

    interface Minutely15Frames {
        val start_minutely_15: QueryList<LocalDateTime>?
        val end_minutely_15: QueryList<LocalDateTime>?
    }

    interface TimeFormat

    interface Daily<V> : TimeFormat {
        val daily: QueryList<V>
    }

    interface Hourly<V> : TimeFormat {
        val hourly: QueryList<V>
    }

    interface Minutely15<V> : TimeFormat {
        val minutely_15: QueryList<V>
    }

    interface CurrentWeather<V> : TimeFormat {
        val current: QueryList<V>
    }

    interface PastDays : TimeFormat {
        val past_days: Int?
    }

    interface ForecastDays : TimeFormat {
        val forecast_days: Int?
    }

    interface PastHours : TimeFormat {
        val past_hours: Int?
    }

    interface ForecastHours : TimeFormat {
        val forecast_hours: Int?
    }

    interface PastMinutely15 : TimeFormat {
        val past_minutely_15: Int?
    }

    interface ForecastMinutely15 : TimeFormat {
        val forecast_minutely_15: Int?
    }

    interface ContentFormat {
        val format: com.openmeteo.sdk.common.ContentFormat?
    }

    interface CommercialLicense {
        val apikey: String?
    }

}
