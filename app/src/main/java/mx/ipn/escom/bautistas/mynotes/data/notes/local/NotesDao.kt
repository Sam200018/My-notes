package mx.ipn.escom.bautistas.mynotes.data.notes.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import mx.ipn.escom.bautistas.mynotes.utils.Constants

@Dao
interface NotesDao {
    @Query("SELECT * FROM ${Constants.note_table_name}")
    fun getAll(): List<NoteEntity>

    @Query("SELECT * FROM ${Constants.note_table_name} WHERE uid IN (:noteId)")
    fun loadAllById(noteId: Int): List<NoteEntity>

    @Query("SELECT * FROM ${Constants.note_table_name} WHERE title LIKE :title LIMIT 1")
    fun findByName(title: String): NoteEntity

    @Insert
    fun insert(note: NoteEntity)

    @Delete
    fun delete(note: NoteEntity)

    @Update
    fun update(note: NoteEntity)
}