package com.example.readingappjet.screens.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.readingappjet.R
import com.example.readingappjet.components.EmailInput
import com.example.readingappjet.components.HeaderName
import com.example.readingappjet.components.PasswordInput

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ReaderLoginScreen(navController: NavController) {
    val showLoginForm = rememberSaveable{
        mutableStateOf(true)

    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {
            HeaderName()
            if(showLoginForm.value){
                UserLoginForm(loading = false,isCreateAccount = false){
                        email,password ->
                   //TODO: Login the User
                }
            }else{
                UserLoginForm(loading = false, isCreateAccount = true){
                    email,password ->
                    //TODO: create Account logic
                }
            }

        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(showLoginForm.value) {
                val text = "Sign up"
                Text(text = "New User?")
                Text(text,
                    modifier = Modifier
                        .clickable {
                            showLoginForm.value = !showLoginForm.value
                        }
                        .padding(start = 5.dp),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.secondaryVariant)
            } else{
                val text = "Login"
                Text(text = "Already have an account? ")
                Text(text,
                    modifier = Modifier
                        .clickable {
                            showLoginForm.value = !showLoginForm.value
                        }
                        .padding(start = 5.dp),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.secondaryVariant)
            }

        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserLoginForm(
    loading: Boolean = false,
    isCreateAccount: Boolean = false,
    onDone:(String,String) -> Unit = {email, pwd ->}
){
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisibility = rememberSaveable {
        mutableStateOf(false)
    }
    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value){
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    val modifier = Modifier
        .height(250.dp)
        .background(MaterialTheme.colors.background)
        .verticalScroll(rememberScrollState())

    Column(modifier,
        horizontalAlignment = Alignment.CenterHorizontally){
        if(isCreateAccount) Text(text = stringResource(id = R.string.create_account_info),
        modifier = Modifier.padding(4.dp))
        EmailInput(emailState = email, enabled = true, onAction = KeyboardActions {
            passwordFocusRequest.requestFocus()
        })
        PasswordInput(
            modifier = Modifier.focusRequester(passwordFocusRequest),
            passwordState = password,
            labelId = "password",
            enabled = !loading,
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions {
                if(!valid) return@KeyboardActions
                onDone(email.value.trim(), password.value.trim())
            }
        )
        SubmitButton(
            textId = if(isCreateAccount) "Create Account" else "Login",
            loading = loading,
            validInputs = valid
        ){
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }

}

@Composable
fun SubmitButton(textId: String,
                 loading: Boolean,
                  validInputs: Boolean,
                    onClick: () -> Unit) {
    Button(onClick = onClick,
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth(),
                enabled = !loading && validInputs){
        Button(onClick = onClick,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        enabled = !loading && validInputs,
        shape = CircleShape,
        ) {
            if (loading) CircularProgressIndicator(modifier = Modifier.size(25.dp))
            else Text(text = textId, modifier = Modifier.padding(5.dp))

        }
    }
}


