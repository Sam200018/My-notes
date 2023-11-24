package mx.ipn.escom.bautistas.mynotes.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.ipn.escom.bautistas.mynotes.data.notes.model.NoteDatabase
import mx.ipn.escom.bautistas.mynotes.data.notes.local.NotesDao
import mx.ipn.escom.bautistas.mynotes.utils.Constants
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providesNoteRoomDatabase(@ApplicationContext app: Context): NoteDatabase =
        Room.databaseBuilder(
            app, NoteDatabase::class.java, Constants.note_database
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun providesNotesDao(noteDatabase: NoteDatabase): NotesDao = noteDatabase.notesDao()
}