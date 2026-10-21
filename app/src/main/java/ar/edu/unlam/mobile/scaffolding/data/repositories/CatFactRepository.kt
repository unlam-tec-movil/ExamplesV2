package ar.edu.unlam.mobile.scaffolding.data.repositories

import kotlinx.coroutines.flow.Flow

interface CatFactRepository {
    suspend fun getCatFact(): Flow<CatFact>

    suspend fun getCatFact(amount: Int): List<CatFact>
}
