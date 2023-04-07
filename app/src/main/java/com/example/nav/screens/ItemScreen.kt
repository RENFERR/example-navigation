package com.example.nav.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nav.ui.elements.bars.ItemTopBar
import com.example.nav.ui.theme.NavigationExampleTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ItemScreen(
    item: String,
    navigator: DestinationsNavigator
) {
    ItemScreen(
        item = item,
        onBackPressed = { navigator.popBackStack() }
    )
}

@Composable
private fun ItemScreen(
    item: String,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            ItemTopBar(
                title = "Item Info",
                onBackPressed = onBackPressed
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Item",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = item,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListItemScreenPreview() {
    ItemScreen(
        item = "Some Item",
        onBackPressed = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ListItemPreviewLight() {
    NavigationExampleTheme(darkTheme = false) {
        ListItemScreenPreview()
    }
}

@Preview(showBackground = true)
@Composable
private fun ListItemPreviewDark() {
    NavigationExampleTheme(darkTheme = true) {
        ListItemScreenPreview()
    }
}