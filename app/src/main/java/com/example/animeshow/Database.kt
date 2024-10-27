package com.example.animeshow

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(animeList: List<AnimeData>)

    @Query("SELECT * FROM anime_table")
    suspend fun getAllAnimes(): List<AnimeData>
    @Query("SELECT * FROM anime_table WHERE id = :animeId")
    suspend fun getAnimeById(animeId: String): AnimeData?
}


@Database(entities = [AnimeData::class], version = 1)
@TypeConverters(Converters::class)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}

object DatabaseInstance {
    private var INSTANCE: AnimeDatabase? = null

    fun getDatabase(context: Context): AnimeDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AnimeDatabase::class.java,
                "anime_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}

class Converters {
    @TypeConverter
    fun fromAttributes(attributes: Attributes?): String {
        return Gson().toJson(attributes)
    }

    @TypeConverter
    fun toAttributes(attributesString: String): Attributes? {
        val type = object : TypeToken<Attributes>() {}.type
        return Gson().fromJson(attributesString, type)
    }
}