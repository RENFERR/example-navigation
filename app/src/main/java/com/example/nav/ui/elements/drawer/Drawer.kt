package com.example.nav.ui.elements.drawer

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nav.destinations.ListScreenDestination
import com.example.nav.destinations.LoginScreenDestination
import com.example.nav.destinations.UserScreenDestination
import com.example.nav.ui.elements.drawer.DrawerItem.Companion.toRoute
import com.example.nav.ui.theme.NavigationExampleTheme
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Composable
fun Drawer(
    state: DrawerState,
    currentPage: DrawerItem,
    navigator: DestinationsNavigator,
    content: @Composable () -> Unit
) {
    val drawerItems = listOf(
        DrawerItem.List,
        DrawerItem.User,
        DrawerItem.Logout
    )
    Drawer(
        drawerItems = drawerItems,
        state = state,
        content = content,
        currentPage = currentPage,
        onItemClick = { item ->
            when (item) {
                DrawerItem.List -> navigator.list(currentRoute = currentPage.toRoute())
                DrawerItem.Logout -> navigator.logout(currentRoute = currentPage.toRoute())
                DrawerItem.User ->
                    navigator.user(currentRoute = currentPage.toRoute(), argument = "MyUsername")
            }
        }
    )
}

private fun DestinationsNavigator.list(currentRoute: String) {
    navigate(direction = ListScreenDestination) {
        popUpTo(route = currentRoute) {
            inclusive = true
        }
    }
}

private fun DestinationsNavigator.logout(currentRoute: String) {
    navigate(direction = LoginScreenDestination) {
        popUpTo(route = currentRoute) {
            inclusive = true
        }
    }
}

private fun DestinationsNavigator.user(currentRoute: String, argument: String) {
    navigate(direction = UserScreenDestination.invoke(username = argument)) {
        popUpTo(route = currentRoute) {
            inclusive = true
        }
    }
}

@Composable
private fun Drawer(
    state: DrawerState,
    drawerItems: List<DrawerItem>,
    currentPage: DrawerItem,
    onItemClick: (DrawerItem) -> Unit,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
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
                                    if (currentPage != item) {
                                        if (state.isOpen) scope.launch { state.close() }
                                        onItemClick.invoke(item)
                                    }
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
        DrawerItem.List,
        DrawerItem.User,
        DrawerItem.Logout
    )
    Drawer(
        drawerItems = drawerItems,
        state = state,
        content = {},
        onItemClick = {},
        currentPage = DrawerItem.List
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