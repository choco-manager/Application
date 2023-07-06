package ru.dadyarri.choco.pages

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.dadyarri.choco.R
import ru.dadyarri.choco.components.SwitchWithLabel
import ru.dadyarri.choco.pageWrapper.PageWrapper

@Composable
fun ProfilePage(isInDarkTheme: MutableState<Boolean>) {
    PageWrapper(stringResource(R.string.page_profile)) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            SwitchWithLabel(label = "Тёмная тема", state = isInDarkTheme.value, onStateChange = {
                isInDarkTheme.value = it
            })
            Divider()
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Версия приложения", style = MaterialTheme.typography.labelLarge)
                Text(text = "2.0", style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}