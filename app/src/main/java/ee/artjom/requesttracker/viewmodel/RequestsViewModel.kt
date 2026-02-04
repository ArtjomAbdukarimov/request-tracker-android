package ee.artjom.requesttracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ee.artjom.requesttracker.data.AppDatabase
import ee.artjom.requesttracker.data.RequestEntity
import ee.artjom.requesttracker.data.RequestStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class RequestsViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = AppDatabase.get(app).requestDao()

    private val _filter = MutableStateFlow<RequestStatus?>(null)
    val filter: StateFlow<RequestStatus?> = _filter

    val items = _filter.flatMapLatest { status ->
        if (status == null) dao.observeAll() else dao.observeByStatus(status)
    }

    fun setFilter(status: RequestStatus?) {
        _filter.value = status
    }

    fun addRequest(title: String, description: String) {
        val t = title.trim()
        val d = description.trim()
        if (t.isBlank()) return

        viewModelScope.launch {
            dao.insert(
                RequestEntity(
                    title = t,
                    description = d
                )
            )
        }
    }

    fun moveStatus(id: Long, newStatus: RequestStatus) {
        viewModelScope.launch {
            dao.updateStatus(id, newStatus)
        }
    }
}