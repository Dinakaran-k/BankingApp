package com.dinakaran.bankApp

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.dinakaran.bankApp.screens.FingerprintLoginScreen
import com.dinakaran.bankApp.screens.HomeScreen
import com.dinakaran.bankApp.screens.SetBarColor
import com.dinakaran.bankApp.ui.theme.BankingAppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //biometric
        val biometricManager = BiometricManager.from(this)
        val canAuthenticate = biometricManager.canAuthenticate(
            BiometricManager.Authenticators.BIOMETRIC_STRONG
        ) == BiometricManager.BIOMETRIC_SUCCESS
        enableEdgeToEdge()
        setContent {
            BankingAppTheme {

                SetBarColor(color = MaterialTheme.colorScheme.background)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FingerprintLoginScreen(
                        canAuthenticate = canAuthenticate,
                        onAuthSuccess = {
                            startActivity(Intent(this, HomeScreen::class.java))
                            finish()
                        }
                    )
                }
            }
        }
    }
}