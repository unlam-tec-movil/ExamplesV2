package ar.edu.unlam.mobile.scaffolding.data.datasources.network

import ar.edu.unlam.mobile.scaffolding.data.repositories.CatFact
import kotlinx.serialization.Serializable

@Serializable
data class MeowFactDTO(
    val data: List<String>,
)

fun MeowFactDTO.toModel(): CatFact =
    CatFact(
        fact = data.firstOrNull() ?: "",
    )
