package com.newOs.quotivate.quotes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.newOs.quotivate.quotes.presentation.favoritesList.FavoritesScreen
import com.newOs.quotivate.quotes.presentation.favoritesList.FavoritesViewModel
import com.newOs.quotivate.quotes.presentation.quoteOfDay.MainScreen
import com.newOs.quotivate.quotes.presentation.quoteOfDay.MainViewModel
import com.newOs.quotivate.quotes.presentation.quotesList.QuotesScreen
import com.newOs.quotivate.quotes.presentation.quotesList.QuotesViewModel
import com.newOs.quotivate.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotivateAppTheme {
                Navigation()
            }
        }
    }
}


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){

        composable(route = Screen.MainScreen.route){
            val vm: MainViewModel = hiltViewModel()
            MainScreen(
                navController = navController,
                state = vm.state.value,
                onFavoriteIconClick = { id -> vm.toggleFavoriteState(id) },
                onRefresh = { vm.getRandomQuote() }
            )
        }

        composable(route = Screen.QuotesScreen.route){
            val vm : QuotesViewModel = hiltViewModel()
            QuotesScreen(
                quotes = vm.state.flow,
                onFavoriteIconClick = {id, oldValue -> vm.toggleFavoriteState(id, oldValue)}
            )
        }

        composable(route = Screen.FavoritesScreen.route){
            val vm: FavoritesViewModel = hiltViewModel()
            FavoritesScreen(
                state = vm.state.value,
                onFavoriteIconClick = { id,oldValue -> vm.toggleFavoriteState(id,oldValue) }
            )
        }

    }
}


sealed class Screen(val route: String){
    object MainScreen : Screen("main_screen")
    object QuotesScreen : Screen("quotes_screen")
    object FavoritesScreen : Screen("favorites_screen")
}
