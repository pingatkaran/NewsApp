package com.app.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.feature_home.home.DetailsScreen
import com.app.feature_home.home.HomeScreen
import com.app.domain.model.Article

object Routes {
    const val HOME = "home"
    const val DETAILS = "details"
    const val ARTICLE_ARG = "article"
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(onArticleClick = { article ->
                navController.currentBackStackEntry?.savedStateHandle?.set(Routes.ARTICLE_ARG, article)
                navController.navigate(Routes.DETAILS)
            })
        }
        composable(route = Routes.DETAILS) {
            navController.previousBackStackEntry?.savedStateHandle?.get<Article>(Routes.ARTICLE_ARG)?.let {
                DetailsScreen(article = it) {
                    navController.popBackStack()
                }
            }
        }
    }
}