package com.nishan.taskmatrix.domain.repository

import com.nishan.taskmatrix.domain.model.Task

interface TaskRepository {
    suspend fun insertTask(task: Task)
}
