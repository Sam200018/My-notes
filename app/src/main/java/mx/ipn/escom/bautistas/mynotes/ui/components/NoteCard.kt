package mx.ipn.escom.bautistas.mynotes.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.ipn.escom.bautistas.mynotes.domain.notes.model.Note

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: Note,
    onSelectedNote: (note: Note) -> Unit
) {
    Card(
        modifier.clickable { onSelectedNote(note) },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = note.title,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = note.body, maxLines = 3, overflow = TextOverflow.Ellipsis)
        }

    }

}

@Preview
@Composable
fun NoteCardPreview() {
    NoteCard(
        note = Note(
            0,
            "Viaje a Cuernavaca",
            "oa" +
                    "pdsjajsdklajdsjsakldjasldj" +
                    "askdjlasjkalkdas" +
                    "djklasjdlkja akdjlkajdkjaskdjaksljdsakjdsakj akjsdkjaskjas "
        ), onSelectedNote = {}
    )
}