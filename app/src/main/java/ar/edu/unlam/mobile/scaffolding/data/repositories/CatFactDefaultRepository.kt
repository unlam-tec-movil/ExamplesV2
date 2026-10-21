package ar.edu.unlam.mobile.scaffolding.data.repositories

import ar.edu.unlam.mobile.scaffolding.data.datasources.network.MeowFactsAPI
import ar.edu.unlam.mobile.scaffolding.data.datasources.network.toModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CatFactDefaultRepository
    @Inject
    constructor(
        private val meowFactsAPI: MeowFactsAPI,
    ) : CatFactRepository {
        override suspend fun getCatFact(): Flow<CatFact> =
            flow {
                emit(
                    meowFactsAPI.getCatFact().toModel(),
                )
            }

        override suspend fun getCatFact(amount: Int): List<CatFact> = meowFactsAPI.getCatFact(amount).data.map { CatFact(it) }
    }
