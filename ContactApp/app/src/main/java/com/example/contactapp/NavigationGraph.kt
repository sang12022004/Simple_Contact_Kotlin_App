package com.example.contactapp

import SearchContactScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel



sealed class NavRoute(val route: String) {
    object LIST_SCREEN : NavRoute("contactlist")
    object DETAIL_SCREEN : NavRoute("contactdetail")
    object SEARCH_SCREEN : NavRoute("searchcontact")
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.LIST_SCREEN.route
    ) {
        composable(NavRoute.LIST_SCREEN.route) {
            ContactLisScreen(navController)
        }
        composable(
            NavRoute.DETAIL_SCREEN.route + "?id={id}",
            arguments = listOf(navArgument("id") { nullable = true })
        ) {
            val id = it.arguments?.getString("id")
            if (id != null) {
                ContactDetailScreen(navController = navController, id.toInt())
            } else {
                ContactDetailScreen(navController = navController)
            }
        }
        composable(NavRoute.SEARCH_SCREEN.route) {
            val viewModel: ContactListViewModel = viewModel()
            SearchContactScreen(navController = navController, viewModel = viewModel)
        }
    }
}
