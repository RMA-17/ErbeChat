package com.rmaprojects.shared.core.data.repository

import com.rmaprojects.shared.core.data.source.local.LocalDataSource
import com.rmaprojects.shared.core.data.source.remote.RemoteDataSource
import com.rmaprojects.shared.core.domain.repository.ChatRepository
import com.russhwolf.settings.ObservableSettings

class ChatRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
): ChatRepository {

}