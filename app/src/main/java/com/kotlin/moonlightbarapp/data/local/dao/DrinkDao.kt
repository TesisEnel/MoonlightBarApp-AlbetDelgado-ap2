package com.kotlin.moonlightbarapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.kotlin.moonlightbarapp.data.local.entities.FavoriteDrink
import kotlinx.coroutines.flow.Flow

@Dao
interface DrinkDao {
    @Upsert
    suspend fun save(drink: FavoriteDrink)
    @Query(
        """
        SELECT * 
        FROM FavoriteDrink 
        WHERE idDrink=:id  
        LIMIT 1
        """
    )
    suspend fun find(id: Int): FavoriteDrink
    @Delete
    suspend fun delete(drink: FavoriteDrink)

    @Query("SELECT * FROM FavoriteDrink")
    fun getAll(): Flow<List<FavoriteDrink>>
}