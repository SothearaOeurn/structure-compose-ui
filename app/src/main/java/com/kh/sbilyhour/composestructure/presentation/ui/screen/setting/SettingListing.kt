package com.kh.sbilyhour.composestructure.presentation.ui.screen.setting

import com.kh.sbilyhour.composestructure.presentation.ui.widgets.expandable.Section
import com.kh.sbilyhour.composestructure.presentation.ui.widgets.expandable.SubListItem

class SettingListing {
    fun listing() = listOf(
        Section(
            title = "1. Staff Information",
            subList = listOf(
                SubListItem(keyword = "Code", keyValue = "KH0010001"),
                SubListItem(keyword = "Name", keyValue = "Head Office"),
                SubListItem(keyword = "RM-Name", keyValue = "Chy Sarat")
            )
        ),
        Section(
            title = "2. Customer Information",
            subList = listOf(
                SubListItem(keyword = "CIF#", keyValue = "1166508"),
                SubListItem(keyword = "Acc Number", keyValue = "000372579"),
                SubListItem(keyword = "Account Name", keyValue = "MARK SREYLONG"),
                SubListItem(
                    keyword = "Address",
                    keyValue = "Phnom Penh/Chamkar Mon/Tonle Basak/Phum 9"
                )
            )
        ),
        Section(
            title = "1.  Loan Information",
            subList = listOf(
                SubListItem(keyword = "Currency", keyValue = "USD"),
                SubListItem(keyword = "Disburse Amount", keyValue = "0.00"),
                SubListItem(keyword = "Disburse Date", keyValue = "06-10-2020"),
                SubListItem(keyword = "Outstanding Amount", keyValue = "187,939.59"),
                SubListItem(keyword = "Product Type", keyValue = "MORTGAGE.HOUSING.LOAN.LT"),
            )
        ),
    )
}