package com.newOs.quotivate.quotes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.newOs.quotivate.quotes.presentation.favorites.FavoritesScreen
import com.newOs.quotivate.quotes.presentation.favorites.FavoritesViewModel
import com.newOs.quotivate.quotes.presentation.main.MainScreen
import com.newOs.quotivate.quotes.presentation.main.MainViewModel
import com.newOs.quotivate.quotes.presentation.quotes.QuotesScreen
import com.newOs.quotivate.quotes.presentation.quotes.QuotesViewModel
import com.newOs.quotivate.composeUi.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
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
                onRefresh = { vm.getRandomQuote() },
                onShareIconClick = { quote,context ->  vm.shareQuote(quote = quote, context = context)},
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
