package com.dinakaran.bankApp.screens

import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executor

@Composable
fun FingerprintLoginScreen(
    canAuthenticate : Boolean,
    onAuthSuccess : () -> Unit
){
    val context = LocalContext.current
    val activity = context as FragmentActivity
    val executor : Executor = ContextCompat.getMainExecutor(context)

    val biometricPrompt = remember { BiometricPrompt(activity,executor,
        object : BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
                onAuthSuccess()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show()

            }
        })
    }

    val promptInfo = remember {
        BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Authenticate using your fingerprint")
            .setNegativeButtonText("Cancel")
            .build()
    }

    LaunchedEffect(Unit) {
        if (canAuthenticate) {
            biometricPrompt.authenticate(promptInfo)
        } else {
            Toast.makeText(context, "Biometric not available", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Default.Fingerprint,
            contentDescription = "Fingerprint Icon",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if(canAuthenticate){
                    biometricPrompt.authenticate(promptInfo)
                } else {
                    Toast.makeText(context,"Biometric not available",Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text(text = "Login with Fingerprint")
        }
    }
}