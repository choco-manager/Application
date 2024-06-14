package ru.dadyarri.choco.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import ru.dadyarri.choco.R
import ru.dadyarri.choco.ui.actions.LoginAction
import ru.dadyarri.choco.ui.form_fields.LoginFormField
import ru.dadyarri.choco.ui.state.LoginState

@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
) {
    LazyColumn {
        item {
            OutlinedTextField(
                value = state.login,
                onValueChange = { onAction(LoginAction.UpdateField(LoginFormField.Login, it)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Ascii,
                    imeAction = ImeAction.Next
                ),
                enabled = state.isFormEnabled,
                isError = !state.loginError?.asString().isNullOrBlank(),
                supportingText = {
                    if (!state.loginError?.asString().isNullOrBlank()) {
                        Text(text = state.loginError?.asString()!!)
                    }
                },
                label = {
                    Text(text = stringResource(R.string.login))
                }
            )
        }

        item {
            OutlinedTextField(
                value = state.password,
                onValueChange = { onAction(LoginAction.UpdateField(LoginFormField.Password, it)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
                visualTransformation = if (state.isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        onAction(LoginAction.Login)
                    }
                ),
                enabled = state.isFormEnabled,
                isError = !state.passwordError?.asString().isNullOrBlank(),
                supportingText = {
                    if (!state.passwordError?.asString().isNullOrBlank()) {
                        Text(text = state.passwordError?.asString()!!)
                    }
                },
                label = {
                    Text(text = stringResource(R.string.password))
                },
                trailingIcon = {
                    val image = if (state.isPasswordVisible)
                        Icons.Outlined.Visibility
                    else Icons.Outlined.VisibilityOff

                    IconButton(onClick = { onAction(LoginAction.TogglePasswordVisibility) }) {
                        Icon(imageVector = image, null)
                    }
                }
            )
        }

        item {
            TextButton(onClick = { onAction(LoginAction.ForgotPassword) }) {
                Text(text = stringResource(R.string.forgot_password))
            }
        }

        item {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onAction(LoginAction.Login)
                },
                enabled = state.isFormEnabled
            ) {
                Text(text = stringResource(R.string.log_in))
            }
        }
    }


}