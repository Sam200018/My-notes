package mx.ipn.escom.bautistas.mynotes.data.notes.model

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.ipn.escom.bautistas.mynotes.data.notes.local.NoteEntity
import mx.ipn.escom.bautistas.mynotes.data.notes.local.NotesDao

@Database(entities = [NoteEntity::class], version = 2)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}