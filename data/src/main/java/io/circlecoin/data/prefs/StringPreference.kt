package io.circlecoin.data.prefs

import android.content.SharedPreferences

class StringPreference @JvmOverloads constructor(private val preferences: SharedPreferences, private val key: String,
    private val defaultValue: String? = null) {

  val isSet: Boolean
    get() = preferences.contains(key)

  fun get(): String? {
    return preferences.getString(key, defaultValue)
  }

  fun set(value: String) {
    preferences.edit().putString(key, value).apply()
  }

  fun delete() {
    preferences.edit().remove(key).apply()
  }
}
