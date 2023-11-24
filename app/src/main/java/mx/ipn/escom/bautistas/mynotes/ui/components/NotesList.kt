package mx.ipn.escom.bautistas.mynotes.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.ipn.escom.bautistas.mynotes.domain.notes.model.Note

@Composable
fun NotesList(
    modifier: Modifier = Modifier,
    notes: List<Note>,
    onSelectedNote: (note: Note) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 175.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(items = notes) { note ->
            NoteCard(
                modifier = Modifier
                    .padding(10.dp)
                    .height(100.dp)
                    .width(150.dp),
                note = note,
                onSelectedNote = onSelectedNote
            )

        }
    }
}

@Preview
@Composable
fun NotesListPreview() {
    val notes = listOf<Note>(
        Note(
            0,
            "Viaje a Cuernavaca",
            "oa" +
                    "pdsjajsdklajdsjsakldjasldj" +
                    "askdjlasjkalkdas" +
                    "djklasjdlkja akdjlkajdkjaskdjaksljdsakjdsakj akjsdkjaskjas "
        ),
        Note(
            0,
            "Viaje a Cuernavaca",
            "oa" +
                    "pdsjajsdklajdsjsakldjasldj" +
                    "askdjlasjkalkdas" +
                    "djklasjdlkja akdjlkajdkjaskdjaksljdsakjdsakj akjsdkjaskjas "
        ),
        Note(
            0,
            "Viaje a Cuernavaca",
            "oa" +
                    "pdsjajsdklajdsjsakldjasldj" +
                    "askdjlasjkalkdas" +
                    "djklasjdlkja akdjlkajdkjaskdjaksljdsakjdsakj akjsdkjaskjas "
        ),
        Note(
            0,
            "Viaje a Cuernavaca",
            "oa" +
                    "pdsjajsdklajdsjsakldjasldj" +
                    "askdjlasjkalkdas" +
                    "djklasjdlkja akdjlkajdkjaskdjaksljdsakjdsakj akjsdkjaskjas "
        ),
        Note(
            0,
            "Viaje a Cuernavaca",
            "oa" +
                    "pdsjajsdklajdsjsakldjasldj" +
                    "askdjlasjkalkdas" +
                    "djklasjdlkja akdjlkajdkjaskdjaksljdsakjdsakj akjsdkjaskjas "
        ),
        Note(
            0,
            "Viaje a Cuernavaca",
            "oa" +
                    "pdsjajsdklajdsjsakldjasldj" +
                    "askdjlasjkalkdas" +
                    "djklasjdlkja akdjlkajdkjaskdjaksljdsakjdsakj akjsdkjaskjas "
        ),
    )
    NotesList(notes = notes) {}
}