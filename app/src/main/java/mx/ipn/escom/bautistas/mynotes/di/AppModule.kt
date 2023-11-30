package mx.ipn.escom.bautistas.mynotes.di

import android.content.Context
import androidx.room.Room
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.ipn.escom.bautistas.mynotes.data.notes.model.NoteDatabase
import mx.ipn.escom.bautistas.mynotes.data.notes.local.NotesDao
import mx.ipn.escom.bautistas.mynotes.data.signin.GoogleAuthUiClient
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

    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun providesGoogleUiClient(
        @ApplicationContext app: Context,
        firebaseAuth: FirebaseAuth
    ): GoogleAuthUiClient = GoogleAuthUiClient(
        context = app,
        oneTapClient = Identity.getSignInClient(
            app
        ),
        firebaseAuth
    )
}