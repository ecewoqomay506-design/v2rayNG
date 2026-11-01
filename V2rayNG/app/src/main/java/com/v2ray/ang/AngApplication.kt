package com.v2ray.ang

import android.app.Application
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.work.Configuration
import androidx.work.WorkManager
import com.v2ray.ang.AppConfig.ANG_PACKAGE
import com.v2ray.ang.handler.AppLocaleManager
import com.v2ray.ang.handler.MmkvManager
import com.v2ray.ang.handler.SettingsManager
import com.v2ray.ang.ui.compose.ThemeManager

class AngApplication : Application() {
    companion object {
        lateinit var application: AngApplication
    }

    /**
     * Attaches the base context to the application.
     * @param base The base context.
     */
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base?.let(ContextCompat::getContextForLanguage))
        application = this
    }

    private val workManagerConfiguration: Configuration = Configuration.Builder()
        .setDefaultProcessName("${ANG_PACKAGE}:bg")
        .build()

    /**
     * Initializes the application.
     */
    override fun onCreate() {
        super.onCreate()

        MmkvManager.initialize(this)

        AppLocaleManager.initialize(this)

        // Initialize WorkManager with the custom configuration
        WorkManager.initialize(this, workManagerConfiguration)

        // Ensure critical preference defaults are present in MMKV early
        SettingsManager.initApp(this)


        // AryanVPN: seed default servers on first launch only
        try {
            val prefs = getSharedPreferences("aryan_seed", MODE_PRIVATE)
            if (!prefs.getBoolean("defaults_seeded", false)) {
                val defaults = """
vless://b1b755a0-d4a2-4285-9553-7aa9645f221d@15.197.166.3:443?encryption=none&host=Msg-Queue-FFF737.JACKiDK995.wORkErS.DeV&type=ws&security=tls&path=%2Fvl%2FGlC1ZbxUhFD5innk8B%3Fed%3D2560&sni=MSg-QueUE-fff737.jackiDk995.wORKERs.DeV&fp=chrome&alpn=http%2F1.1#Aryan Server 1
vless://b1b755a0-d4a2-4285-9553-7aa9645f221d@15.197.166.3:443?encryption=none&host=Msg-Queue-FFF737.JACKiDK995.wORkErS.DeV&type=ws&security=tls&path=%2Fvl%2FGlC1ZbxUhFD5innk8B%3Fed%3D2560&sni=MSg-QueUE-fff737.jackiDk995.wORKERs.DeV&fp=chrome&alpn=http%2F1.1#Aryan Server 2
vless://b1b755a0-d4a2-4285-9553-7aa9645f221d@15.197.166.3:443?encryption=none&host=Msg-Queue-FFF737.JACKiDK995.wORkErS.DeV&type=ws&security=tls&path=%2Fvl%2FGlC1ZbxUhFD5innk8B%3Fed%3D2560&sni=MSg-QueUE-fff737.jackiDk995.wORKERs.DeV&fp=chrome&alpn=http%2F1.1#Aryan Server 3
vless://b1b755a0-d4a2-4285-9553-7aa9645f221d@15.197.166.3:443?encryption=none&host=Msg-Queue-FFF737.JACKiDK995.wORkErS.DeV&type=ws&security=tls&path=%2Fvl%2FGlC1ZbxUhFD5innk8B%3Fed%3D2560&sni=MSg-QueUE-fff737.jackiDk995.wORKERs.DeV&fp=chrome&alpn=http%2F1.1#Aryan Server 4
vless://b1b755a0-d4a2-4285-9553-7aa9645f221d@15.197.166.3:443?encryption=none&host=Msg-Queue-FFF737.JACKiDK995.wORkErS.DeV&type=ws&security=tls&path=%2Fvl%2FGlC1ZbxUhFD5innk8B%3Fed%3D2560&sni=MSg-QueUE-fff737.jackiDk995.wORKERs.DeV&fp=chrome&alpn=http%2F1.1#Aryan Server 5
vless://b1b755a0-d4a2-4285-9553-7aa9645f221d@15.197.166.3:443?encryption=none&host=Msg-Queue-FFF737.JACKiDK995.wORkErS.DeV&type=ws&security=tls&path=%2Fvl%2FGlC1ZbxUhFD5innk8B%3Fed%3D2560&sni=MSg-QueUE-fff737.jackiDk995.wORKERs.DeV&fp=chrome&alpn=http%2F1.1#Aryan Server 6
vless://b1b755a0-d4a2-4285-9553-7aa9645f221d@15.197.166.3:443?encryption=none&host=Msg-Queue-FFF737.JACKiDK995.wORkErS.DeV&type=ws&security=tls&path=%2Fvl%2FGlC1ZbxUhFD5innk8B%3Fed%3D2560&sni=MSg-QueUE-fff737.jackiDk995.wORKERs.DeV&fp=chrome&alpn=http%2F1.1#Aryan Server 7
vless://b1b755a0-d4a2-4285-9553-7aa9645f221d@15.197.166.3:443?encryption=none&host=Msg-Queue-FFF737.JACKiDK995.wORkErS.DeV&type=ws&security=tls&path=%2Fvl%2FGlC1ZbxUhFD5innk8B%3Fed%3D2560&sni=MSg-QueUE-fff737.jackiDk995.wORKERs.DeV&fp=chrome&alpn=http%2F1.1#Aryan Server 8
vless://b1b755a0-d4a2-4285-9553-7aa9645f221d@15.197.166.3:443?encryption=none&host=Msg-Queue-FFF737.JACKiDK995.wORkErS.DeV&type=ws&security=tls&path=%2Fvl%2FGlC1ZbxUhFD5innk8B%3Fed%3D2560&sni=MSg-QueUE-fff737.jackiDk995.wORKERs.DeV&fp=chrome&alpn=http%2F1.1#Aryan Server 9
vless://b1b755a0-d4a2-4285-9553-7aa9645f221d@15.197.166.3:443?encryption=none&host=Msg-Queue-FFF737.JACKiDK995.wORkErS.DeV&type=ws&security=tls&path=%2Fvl%2FGlC1ZbxUhFD5innk8B%3Fed%3D2560&sni=MSg-QueUE-fff737.jackiDk995.wORKERs.DeV&fp=chrome&alpn=http%2F1.1#Aryan Server 10
                """.trimIndent()
                val (count, _) = com.v2ray.ang.handler.AngConfigManager.importBatchConfig(defaults, "", false)
                if (count > 0) {
                    prefs.edit().putBoolean("defaults_seeded", true).apply()
                }
            }
        } catch (_: Exception) {
            // ignore seed failures so app still starts
        }

        // Initialize theme state from MMKV
        ThemeManager.refresh()
    }
}
