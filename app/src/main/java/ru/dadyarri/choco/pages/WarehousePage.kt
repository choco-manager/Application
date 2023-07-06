package ru.dadyarri.choco.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import ru.dadyarri.choco.R
import ru.dadyarri.choco.pageWrapper.PageWrapper

@Composable
fun WarehousePage(isWholesalePricesEnabled: MutableState<Boolean>) {
    PageWrapper(stringResource(R.string.page_warehouse)) {}
}