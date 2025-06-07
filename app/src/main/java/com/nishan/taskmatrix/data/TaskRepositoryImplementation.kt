package com.nishan.taskmatrix.data

import com.nishan.taskmatrix.data.dao.TaskDao
import com.nishan.taskmatrix.data.model.asEntity
import com.nishan.taskmatrix.domain.model.Task
import com.nishan.taskmatrix.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class TaskRepositoryImplementation(
    private val topicDao: TaskDao
) : TaskRepository {
    override suspend fun insertTask(task: Task)  {
        topicDao.insert(task.asEntity())
    }
}