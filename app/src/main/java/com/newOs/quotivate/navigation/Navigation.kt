package com.newOs.quotivate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.newOs.quotivate.screens.FavoritesScreen
import com.newOs.quotivate.screens.MainScreen
import com.newOs.quotivate.screens.QuotesScreen


@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            MainScreen(navController = navController)
        }
        composable(route = Screen.QuotesScreen.route){
            QuotesScreen()
        }
        composable(route = Screen.FavoritesScreen.route){
            FavoritesScreen()
        }

    }
}


