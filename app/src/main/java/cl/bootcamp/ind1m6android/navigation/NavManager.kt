package cl.bootcamp.ind1m6android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

        composable(Screen.AddContact.route) {
            CreateContactScreen(navController, contactViewModel)
        }
    }
}