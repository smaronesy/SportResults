package com.example.sportresults.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportresults.model.Fone

@Entity(tableName="fone_results")
data class FoneEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "publication_date")
    val publicationDate: Long = -1,

    @ColumnInfo(name = "seconds")
    var seconds: Double = -1.0,

    @ColumnInfo(name = "tournament")
    var tournament: String = "",

    @ColumnInfo(name = "winner")
    val winner: String = "",

    )

fun FoneEntity.toFone(): Fone {
    return Fone(
        publicationDate = publicationDate,
        tournament = tournament,
        seconds = seconds,
        winner = winner
    )
}

fun Fone.toFoneEntity(): FoneEntity {
    return FoneEntity(
        publicationDate = publicationDate,
        tournament = tournament,
        seconds = seconds,
        winner = winner
    )
}

fun List<FoneEntity>.asDatabaseObject(): List<Fone> {
    return map {
        Fone(
            publicationDate = it.publicationDate,
            tournament = it.tournament,
            seconds = it.seconds,
            winner = it.winner
        )
    }
}

fun List<Fone>.asDatabaseObject(): Array<FoneEntity> {
    return map {
        FoneEntity(
            publicationDate = it.publicationDate,
            tournament = it.tournament,
            seconds = it.seconds,
            winner = it.winner
        )
    }.toTypedArray()
}