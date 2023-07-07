package ru.dadyarri.choco.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import ru.dadyarri.choco.R
import ru.dadyarri.choco.pageWrapper.PageWrapper

@Composable
fun ProfilePage(
    isInDarkTheme: MutableState<Boolean>,
    isWholesalePricesEnabled: MutableState<Boolean>
) {
    PageWrapper(stringResource(R.string.page_profile)) {
        Column {
            ListItem(
                headlineContent = { Text(text = "Тёмная тема") },
                trailingContent = {
                    Switch(
                        checked = isInDarkTheme.value,
                        onCheckedChange = { isInDarkTheme.value = it })
                }
            )
            ListItem(
                headlineContent = { Text(text = "Оптовые цены") },
                trailingContent = {
                    Switch(
                        checked = isWholesalePricesEnabled.value,
                        onCheckedChange = { isWholesalePricesEnabled.value = it })
                }
            )
            Divider()
            ListItem(
                headlineContent = { Text(text = "Версия приложения") },
                trailingContent = {
                    Text(
                        text = "1.0"
                    )
                }
            )
        }
    }
}