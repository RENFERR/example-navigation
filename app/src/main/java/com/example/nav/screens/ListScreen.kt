package com.example.nav.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nav.bars.MenuTopBar
import com.example.nav.drawer.Drawer
import com.example.nav.screens.destinations.ListItemScreenDestination
import com.example.nav.ui.theme.NavigationExampleTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import java.util.UUID

@Destination
@Composable
fun ListScreen(
    navigator: DestinationsNavigator
) {
    val exampleItems = rememberSaveable {
        mutableListOf<String>().apply {
            repeat(40) {
                add(element = UUID.randomUUID().toString())
            }
        }
    }
    val state = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    Drawer(
        state = state,
        navigator = navigator,
        content = {
            ListScreen(
                items = exampleItems,
                onItemClick = { _, item ->
                    navigator.navigate(ListItemScreenDestination.invoke(item = item))
                },
                onOpenDrawerClick = {
                    if (!state.isOpen) scope.launch { state.open() }
                }
            )
        }
    )
}

@Composable
private fun ListScreen(
    items: List<String>,
    onOpenDrawerClick: () -> Unit,
    onItemClick: (index: Int, item: String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            MenuTopBar(
                title = "Items",
                onMenuPressed = onOpenDrawerClick
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(
                items = items,
                key = { index, _ ->
                    index
                }
            ) { index, item ->
                val click = remember { { onItemClick.invoke(index, item) } }
                Text(
                    modifier = Modifier
                        .clip(shape = MaterialTheme.shapes.small)
                        .clickable(onClick = click),
                    text = "Item: $item",
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListScreenPreview() {
    val context = LocalContext.current
    ListScreen(
        items = listOf(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString()
        ),
        onItemClick = { index, item ->
            Toast.makeText(
                context,
                "You click at ${index + 1} item. Name: $item",
                Toast.LENGTH_SHORT
            ).show()
        },
        onOpenDrawerClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun ListScreenPreviewLight() {
    NavigationExampleTheme(darkTheme = false) {
        ListScreenPreview()
    }
}

@Preview(showBackground = true)
@Composable
private fun ListScreenPreviewDark() {
    NavigationExampleTheme(darkTheme = true) {
        ListScreenPreview()
    }
}