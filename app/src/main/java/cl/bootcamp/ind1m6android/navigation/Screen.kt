package cl.bootcamp.ind1m6android.navigation

sealed class Screen(val route: String) {
    object HomeListContacts: Screen("home_list_Contacts")
    object AddContact: Screen("Create_Contact")
}