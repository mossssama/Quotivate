package com.newOs.quotivate.navigation

sealed class Screen(val route: String){
    object MainScreen : Screen("main_screen")
    object QuotesScreen : Screen("quotes_screen")
    object FavoritesScreen : Screen("favorites_screen")

    fun withArgs(vararg args: String):String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
