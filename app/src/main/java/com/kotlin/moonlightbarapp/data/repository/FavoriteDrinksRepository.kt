package com.kotlin.moonlightbarapp.data.repository

import com.kotlin.moonlightbarapp.data.local.dao.DrinkDao
import com.kotlin.moonlightbarapp.data.local.entities.FavoriteDrinks
import javax.inject.Inject

class FavoriteDrinksRepository @Inject constructor(
    private val drinkDao: DrinkDao
) {
    suspend fun save(drink: FavoriteDrinks) = drinkDao.save(drink)
    suspend fun delete(drink: FavoriteDrinks) = drinkDao.delete(drink)
    fun getAll() = drinkDao.getAll()
}