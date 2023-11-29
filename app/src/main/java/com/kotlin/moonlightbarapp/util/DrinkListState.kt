package com.kotlin.moonlightbarapp.util

import com.kotlin.moonlightbarapp.data.remote.dto.DrinkDto

data class DrinkListState(
    val isLoading: Boolean = false,
    val drinks: List<DrinkDto> = emptyList(),
    val popularDrinks: List<DrinkDto> = emptyList(),
    val drinksByLetter: List<DrinkDto> = emptyList(),
    val error: String = "",
    val drink: DrinkDto? = null,
)
