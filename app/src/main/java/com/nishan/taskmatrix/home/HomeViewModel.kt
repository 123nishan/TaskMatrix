package com.nishan.taskmatrix.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nishan.taskmatrix.domain.mapper.mapToQuadrant
import com.nishan.taskmatrix.domain.mapper.toQuadrantCounts
import com.nishan.taskmatrix.domain.model.MatrixQuadrant
import com.nishan.taskmatrix.domain.repository.TaskRepository
import com.nishan.taskmatrix.util.isToday
import com.nishan.taskmatrix.util.toLocalDate
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class HomeViewModel(
    private val taskRepository: TaskRepository
): ViewModel() {

    val feedState: StateFlow<HomeUiState> = taskRepository.fetchAllTasks()
        .map { it ->

            val quadrantMap: List<MatrixQuadrant> = it.map {
                mapToQuadrant(
                    stars = it.priority.level,
                    dueDate = it.endTime?.toLocalDate(),
                    allDay = it.isAllDay
                )
            }
            val todayTasks = it.filter { task ->
                isToday(task.endTime, task.isAllDay)
            }
            HomeUiState.Success(feed=todayTasks, quadrantCount = quadrantMap.toQuadrantCounts())
        }
        .catch {
            println("Error fetching tasks: ${it.message}")
            HomeUiState.LoadFailed
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState.Loading
        )

}



