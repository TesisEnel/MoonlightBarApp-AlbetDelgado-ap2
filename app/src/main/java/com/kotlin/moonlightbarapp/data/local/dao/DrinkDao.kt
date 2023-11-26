package com.kotlin.moonlightbarapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.kotlin.moonlightbarapp.data.local.entities.FavoriteDrinks
import kotlinx.coroutines.flow.Flow

@Dao
interface DrinkDao {
    @Upsert
    suspend fun save(drink: FavoriteDrinks)
    @Query(
        """
        SELECT * 
        FROM FavoriteDrinks 
        WHERE idDrink=:id  
        LIMIT 1
        """
    )
    suspend fun find(id: Int): FavoriteDrinks
    @Delete
    suspend fun delete(drink: FavoriteDrinks)

    @Query("SELECT * FROM FavoriteDrinks")
    fun getAll(): Flow<List<FavoriteDrinks>>
}