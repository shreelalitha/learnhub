package com.learnhub.app.features.courseDashboard.screens

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.learnhub.app.R

@Composable
fun HomeBottomBar(selectedItem: String,
                  onHomeClick: () -> Unit) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
        NavigationBarItem(
            selected = selectedItem == "courses",

            onClick = onHomeClick,
            icon = {
                Icon(
                    painter = painterResource(R.drawable.dashboard),
                    contentDescription = "Courses"
                )
            },
            label = { Text("Dashboard") }
        )

        NavigationBarItem(
            selected = selectedItem == "profile",
            onClick = {},
            icon = {
                Icon(
                    painter = painterResource(R.drawable.user),
                    contentDescription = "Profile"
                )
            },
            label = { Text("Profile") }
        )
    }
}