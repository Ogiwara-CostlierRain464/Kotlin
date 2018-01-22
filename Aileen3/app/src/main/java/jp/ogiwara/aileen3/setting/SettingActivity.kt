package jp.ogiwara.aileen3.setting

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import jp.ogiwara.aileen3.R

class SettingActivity: Activity(), SharedPreferences.OnSharedPreferenceChangeListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_Holo_Light)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.registerOnSharedPreferenceChangeListener(this)

        fragmentManager.beginTransaction()
                .replace(android.R.id.content,SettingsFragment())
                .commit()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {

    }

    class SettingsFragment: PreferenceFragment(){
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.preferences)
        }
    }
}