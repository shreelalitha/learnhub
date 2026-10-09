package com.learnhub.app.features.courseDashboard.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.learnhub.app.data.room.CourseEntity
import com.learnhub.app.features.courseDashboard.viewModel.CourseUiState
import com.learnhub.app.features.courseDashboard.viewModel.CoursesViewModel
import com.learnhub.app.utils.filterCompletedCourses
import com.learnhub.app.utils.filterContinueCourses
import com.learnhub.app.utils.filterExploreCourses

@Composable
fun CourseListScreen(
    viewModel: CoursesViewModel = hiltViewModel(),
    onCourseClick: (String) -> Unit
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = { HomeTopBar() },
        bottomBar = {
            HomeBottomBar(
                selectedItem = "courses",
                onHomeClick = {}
            )
        }
    ) { paddingValues ->

        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                placeholder = {
                    Text(
                        "Search Courses",
                        style = TextStyle(color = Color.LightGray)
                    )
                },
                singleLine = true,
                trailingIcon = {},
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {}
                ),
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color.LightGray,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                when (val currentState = state) {
                    CourseUiState.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is CourseUiState.Success -> {

                        val continueCourses = filterContinueCourses(currentState.courses)
                        val exploreCourses = filterExploreCourses(currentState.courses)
                        val completedCourses = filterCompletedCourses(currentState.courses)

                        if (currentState.courses.isEmpty()) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No Courses available"
                                )
                            }
                        } else {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(
                                    start = 16.dp,
                                    end = 16.dp,
                                    top = 8.dp,
                                    bottom = 16.dp
                                ),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                if (continueCourses.isNotEmpty()) {
                                    item {
                                        Text(
                                            text = "Continue Learning",
                                            style = MaterialTheme.typography.titleLarge,
                                            color = MaterialTheme.colorScheme.onBackground,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }

                                    items(continueCourses) { course ->
                                        CourseCard(
                                            course = course,
                                            onContinueClick = {
                                                onCourseClick(course.courseId)
                                            }
                                        )
                                    }

                                    item {
                                        HorizontalDivider(
                                            modifier = Modifier.fillMaxWidth()
                                                .padding(top = 8.dp),
                                            color = MaterialTheme.colorScheme.outlineVariant
                                        )
                                    }
                                }

                                if (completedCourses.isNotEmpty()) {
                                    item {
                                        Text(
                                            text = "Completed Courses",
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(bottom = 8.dp)
                                        )

                                        LazyRow(
                                            modifier = Modifier.padding(bottom = 8.dp),
                                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                                            contentPadding = PaddingValues(horizontal = 4.dp)
                                        ) {
                                            items(
                                                completedCourses,
                                                key = { it.courseId }) { course ->
                                                CourseCard(
                                                    course = course,
                                                    onContinueClick = {
                                                        onCourseClick(course.courseId)
                                                    }
                                                )
                                            }
                                        }

                                        HorizontalDivider(
                                            modifier = Modifier.fillMaxWidth()
                                                .padding(vertical = 8.dp),
                                            color = MaterialTheme.colorScheme.outlineVariant
                                        )
                                    }
                                }

                                if (exploreCourses.isNotEmpty()) {
                                    item {
                                        Text(
                                            text = "Explore Courses",
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }

                                    items(exploreCourses) { course ->
                                        CourseCard(
                                            course = course,
                                            onContinueClick = {
                                                onCourseClick(course.courseId)
                                            }
                                        )
                                    }
                                }

                                if (continueCourses.isEmpty() && exploreCourses.isEmpty()) {
                                    item {
                                        Text(
                                            text = "You've completed all the courses!",
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                    }
                                }
                            }
                        }
                    }

                    is CourseUiState.Error -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    currentState.message,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}