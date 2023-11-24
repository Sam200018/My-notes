package mx.ipn.escom.bautistas.mynotes.domain.notes

import mx.ipn.escom.bautistas.mynotes.data.notes.NoteRepository
import mx.ipn.escom.bautistas.mynotes.domain.notes.model.Note
import mx.ipn.escom.bautistas.mynotes.domain.notes.model.toNote
import javax.inject.Inject

class GetNotes @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(): List<Note> {
        return noteRepository.getAllNotes().map {
            it.toNote()
        }
    }
}