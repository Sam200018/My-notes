package mx.ipn.escom.bautistas.mynotes.domain.notes

import mx.ipn.escom.bautistas.mynotes.data.notes.NoteRepository
import mx.ipn.escom.bautistas.mynotes.domain.notes.model.Note
import mx.ipn.escom.bautistas.mynotes.domain.notes.model.toNoteEntity
import javax.inject.Inject

class InsertNote @Inject constructor(
    private val noteRepository: NoteRepository
) {

    suspend operator fun invoke(note: Note){
        return noteRepository.insertNote(note.toNoteEntity())
    }
}