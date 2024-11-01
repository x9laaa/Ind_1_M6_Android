package cl.bootcamp.ind1m6android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.bootcamp.ind1m6android.view.ContactListScreen
import cl.bootcamp.ind1m6android.view.CreateContactScreen
import cl.bootcamp.ind1m6android.viewModel.ContactViewModel

@Composable
fun NavController(contactViewModel: ContactViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.HomeListContacts.route) {
        composable(Screen.HomeListContacts.route) {
            ContactListScreen(navController, contactViewModel)
        }

        composable(Screen.AddContact.route + "/{id}", arguments = listOf(
            navArgument("id") {
                type = NavType.LongType
                defaultValue = 0L
                nullable = false
            }
        )) { entry ->
            val id = if (entry.arguments != null) entry.arguments!!.getLong("id") else 0L
            CreateContactScreen(id, navController, contactViewModel)
        }
    }
}