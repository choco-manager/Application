package ru.dadyarri.choco.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import ru.dadyarri.choco.R
import ru.dadyarri.choco.ui.actions.RequestPasswordRestorationAction
import ru.dadyarri.choco.ui.form_fields.RequestPasswordRestorationFormField
import ru.dadyarri.choco.ui.state.RequestPasswordRestorationState

@Composable
fun RequestPasswordRestorationScreen(
    state: RequestPasswordRestorationState,
    onAction: (RequestPasswordRestorationAction) -> Unit
) {
    OutlinedTextField(
        value = state.login,
        onValueChange = {
            onAction(
                RequestPasswordRestorationAction.UpdateField(
                    RequestPasswordRestorationFormField.Login,
                    it
                )
            )
        },
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

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            onAction(RequestPasswordRestorationAction.RequestRestoration)
        },
        enabled = state.isFormEnabled
    ) {
        Text(text = stringResource(R.string.restore_password))
    }
}