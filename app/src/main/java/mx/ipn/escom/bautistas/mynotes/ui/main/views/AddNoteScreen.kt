package mx.ipn.escom.bautistas.mynotes.ui.main.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.ipn.escom.bautistas.mynotes.ui.main.viewmodels.NoteViewModel
import mx.ipn.escom.bautistas.mynotes.ui.theme.MyNotesTheme

@Composable
fun AddNoteScreen(noteViewModel: NoteViewModel, backHome: () -> Unit) {

    val state by noteViewModel.state.collectAsStateWithLifecycle()
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = noteViewModel.title,
                label = { Text(text = "title") },
                onValueChange = { noteViewModel.onTitleChanged(it) })
            Spacer(modifier = Modifier.height(15.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = noteViewModel.body,
                label = { Text(text = "body") },
                onValueChange = { noteViewModel.onBodyChanged(it) })
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

                if (state.note.id == 0) {
                    Button(onClick = {
                        noteViewModel.insertNote {
                            backHome()
                        }
                    }) {
                        Icon(
                            Icons.Filled.Done,
                            contentDescription = "done",
                            Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                        Text(text = "Save!")
                    }
                } else {
                    Button(onClick = {
                        noteViewModel.updateNote {
                            backHome()
                        }
                    }) {
                        Icon(
                            Icons.Filled.Done,
                            contentDescription = "done",
                            Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                        Text(text = "Update!")
                    }

                }

            }
            if (state.note.id != 0)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(onClick = {
                        noteViewModel.deleteNote {
                            backHome()
                        }
                    }) {
                        Text(text = "Delete")
                    }

                }
        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun AddNotePreview() {
    MyNotesTheme {

        AddNoteScreen(viewModel()) {}
    }
}