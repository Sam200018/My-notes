package mx.ipn.escom.bautistas.mynotes.ui.main.interaction

import mx.ipn.escom.bautistas.mynotes.domain.notes.model.Note

data class NoteState(
    var notes: List<Note> = emptyList(),
    var note: Note = Note()
)
