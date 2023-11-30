package mx.ipn.escom.bautistas.mynotes.domain.notes.model

import android.net.Uri
import mx.ipn.escom.bautistas.mynotes.data.notes.local.NoteEntity

data class Note(
    val id: Int = 0,
    val title: String = "",
    val body: String = "",
    val image: Uri? = null,
)


fun Note.toNoteEntity(): NoteEntity {
    return if (image != null) {
        NoteEntity(uid = id, title = title, body = body, image = image.toString())

    } else
        NoteEntity(uid = id, title = title, body = body, image = null)
}

fun NoteEntity.toNote(): Note {
    return if (image != null) {

        Note(id = uid, title = title, body = body, image = Uri.parse(image))
    } else {

        Note(id = uid, title = title, body = body, image = null)
    }
}