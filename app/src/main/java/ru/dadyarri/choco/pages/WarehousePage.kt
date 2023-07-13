package ru.dadyarri.choco.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import ru.dadyarri.choco.MainActivityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarehousePage(viewModel: MainActivityViewModel = hiltViewModel()) {
    Column {
        ListItem(headlineText = { Text(text = "") })
    }
}
