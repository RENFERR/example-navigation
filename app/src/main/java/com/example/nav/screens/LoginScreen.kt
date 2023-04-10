package com.example.nav.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nav.destinations.ListScreenDestination
import com.example.nav.destinations.LoginScreenDestination
import com.example.nav.ui.theme.NavigationExampleTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator
) {
    LoginScreen(
        onLogin = {
            navigator.navigate(ListScreenDestination) {
                popUpTo(route = LoginScreenDestination.route) {
                    inclusive = true
                }
            }
        }
    )
}

@Composable
fun LoginScreen(
    onLogin: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(
                    text = "Login screen",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = onLogin) {
                    Text(text = "Authenticate")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen { }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreviewLight() {
    NavigationExampleTheme(darkTheme = false) {
        LoginScreenPreview()
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreviewDark() {
    NavigationExampleTheme(darkTheme = true) {
        LoginScreenPreview()
    }
}