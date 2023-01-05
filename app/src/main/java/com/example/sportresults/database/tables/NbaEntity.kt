package com.example.sportresults.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportresults.model.Nba

@Entity(tableName="nba_results")
data class NbaEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "game_number")
    val gameNumber: Int = -1,

    @ColumnInfo(name = "looser")
    var looser: String = "",

    @ColumnInfo(name = "mvp")
    var mvp: String = "",

    @ColumnInfo(name = "publication_date")
    val publicationDate: Long = -1,

    @ColumnInfo(name = "tournament")
    val tournament: String = "",

    @ColumnInfo(name = "winner")
    val winner: String = "",
)

fun NbaEntity.toTennis(): Nba {
    return Nba(
        gameNumber = gameNumber,
        looser = looser,
        mvp = mvp,
        publicationDate = publicationDate,
        tournament = tournament,
        winner = winner
    )
}

fun Nba.toTennisEntity(): NbaEntity {
    return NbaEntity(
        gameNumber = gameNumber,
        looser = looser,
        mvp = mvp,
        publicationDate = publicationDate,
        tournament = tournament,
        winner = winner
    )
}

fun List<NbaEntity>.asDatabaseObject(): List<Nba> {
    return map {
        Nba(
            gameNumber = it.gameNumber,
            looser = it.looser,
            mvp = it.mvp,
            publicationDate = it.publicationDate,
            tournament = it.tournament,
            winner = it.winner)
    }
}

fun List<Nba>.asDatabaseObject(): Array<NbaEntity> {
    return map {
        NbaEntity(
            gameNumber = it.gameNumber,
            looser = it.looser,
            mvp = it.mvp,
            publicationDate = it.publicationDate,
            tournament = it.tournament,
            winner = it.winner)
    }.toTypedArray()
}
