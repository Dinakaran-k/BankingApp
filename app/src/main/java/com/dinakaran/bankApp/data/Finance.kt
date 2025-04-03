package com.dinakaran.bankApp.data

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Finance(
    val icon : ImageVector,
    val name : String,
    val background : Color
)


