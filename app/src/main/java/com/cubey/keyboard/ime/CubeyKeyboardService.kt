package com.cubey.keyboard.ime

import android.inputmethodservice.InputMethodService
import android.view.LayoutInflater
import android.view.View
import com.cubey.keyboard.data.PreferencesStore
import com.cubey.keyboard.domain.ThemeManager
import com.cubey.keyboard.domain.FontManager
import com.cubey.keyboard.ime.view.KeyboardView

class CubeyKeyboardService : InputMethodService() {

    private lateinit var keyboardView: KeyboardView
    private lateinit var prefs: PreferencesStore
    private lateinit var themeManager: ThemeManager
    private lateinit var fontManager: FontManager

    override fun onCreate() {
        super.onCreate()
        prefs = PreferencesStore(this)
        themeManager = ThemeManager(prefs)
        fontManager = FontManager()
    }

    override fun onCreateInputView(): View {
        keyboardView = KeyboardView(this, null).apply {
            applyTheme(themeManager.currentTheme())
            applyTypeface(fontManager.loadFont(this@CubeyKeyboardService, prefs.getFont()))
        }
        return keyboardView
    }

    fun reloadAppearance() {
        keyboardView.applyTheme(themeManager.currentTheme())
        keyboardView.applyTypeface(fontManager.loadFont(this, prefs.getFont()))
        keyboardView.invalidate()
    }
}
