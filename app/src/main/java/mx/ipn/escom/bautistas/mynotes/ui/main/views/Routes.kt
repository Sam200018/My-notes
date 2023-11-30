package mx.ipn.escom.bautistas.mynotes.ui.main.views

sealed class Routes(
    val route: String
) {
    data object SignInScreen : Routes(route = "Sign_in")
    data object HomeScreen : Routes(route = "home")
    data object AddNoteScreen : Routes(route = "add_note")
    data object UpdateNoteScreen : Routes(route = "update_note")
}
