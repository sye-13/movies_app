package com.example.movies_app.configuration.data

import com.example.movies_app.configuration.domain.ConfigurationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ConfigurationRepositoryImpl @Inject constructor(
    private val dataSource: ConfigurationDataSource
) : ConfigurationRepository {

    private var memoryCache: ConfigurationApiModel? = null

    override fun fetchConfiguration(): Flow<ConfigurationApiModel> =
        flow {
            memoryCache?.let {
                emit(it)
                return@flow
            }
            try {
                val configuration = dataSource.fetchConfiguration()
                memoryCache = configuration
                emit(configuration)
            } catch (e: Exception) {
                emit(
                    ConfigurationApiModel(
                        images = ConfigurationImage(
                            baseUrl = ""
                        )
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
}
