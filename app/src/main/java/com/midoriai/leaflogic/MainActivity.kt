package com.midoriai.leaflogic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.midoriai.leaflogic.ui.theme.LeafLogicTheme
import com.midoriai.leaflogic.ui.navigation.LeafLogicNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LeafLogicTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LeafLogicNavigation(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}