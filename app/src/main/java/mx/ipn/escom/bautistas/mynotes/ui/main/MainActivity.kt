package mx.ipn.escom.bautistas.mynotes.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mx.ipn.escom.bautistas.mynotes.ui.main.viewmodels.NoteViewModel
import mx.ipn.escom.bautistas.mynotes.ui.main.viewmodels.SignInViewModel
import mx.ipn.escom.bautistas.mynotes.ui.main.views.AddNoteScreen
import mx.ipn.escom.bautistas.mynotes.data.signin.GoogleAuthUiClient
import mx.ipn.escom.bautistas.mynotes.ui.main.views.HomeScreen
import mx.ipn.escom.bautistas.mynotes.ui.main.views.Routes
import mx.ipn.escom.bautistas.mynotes.ui.main.views.SignInScreen
import mx.ipn.escom.bautistas.mynotes.ui.theme.MyNotesTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var  googleAuthUiClient: GoogleAuthUiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNotesTheme {
                val navController = rememberNavController()
                val noteViewModel: NoteViewModel = hiltViewModel()
                NavHost(
                    navController = navController,
                    startDestination = Routes.SignInScreen.route
                ) {
                    composable(Routes.SignInScreen.route) {
                        val signInViewModel: SignInViewModel = hiltViewModel()
                        val state by signInViewModel.signInState.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = Unit) {
                            Log.d("getSignedInUser",googleAuthUiClient.getSignedInUser().toString() )
                            if (googleAuthUiClient.getSignedInUser() != null) {
                                navController.navigate(Routes.HomeScreen.route)
                            }
                        }

                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = { result ->
                                if (result.resultCode == RESULT_OK) {
                                    lifecycleScope.launch {
                                        val signInResult =
                                            googleAuthUiClient.googleSignInWithIntent(
                                                intent = result.data ?: return@launch
                                            )
                                        signInViewModel.onSignInResult(signInResult)
                                    }
                                }

                            }
                        )

                        LaunchedEffect(key1 = state.isSignInSuccessful) {
                            if (state.isSignInSuccessful) {
                                Toast.makeText(
                                    applicationContext,
                                    "Sign in successful",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(Routes.HomeScreen.route)
                                signInViewModel.resetState()
                            }
                        }

                        SignInScreen(
                            state = state,
                            onSignInClick = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthUiClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            }
                        )


                    }


                    composable(Routes.HomeScreen.route) {
                        HomeScreen(
                            noteViewModel,
                            onNavigate = { screen ->
                                noteViewModel.toNewNote()
                                navigate(navController, screen)
                            },
                            onLogout = {
                                Log.d("Signout", "Here")
                                lifecycleScope.launch {
                                    googleAuthUiClient.signOut()
                                    Toast.makeText(applicationContext, "Log out", Toast.LENGTH_LONG)
                                        .show()
                                    navController.popBackStack()
                                }
                            }
                        )
                    }
                    composable(Routes.AddNoteScreen.route) {
                        AddNoteScreen(noteViewModel) {
                            navController.popBackStack()
                        }
                    }
                    composable(Routes.UpdateNoteScreen.route) {
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
