package mx.ipn.escom.bautistas.mynotes.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.ipn.escom.bautistas.mynotes.ui.main.viewmodels.NoteViewModel
import mx.ipn.escom.bautistas.mynotes.ui.main.views.AddNoteScreen
import mx.ipn.escom.bautistas.mynotes.ui.main.views.HomeScreen
import mx.ipn.escom.bautistas.mynotes.ui.main.views.Routes
import mx.ipn.escom.bautistas.mynotes.ui.theme.MyNotesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNotesTheme {
                val navController = rememberNavController()
                val noteViewModel: NoteViewModel = hiltViewModel()
                NavHost(navController = navController, startDestination = Routes.HomeScreen.route) {
                    composable(Routes.HomeScreen.route) {
                        HomeScreen(noteViewModel) { screen ->
                            noteViewModel.toNewNote()
                            navigate(navController, screen)
                        }
                    }
                    composable(Routes.AddNoteScreen.route) {
                        AddNoteScreen(noteViewModel) {
                            navController.popBackStack()
                        }
                    }
                    composable(Routes.UpdateNoteScreen.route){
                        AddNoteScreen(noteViewModel) {
                            navController.popBackStack()
                        }
                    }

                }

            }
        }
    }

    private fun navigate(navController: NavController, route: Routes) {
        navController.navigate(route.route) {
//            Save screen's state
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
//            It makes only instance of that screen
            launchSingleTop = true
        }
    }
}
