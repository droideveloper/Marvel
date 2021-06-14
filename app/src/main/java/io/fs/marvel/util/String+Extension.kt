package io.fs.marvel.util

import java.math.BigInteger
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

private const val parseFormat = "yyyy-MM-dd'T'HH:mm:ssZ"
private const val stringFormat = "dd MMM yy"

fun String.toDate(formatString: String = parseFormat): Date {
    val parser = SimpleDateFormat(formatString, Locale.getDefault())
    return parser.parse(this) ?: Date()
}

fun Date.toDateString(formatString: String = stringFormat): String {
    val formatter = SimpleDateFormat(formatString, Locale.getDefault())
    return formatter.format(this)
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(this.toByteArray())).toString(16).padStart(32, '0')
}