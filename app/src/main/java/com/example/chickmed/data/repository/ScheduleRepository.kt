package com.example.chickmed.data.repository

import com.example.chickmed.data.local.room.bookmark.BookmarkArticleDao
import com.example.chickmed.data.local.room.schedule.ScheduleDao
import com.example.chickmed.data.model.ScheduleModel

class ScheduleRepository (
    private val ScheduleDao: ScheduleDao
) {
    private val scheduleList = mutableListOf<ScheduleModel>()

    suspend fun addSchedule(schedule: ScheduleModel) = ScheduleDao.insertSchedule(schedule)

    fun getScheduleList() = ScheduleDao.getSchedule()

    fun getScheduleById(id: Int) = ScheduleDao.getScheduleById(id)

    suspend fun deleteSchedule(id: Int) = ScheduleDao.deleteSchedule(id)

    suspend fun updateActiveSchedule(id: Int, isActive: Boolean) = ScheduleDao.updateActiveSchedule(id, isActive)

    suspend fun updateSchedule(id: Int, title: String, time: String, day: String, isActive: Boolean) = ScheduleDao.updateSchedule(id, title, time, day, isActive)

    companion object {
        @Volatile
        private var instance: ScheduleRepository? = null
        fun getInstance(
            scheduleDao: ScheduleDao
        ): ScheduleRepository =
            instance ?: synchronized(this) {
                instance ?: ScheduleRepository(scheduleDao)
            }.also { instance = it }
    }
}