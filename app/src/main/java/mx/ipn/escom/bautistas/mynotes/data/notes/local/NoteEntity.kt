package mx.ipn.escom.bautistas.mynotes.data.notes.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.ipn.escom.bautistas.mynotes.domain.notes.model.Note
import mx.ipn.escom.bautistas.mynotes.utils.Constants

@Entity(tableName = Constants.note_table_name)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body") val body: String
)
