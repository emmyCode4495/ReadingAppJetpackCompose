package com.example.readingappjet

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.readingappjet.navigation.ReaderNavigation
import com.example.readingappjet.ui.theme.ReadingAppJetTheme
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReadingAppJetTheme {
                ReaderApp()
            }
        }
    }
}

@Composable
fun ReaderApp() {
    // A surface container using the 'background' color from the theme
    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background) {
        Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            ReaderNavigation()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReadingAppJetTheme {
    }
}