package ru.dadyarri.choco.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import ru.dadyarri.choco.R
import ru.dadyarri.choco.ui.actions.RestorePasswordAction
import ru.dadyarri.choco.ui.form_fields.RestorePasswordFormField
import ru.dadyarri.choco.ui.state.RestorePasswordState

@Composable
fun RestorePasswordScreen(
    state: RestorePasswordState,
    onAction: (RestorePasswordAction) -> Unit,
) {
    LazyColumn {


        item {
            OutlinedTextField(
                value = state.password,
                onValueChange = {
                    onAction(
                        RestorePasswordAction.UpdateField(
                            RestorePasswordFormField.Password,
                            it
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                ),
                visualTransformation = if (state.isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
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

                    IconButton(onClick = { onAction(RestorePasswordAction.TogglePasswordVisibility) }) {
                        Icon(imageVector = image, null)
                    }
                }
            )
        }

        item {
            OutlinedTextField(
                value = state.repeatPassword,
                onValueChange = {
                    onAction(
                        RestorePasswordAction.UpdateField(
                            RestorePasswordFormField.RepeatPassword,
                            it
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                ),
                visualTransformation = if (state.isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                enabled = state.isFormEnabled,
                isError = !state.repeatPasswordError?.asString().isNullOrBlank(),
                supportingText = {
                    if (!state.repeatPasswordError?.asString().isNullOrBlank()) {
                        Text(text = state.repeatPasswordError?.asString()!!)
                    }
                },
                label = {
                    Text(text = stringResource(R.string.repeat_password))
                },
            )
        }

        item {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onAction(RestorePasswordAction.Restore)
                },
                enabled = state.isFormEnabled
            ) {
                Text(text = stringResource(R.string.restore_password))
            }
        }
    }
}