package com.example.nav.ui.elements.drawer

import android.os.Parcelable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nav.destinations.ListScreenDestination
import com.example.nav.destinations.LoginScreenDestination
import com.example.nav.destinations.UserScreenDestination
import com.example.nav.ui.theme.NavigationExampleTheme
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
sealed class DrawerItem(val title: String, val icon: @RawValue ImageVector) : Parcelable {
    object List : DrawerItem(title = "Items", icon = Icons.Rounded.List)
    object User : DrawerItem(title = "User", icon = Icons.Rounded.Person)
    object Logout : DrawerItem(title = "Logout", icon = Icons.Rounded.Logout)

    companion object {
        fun DrawerItem.toRoute(): String = when (this) {
            List -> ListScreenDestination.route
            Logout -> LoginScreenDestination.route
            User -> UserScreenDestination.route
        }
    }
}

@Composable
fun DrawerItem(
    item: DrawerItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    NavigationDrawerItem(
        icon = {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = item.icon,
                contentDescription = item.title
            )
        },
        label = {
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        selected = selected,
        onClick = onClick,
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = Color.Transparent,
            unselectedContainerColor = Color.Transparent,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedTextColor = MaterialTheme.colorScheme.onBackground,
            selectedIconColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.onBackground
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun DrawerItemPreview() {
    NavigationExampleTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DrawerItem(
                item = DrawerItem.List,
                selected = false,
                onClick = {}
            )
            DrawerItem(
                item = DrawerItem.List,
                selected = true,
                onClick = {}
            )
            DrawerItem(
                item = DrawerItem.Logout,
                selected = false,
                onClick = {}
            )
            DrawerItem(
                item = DrawerItem.Logout,
                selected = true,
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DrawerItemPreviewLight() {
    NavigationExampleTheme(darkTheme = false) {
        DrawerItemPreview()
    }
}

@Preview(showBackground = true)
@Composable
private fun DrawerItemPreviewDark() {
    NavigationExampleTheme(darkTheme = true) {
        DrawerItemPreview()
    }
}