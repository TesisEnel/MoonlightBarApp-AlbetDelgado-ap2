package com.kotlin.moonlightbarapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.moonlightbarapp.data.local.dao.DrinkDao
import com.kotlin.moonlightbarapp.data.local.entities.FavoriteDrink

@Database(
    entities = [FavoriteDrink::class],
    version = 4,
    exportSchema = false
)
abstract class Database : RoomDatabase(){
    abstract fun drinkDao(): DrinkDao
}