package mx.ipn.escom.bautistas.mynotes

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import mx.ipn.escom.bautistas.mynotes.data.notes.model.NoteDatabase
import mx.ipn.escom.bautistas.mynotes.data.notes.local.NoteEntity
import mx.ipn.escom.bautistas.mynotes.data.notes.local.NotesDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class NoteDatabaseInstrumentedTest {
    private lateinit var notesDao: NotesDao
    private lateinit var db: NoteDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, NoteDatabase::class.java
        ).build()
        notesDao = db.notesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeNoteAndReadAllNotes() {
//        Create a note instance
        val title = "Viaje"
        val body = "Alistar comida"
        val noteEntity = NoteEntity(uid = 1, title = title, body = body)

//        Insert note into notes db
        notesDao.insert(noteEntity)

//        read all notes in db
        val notes = notesDao.getAll()

//        delete the inserted note
        notesDao.delete(noteEntity)

//        Show notes list in console
        println(notes)

//        Test if we got the expected result

        assertEquals(notes[0].title, title)

    }
}