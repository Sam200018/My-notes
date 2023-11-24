package mx.ipn.escom.bautistas.mynotes.domain.notes.model

import android.icu.text.CaseMap.Title
import mx.ipn.escom.bautistas.mynotes.data.notes.local.NoteEntity

data class Note(
    val id: Int = 0,
    val title: String = "",
    val body: String = ""
)


fun Note.toNoteEntity(): NoteEntity = NoteEntity(uid = id, title = title, body = body)
fun NoteEntity.toNote(): Note = Note(id = uid, title = title, body = body)