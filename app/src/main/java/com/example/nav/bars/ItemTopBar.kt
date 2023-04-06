package com.example.nav.bars

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.nav.ui.theme.NavigationExampleTheme
import com.example.nav.ui.theme.themedTopAppBarColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemTopBar(
    title: String,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = "Go back"
                )
            }
        },
        colors = themedTopAppBarColors()
    )
}

@Preview(showBackground = true)
@Composable
private fun ItemTopBarPreview() {
    val context = LocalContext.current
    ItemTopBar(
        title = "List Item",
        onBackPressed = {
            Toast.makeText(
                context,
                "On back pressed",
                Toast.LENGTH_SHORT
            ).show()
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ItemTopBarPreviewLight() {
    NavigationExampleTheme(darkTheme = false) {
        ItemTopBarPreview()
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemTopBarPreviewDark() {
    NavigationExampleTheme(darkTheme = true) {
        ItemTopBarPreview()
    }
}