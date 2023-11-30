package mx.ipn.escom.bautistas.mynotes.ui.main.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import mx.ipn.escom.bautistas.mynotes.ui.main.interaction.SignInState
import mx.ipn.escom.bautistas.mynotes.data.signin.model.SignInResult
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    private val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState.asStateFlow()


    fun onSignInResult(result: SignInResult) {
        _signInState.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                errorMessage = result.errorMessage
            )
        }
    }

    fun resetState() {
        _signInState.update { SignInState() }
    }

}