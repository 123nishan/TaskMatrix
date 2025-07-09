package com.nishan.taskmatrix.data

import com.nishan.taskmatrix.data.dao.TaskDao
import com.nishan.taskmatrix.data.model.TaskEntity
import com.nishan.taskmatrix.data.model.asEntity
import com.nishan.taskmatrix.data.model.asExternalModel
import com.nishan.taskmatrix.domain.model.Task
import com.nishan.taskmatrix.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class TaskRepositoryImplementation(
    private val topicDao: TaskDao
) : TaskRepository {
    override suspend fun insertTask(task: Task) {
        topicDao.insert(task.asEntity())
    }

    override fun fetchAllTasks(): Flow<List<Task>> {
        return topicDao.getAll().map {
            it.map(TaskEntity::asExternalModel)
        }
    }
}