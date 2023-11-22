package com.kotlin.moonlightbarapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.moonlightbarapp.data.repository.DrinkRepository
import com.kotlin.moonlightbarapp.util.DrinkListState
import com.kotlin.moonlightbarapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val drinkRepository: DrinkRepository
): ViewModel() {

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

}