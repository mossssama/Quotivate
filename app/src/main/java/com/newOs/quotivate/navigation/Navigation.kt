package com.newOs.quotivate

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.newOs.quotivate.screens.FavoritesScreen
import com.newOs.quotivate.screens.MainScreen
import com.newOs.quotivate.screens.QuotesScreen
import com.newOs.quotivate.navigation.Screen


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(
            route = Screen.MainScreen.route
        ){
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.QuotesScreen.route+"?quote={quote}",
            arguments = listOf(
                navArgument("quote"){
                    type = NavType.StringType
                    defaultValue = "When I become expensive no one wanted me"
                    nullable = true
                }
            )
        ){ entry ->
            QuotesScreen(name=entry.arguments?.getString("name"))
        }
        composable(
            route = Screen.FavoritesScreen.route
        ){
            FavoritesScreen()
        }

    }
}


