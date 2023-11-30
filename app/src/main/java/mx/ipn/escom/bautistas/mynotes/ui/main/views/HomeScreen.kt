package mx.ipn.escom.bautistas.mynotes.ui.main.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.ipn.escom.bautistas.mynotes.ui.components.NotesList
import mx.ipn.escom.bautistas.mynotes.ui.main.viewmodels.NoteViewModel

@Composable
fun HomeScreen(
    noteViewModel: NoteViewModel,
    onNavigate: (Routes) -> Unit,
    onLogout: () -> Unit
) {

    val state by noteViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(), backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(title = {
                Text(text = "Notas")
            }, actions = {
                IconButton(onClick = onLogout) {
                    Icon(Icons.Filled.Logout, contentDescription = "Logout")
                }
            })
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Agregar nota") },
                onClick = { onNavigate(Routes.AddNoteScreen) },
                icon = {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                },
            )
        },
    ) {

        NotesList(modifier = Modifier.padding(it), notes = state.notes) { note ->
            onNavigate(Routes.UpdateNoteScreen)
            noteViewModel.onSelectedNote(note)
        }

    }
}

//@Preview
//@Composable
//fun HomePreview() {
//    HomeScreen(hiltViewModel()) {}
//}