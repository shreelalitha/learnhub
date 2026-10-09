package com.learnhub.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learnhub.app.features.courseDashboard.screens.CourseListScreen
import com.learnhub.app.features.courseDetail.screens.CourseDetailScreen
import com.learnhub.app.features.login.screens.LoginScreen
import com.learnhub.app.ui.theme.LearnHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearnHubTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "login") {

                    composable("login") {
                        LoginScreen(
                            onLoginSuccess = {
                                navController.navigate("courses"){
                                    popUpTo("login") {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }

                    composable("courses") {
                        CourseListScreen(onCourseClick = { courseId ->
                                navController.navigate("courseDetail/$courseId")
                        })
                    }

                    composable("courseDetail/{courseId}") { backStackEntry ->
                        val courseId = backStackEntry.arguments
                            ?.getString("courseId")

                        if (courseId != null) {
                            CourseDetailScreen(
                                courseId = courseId,
                                onBackClick = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
