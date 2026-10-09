package com.learnhub.app.features.courseDashboard.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.learnhub.app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(showBackButton: Boolean = false, onBackClick: () -> Unit = {}) {
    TopAppBar(
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(R.drawable.back),
                        contentDescription = "Back"
                    )
                }
            } else {
                IconButton(
                    onClick = {
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.user),
                        contentDescription = "User"
                    )
                }
            }
        },
        title = {
            Column(modifier = Modifier
                .fillMaxWidth()) {
                Text(
                    text = if (!showBackButton) "Hello there.." else "Course Details",
                    style = MaterialTheme.typography.titleMedium
                )

                if (!showBackButton) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Lets start learning!",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    )
}