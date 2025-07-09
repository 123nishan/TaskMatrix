package com.nishan.taskmatrix.domain.repository

import com.nishan.taskmatrix.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insertTask(task: Task)

    fun fetchAllTasks(): Flow<List<Task>>
}
