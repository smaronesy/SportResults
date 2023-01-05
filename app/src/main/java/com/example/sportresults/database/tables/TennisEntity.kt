package com.example.sportresults.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportresults.model.Tennis

@Entity(tableName="tennis_results")
data class TennisEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "looser")
    var looser: String = "",

    @ColumnInfo(name = "number_of_sets")
    val numberOfSets: Int = -1,

    @ColumnInfo(name = "publication_date")
    val publicationDate: Long = -1,

    @ColumnInfo(name = "tournament")
    val tournament: String = "",

    @ColumnInfo(name = "winner")
    val winner: String = "",
)

fun TennisEntity.toTennis(): Tennis {
    return Tennis(
        looser = looser,
        numberOfSets = numberOfSets,
        publicationDate = publicationDate,
        tournament = tournament,
        winner = winner
    )
}

fun Tennis.toTennisEntity(): TennisEntity {
    return TennisEntity(
        looser = looser,
        numberOfSets = numberOfSets,
        publicationDate = publicationDate,
        tournament = tournament,
        winner = winner
    )
}

fun List<TennisEntity>.asDatabaseObject(): List<Tennis> {
    return map {
        Tennis(
            looser = it.looser,
            numberOfSets = it.numberOfSets,
            publicationDate = it.publicationDate,
            tournament = it.tournament,
            winner = it.winner)
    }
}

fun List<Tennis>.asDatabaseObject(): Array<TennisEntity> {
    return map {
        TennisEntity(
            looser = it.looser,
            numberOfSets = it.numberOfSets,
            publicationDate = it.publicationDate,
            tournament = it.tournament,
            winner = it.winner)
    }.toTypedArray()
}
