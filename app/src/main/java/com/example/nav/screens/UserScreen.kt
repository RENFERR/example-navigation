package com.example.nav.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nav.bars.MenuTopBar
import com.example.nav.drawer.Drawer
import com.example.nav.ui.theme.NavigationExampleTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun UserScreen(
    username: String,
    navigator: DestinationsNavigator
) {
    val state = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    Drawer(
        state = state,
        navigator = navigator,
        content = {
            UserScreen(
                username = username,
                onOpenDrawerClick = {
                    if (!state.isOpen) scope.launch { state.open() }
                }
            )
        }
    )
}

@Composable
private fun UserScreen(
    username: String,
    onOpenDrawerClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            MenuTopBar(
                title = "User",
                onMenuPressed = onOpenDrawerClick
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Username",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = username,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserScreenPreview() {
    UserScreen(
        "Some username",
        onOpenDrawerClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun UserScreenPreviewLight() {
    NavigationExampleTheme(darkTheme = false) {
        UserScreenPreview()
    }
}

@Preview(showBackground = true)
@Composable
private fun UserScreenPreviewDark() {
    NavigationExampleTheme(darkTheme = true) {
        UserScreenPreview()
    }
}