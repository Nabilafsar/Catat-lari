package com.upn.catatlari.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.upn.catatlari.database.RunDatabase
import com.upn.catatlari.model.RunEntity
import kotlinx.coroutines.launch

class RunViewModel(application: Application) : AndroidViewModel(application) {

    private val runDao = RunDatabase.getDatabase(application).runDao()

    val runHistory: LiveData<List<RunEntity>> = runDao.getAllRuns()

    // CREATE
    fun addRun(run: RunEntity) {
        viewModelScope.launch {
            runDao.insertRun(run)
        }
    }

    // READ

    // UPDATE
    fun updateRun(run: RunEntity) {
        viewModelScope.launch { runDao.updateRun(run) }
    }

    // DELETE
    fun deleteRun(run: RunEntity) {
        viewModelScope.launch { runDao.deleteRun(run) }
    }
}
