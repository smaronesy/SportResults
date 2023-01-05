package com.example.sportresults.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportresults.database.tables.FoneEntity
import com.example.sportresults.database.tables.NbaEntity
import com.example.sportresults.database.tables.TennisEntity

@Dao
interface ResultsDao {

    /**************************************************************************************************
     ***************************************** F1 table queries **************************************
     *************************************************************************************************/

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = FoneEntity::class)
    fun insertAllFoneResults(vararg results: FoneEntity)

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM fone_results")
    suspend fun clearFoneResults()

    /**
     * Selects and returns all rows in the table,
     *s
     * sorted by start time in descending order.
     */
    @Query("SELECT * FROM fone_results ORDER BY publication_date DESC")
    fun getAllFoneResults(): LiveData<List<FoneEntity>>

    /**
     * Get the latest Fone result
     */
    @Query("SELECT * FROM fone_results ORDER BY publication_date DESC LIMIT 1")
    fun getLatestFoneResult(): LiveData<FoneEntity>

    /**************************************************************************************************
     ***************************************** NBA table queries **************************************
     *************************************************************************************************/

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = NbaEntity::class)
    fun insertAllNbaResults(vararg results: NbaEntity)

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM nba_results")
    suspend fun clearNbaResults()

    /**
     * Selects and returns all rows in the table,
     *s
     * sorted by start time in descending order.
     */
    @Query("SELECT * FROM nba_results ORDER BY publication_date DESC")
    fun getAllNbaResults(): LiveData<List<NbaEntity>>

    /**
     * Get the latest Fone result
     */
    @Query("SELECT * FROM nba_results ORDER BY publication_date DESC LIMIT 1")
    fun getLatestNbaResult(): LiveData<NbaEntity>

    /**************************************************************************************************
     ***************************************** Tennis table queries ***********************************
     *************************************************************************************************/

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TennisEntity::class)
    fun insertAllTennisResults(vararg results: TennisEntity)

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM tennis_results")
    suspend fun clearTennisResults()

    /**
     * Selects and returns all rows in the table,
     *s
     * sorted by start time in descending order.
     */
    @Query("SELECT * FROM tennis_results ORDER BY publication_date DESC")
    fun getAllTennisResults(): LiveData<List<TennisEntity>>

    /**
     * Get the latest Fone result
     */
    @Query("SELECT * FROM tennis_results ORDER BY publication_date DESC LIMIT 1")
    fun getLatestTennisResult(): LiveData<TennisEntity>
}