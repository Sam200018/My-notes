package mx.ipn.escom.bautistas.mynotes.ui.main.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.ipn.escom.bautistas.mynotes.domain.notes.DeleteNote
import mx.ipn.escom.bautistas.mynotes.domain.notes.GetNotes
import mx.ipn.escom.bautistas.mynotes.domain.notes.InsertNote
import mx.ipn.escom.bautistas.mynotes.domain.notes.UpdateNote
import mx.ipn.escom.bautistas.mynotes.domain.notes.model.Note
import mx.ipn.escom.bautistas.mynotes.ui.main.interaction.NoteState
import javax.inject.Inject

@HiltViewModel
class NoteViewModel
@Inject constructor(
    private val getNotes: GetNotes,
    private val insertNote: InsertNote,
    private val updateNote: UpdateNote,
    private val deleteNote: DeleteNote,
) : ViewModel() {

    private val _state = MutableStateFlow<NoteState>(NoteState())
    val state: StateFlow<NoteState> = _state.asStateFlow()


    var title: String by mutableStateOf("")
        private set

    var body: String by mutableStateOf("")
        private set


    init {
        collectNotes()
    }

    private fun collectNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            val fetchedNotes = getNotes()
            Log.d("Notes", fetchedNotes.toString())
            withContext(Dispatchers.Main) {
                _state.value = _state.value.copy(notes = fetchedNotes)
            }
        }
    }

    fun toNewNote(){
        title = ""
        body = ""
        _state.value.note = Note()
    }

    fun insertNote(backStack: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = Note(id = 0, title = title, body = body)
            insertNote(note)
            collectNotes()
        }
        backStack()
    }

    fun updateNote(backStack: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            var updatedNote = state.value.note.copy(title = title, body = body)
            updateNote(updatedNote)
            collectNotes()
        }
        backStack()
    }

    fun deleteNote(backStack: () -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            deleteNote(state.value.note)
            collectNotes()
        }
        backStack()
    }

    fun onTitleChanged(title: String) {
        this.title = title
    }

    fun onBodyChanged(body: String) {
        this.body = body
    }

    fun onSelectedNote(note: Note) {
        Log.d("Note", note.toString())
        _state.update { currentState ->
            currentState.copy(note = note)
        }
        title = state.value.note.title
        body = state.value.note.body
    }
}