package ee.artjom.requesttracker.ui.screens


import android.content.Context
import android.content.Intent
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.IconButton
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ee.artjom.requesttracker.data.RequestEntity
import ee.artjom.requesttracker.data.RequestStatus
import ee.artjom.requesttracker.viewmodel.RequestsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    vm: RequestsViewModel,
    onAddClick: () -> Unit
) {
    val filter by vm.filter.collectAsState()
    val items by vm.items.collectAsStateWithLifecycle(initialValue = emptyList())
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Request Tracker") },
                actions = {
                    IconButton(onClick = {
                        shareCsv(context, items)
                    }) {
                        Icon(Icons.Default.Share, contentDescription = "Export CSV")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { vm.setFilter(null) }) { Text("ALL") }
                Button(onClick = { vm.setFilter(RequestStatus.NEW) }) { Text("NEW") }
                Button(onClick = { vm.setFilter(RequestStatus.IN_PROGRESS) }) { Text("IN PROGRESS") }
                Button(onClick = { vm.setFilter(RequestStatus.DONE) }) { Text("DONE") }
            }

            Spacer(modifier = Modifier.padding(top = 4.dp))

            if (items.isEmpty()) {
                Text(
                    text = "No requests yet. Tap + to create one.",
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(items, key = { it.id }) { item ->
                        RequestRow(item = item, onNextStatus = {
                            vm.moveStatus(item.id, nextStatus(item.status))
                        })
                    }
                }
            }
        }
    }
}

@Composable
private fun RequestRow(
    item: RequestEntity,
    onNextStatus: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.title, style = MaterialTheme.typography.titleMedium)
            if (item.description.isNotBlank()) {
                Text(text = item.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
        Button(onClick = onNextStatus) {
            Text(item.status.name)
        }
    }
}

private fun nextStatus(current: RequestStatus): RequestStatus =
    when (current) {
        RequestStatus.NEW -> RequestStatus.IN_PROGRESS
        RequestStatus.IN_PROGRESS -> RequestStatus.DONE
        RequestStatus.DONE -> RequestStatus.NEW
    }
private fun shareCsv(context: Context, items: List<RequestEntity>) {
    val csv = buildCsv(items)

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/csv"
        putExtra(Intent.EXTRA_SUBJECT, "Request Tracker Export")
        putExtra(Intent.EXTRA_TEXT, csv)
    }

    context.startActivity(Intent.createChooser(intent, "Share CSV"))
}

private fun buildCsv(items: List<RequestEntity>): String {
    fun esc(s: String): String {
        val v = s.replace("\"", "\"\"")
        return "\"$v\""
    }

    val header = "id,title,description,status,createdAt"
    val rows = items.joinToString(separator = "\n") { r ->
        listOf(
            r.id.toString(),
            esc(r.title),
            esc(r.description),
            r.status.name,
            r.createdAt.toString()
        ).joinToString(",")
    }

    return header + "\n" + rows
}