package com.kotlin.moonlightbarapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.moonlightbarapp.data.local.entities.FavoriteDrinks
import com.kotlin.moonlightbarapp.data.remote.dto.DrinkDto
import com.kotlin.moonlightbarapp.data.repository.DrinkRepository
import com.kotlin.moonlightbarapp.data.repository.FavoriteDrinksRepository
import com.kotlin.moonlightbarapp.util.DrinkListState
import com.kotlin.moonlightbarapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val drinkRepository: DrinkRepository,
    private val favoriteDrinksRepository: FavoriteDrinksRepository
): ViewModel() {

    var drink by mutableStateOf(DrinkDto())

    private val _uiState = MutableStateFlow(DrinkListState())
    val uiState: StateFlow<DrinkListState> = _uiState.asStateFlow()

    init {
        loadScreen()
    }
    fun loadScreen() {
        drinkRepository.getRandomCocktail().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Resource.Success -> {
                    _uiState.update { it.copy(drinks = result.data ?: emptyList(), isLoading = false) }
                }

                is Resource.Error -> {
                    _uiState.update { it.copy(error = result.message ?: "Error desconocido", isLoading = false) }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getCocktailById(id: String) {
        viewModelScope.launch {
            drink = drinkRepository.getCocktailById(id)!!
        }
    }

    fun save(cocktail: DrinkDto){
        viewModelScope.launch {
            val drink = FavoriteDrinks(
                strDrink = cocktail.strDrink,
                strDrinkAlternate = cocktail.strDrinkAlternate,
                strTags = cocktail.strTags,
                strVideo = cocktail.strVideo,
                strCategory = cocktail.strCategory,
                strIBA = cocktail.strIBA,
                strAlcoholic = cocktail.strAlcoholic,
                strGlass = cocktail.strGlass,
                strInstructions = cocktail.strInstructions,
                strInstructionsES = cocktail.strInstructionsES,
                strDrinkThumb = cocktail.strDrinkThumb,
                strIngredient1 = cocktail.strIngredient1,
                strIngredient2 = cocktail.strIngredient2,
                strIngredient3 = cocktail.strIngredient3,
                strIngredient4 = cocktail.strIngredient4,
                strIngredient5 = cocktail.strIngredient5,
                strIngredient6 = cocktail.strIngredient6,
                strIngredient7 = cocktail.strIngredient7,
                strIngredient8 = cocktail.strIngredient8,
                strIngredient9 = cocktail.strIngredient9,
                strIngredient10 = cocktail.strIngredient10,
                strIngredient11 = cocktail.strIngredient11,
                strIngredient12 = cocktail.strIngredient12,
                strIngredient13 = cocktail.strIngredient13,
                strIngredient14 = cocktail.strIngredient14,
                strIngredient15 = cocktail.strIngredient15,
                strMeasure1 = cocktail.strMeasure1,
                strMeasure2 = cocktail.strMeasure2,
                strMeasure3 = cocktail.strMeasure3,
                strMeasure4 = cocktail.strMeasure4,
                strMeasure5 = cocktail.strMeasure5,
                strMeasure6 = cocktail.strMeasure6,
                strMeasure7 = cocktail.strMeasure7,
                strMeasure8 = cocktail.strMeasure8,
                strMeasure9 = cocktail.strMeasure9,
                strMeasure10 = cocktail.strMeasure10,
                strMeasure11 = cocktail.strMeasure11,
                strMeasure12 = cocktail.strMeasure12,
                strMeasure13 = cocktail.strMeasure13,
                strMeasure14 = cocktail.strMeasure14,
                strMeasure15 = cocktail.strMeasure15,
                isFavorite = true
            )
            favoriteDrinksRepository.save(drink)
        }
    }

    fun delete(favoriteDrink: FavoriteDrinks){
        viewModelScope.launch {
            favoriteDrinksRepository.delete(favoriteDrink)
        }
    }

    val favoriteDrinks: StateFlow<List<FavoriteDrinks>> = favoriteDrinksRepository.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(2_000),
        initialValue = emptyList()
    )


}