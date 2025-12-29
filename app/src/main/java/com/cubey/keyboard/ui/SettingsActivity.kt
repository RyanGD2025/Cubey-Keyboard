package com.cubey.keyboard.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cubey.keyboard.databinding.ActivitySettingsBinding
import com.cubey.keyboard.data.PreferencesStore
import com.cubey.keyboard.domain.ThemeManager
import com.cubey.keyboard.domain.FontManager

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private val prefs by lazy { PreferencesStore(this) }
    private val themeManager by lazy { ThemeManager(prefs) }
    private val fontManager by lazy { FontManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(com.cubey.keyboard.R.string.settings_title)

        val themes = listOf(
            getString(com.cubey.keyboard.R.string.theme_light),
            getString(com.cubey.keyboard.R.string.theme_dark),
            getString(com.cubey.keyboard.R.string.theme_colorful)
        )

        val fonts = listOf("Roboto-Regular.ttf", "Sans.ttf", "Monospace.ttf")

        // Theme selection (RadioButtons text set in XML via strings)
        binding.themeLight.setOnClickListener { prefs.setTheme(themes[0]) }
        binding.themeDark.setOnClickListener { prefs.setTheme(themes[1]) }
        binding.themeColorful.setOnClickListener { prefs.setTheme(themes[2]) }

        // Font dropdown
        binding.fontDropdown.setSimpleItems(fonts.toTypedArray())
        binding.fontDropdown.setOnItemClickListener { _, _, pos, _ ->
            prefs.setFont(fonts[pos])
        }

        binding.btnApply.text = getString(com.cubey.keyboard.R.string.apply_button)
        binding.btnApply.setOnClickListener {
            themeManager.notifyThemeChanged()
            finish()
        }
    }
}
