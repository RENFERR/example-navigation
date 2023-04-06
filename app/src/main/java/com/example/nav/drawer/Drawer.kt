package com.example.nav.drawer

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nav.screens.destinations.ListScreenDestination
import com.example.nav.screens.destinations.LoginScreenDestination
import com.example.nav.screens.destinations.UserScreenDestination
import com.example.nav.ui.theme.NavigationExampleTheme
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun Drawer(
    state: DrawerState,
    navigator: DestinationsNavigator,
    content: @Composable () -> Unit
) {
    val drawerItems = listOf(
        DrawerItem.Items,
        DrawerItem.User,
        DrawerItem.Logout
    )
    var currentPage: DrawerItem by rememberSaveable {
        mutableStateOf(DrawerItem.Items)
    }
    Drawer(
        drawerItems = drawerItems,
        state = state,
        content = content,
        currentPage = currentPage,
        onItemClick = { item ->
            when (item) {
                DrawerItem.Items -> navigator.navigate(ListScreenDestination)
                DrawerItem.Logout -> navigator.navigate(LoginScreenDestination)
                DrawerItem.User -> navigator.navigate(UserScreenDestination.invoke("My username"))
            }
            currentPage = item
        }
    )
}

@Composable
private fun Drawer(
    state: DrawerState,
    drawerItems: List<DrawerItem>,
    currentPage: DrawerItem,
    onItemClick: (DrawerItem) -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = state,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RectangleShape,
                drawerContainerColor = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(end = 72.dp),
                content = {
                    LazyColumn {
                        items(
                            items = drawerItems,
                            key = { it.title }
                        ) { item ->
                            DrawerItem(
                                item = item,
                                selected = currentPage == item,
                                onClick = {
                                    if (currentPage != item) onItemClick.invoke(item)
                                }
                            )
                        }
                    }
                }
            )
        },
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun DrawerPreview() {
    val state = rememberDrawerState(initialValue = DrawerValue.Open)
    val drawerItems = listOf(
        DrawerItem.Items,
        DrawerItem.User,
        DrawerItem.Logout
    )
    Drawer(
        drawerItems = drawerItems,
        state = state,
        content = {},
        onItemClick = {},
        currentPage = DrawerItem.Items
    )
}

@Preview(showBackground = true)
@Composable
private fun DrawerPreviewLight() {
    NavigationExampleTheme(darkTheme = false) {
        DrawerPreview()
    }
}

@Preview(showBackground = true)
@Composable
private fun DrawerPreviewDark() {
    NavigationExampleTheme(darkTheme = true) {
        DrawerPreview()
    }
}