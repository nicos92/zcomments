package utils

object MisCharsets {
    val mischarsets =
        mapOf("ISO_8859_1 o ANSI" to Charsets.ISO_8859_1,
            "US_ASCII" to Charsets.US_ASCII,
            "UTF_8" to Charsets.UTF_8,
            "UTF_16" to Charsets.UTF_16,
            "UTF_16BE" to Charsets.UTF_16BE,
            "UTF_16LE" to Charsets.UTF_16LE,
            "UTF_32" to Charsets.UTF_32,
            "UTF_32BE" to  Charsets.UTF_32BE,
            "UTF_32LE" to Charsets.UTF_32LE)

    val delimeters= mapOf("//" to "//", "--" to "--", "-" to "-", ";" to ";", ":" to ":", " " to " ", "_" to "_",  "/" to "/")
}