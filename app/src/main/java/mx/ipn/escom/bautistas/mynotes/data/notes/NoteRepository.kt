package mx.ipn.escom.bautistas.mynotes.data.notes

import mx.ipn.escom.bautistas.mynotes.data.notes.local.NoteEntity
import mx.ipn.escom.bautistas.mynotes.data.notes.local.NotesDao
import javax.inject.Inject


class NoteRepository @Inject constructor(
    private val notesDao: NotesDao
) {

    fun getAllNotes(): List<NoteEntity> = notesDao.getAll()

    fun insertNote(noteEntity: NoteEntity) = notesDao.insert(noteEntity)

    fun updateNote(noteEntity: NoteEntity) = notesDao.update(noteEntity)

    fun deleteNote(noteEntity: NoteEntity) = notesDao.delete(noteEntity)
}