package me.matsumo.koto.core.datasource.helper

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import okio.Path.Companion.toPath
import java.io.File

class PreferenceHelperImpl(
    private val ioDispatcher: CoroutineDispatcher,
) : PreferenceHelper {

    private val baseDirectory: File by lazy {
        val home = System.getProperty("user.home").orEmpty()
        File(home.ifEmpty { "." }, ".koto/datastore").apply {
            if (!exists()) mkdirs()
        }
    }

    override fun create(name: String): DataStore<Preferences> {
        val file = baseDirectory.resolve("$name.preferences_pb")

        return PreferenceDataStoreFactory.createWithPath(
            corruptionHandler = null,
            migrations = emptyList(),
            scope = CoroutineScope(ioDispatcher),
            produceFile = { file.absolutePath.toPath() },
        )
    }

    override fun delete(name: String) {
        baseDirectory
            .resolve("$name.preferences_pb")
            .takeIf { it.exists() }
            ?.delete()
    }
}
