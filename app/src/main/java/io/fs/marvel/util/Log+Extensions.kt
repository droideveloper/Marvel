package io.fs.marvel.util

import android.util.Log
import io.fs.marvel.BuildConfig
import java.io.PrintWriter
import java.io.StringWriter

// log extensions
inline fun <reified T> T.log(message: String) = log(Log.DEBUG, message)

inline fun <reified T> T.log(level: Int, message: String) {
  if (isLogEnabled()) {
    Log.println(level, getClassTag(), message)
  }
}

inline fun <reified T> T.log(error: Throwable) {
  val sw = StringWriter()
  val pw = PrintWriter(sw)
  error.printStackTrace(pw)
  log(Log.ERROR, sw.toString())
}

inline fun <reified T> T.isLogEnabled(): Boolean = BuildConfig.DEBUG
inline fun <reified T> T.getClassTag(): String = T::class.java.simpleName