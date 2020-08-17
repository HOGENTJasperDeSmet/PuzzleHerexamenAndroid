package com.example.puzzleherexamenandroid.puzzle
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.mlkit.common.MlKitException
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.vision.digitalink.*
import java.util.*

/** Class to manage model downloading and selection.  */
class ModelManager {
    private var model: DigitalInkRecognitionModel? = null
    var recognizer: DigitalInkRecognizer? = null
    private val remoteModelManager = RemoteModelManager.getInstance()
    fun setModel(): String {
        // Clear the old model and recognizer.
        model = null
        recognizer?.close()
        recognizer = null
        val languageTag = "En_in"
        // Try to parse the languageTag and get a model from it.
        val modelIdentifier: DigitalInkRecognitionModelIdentifier?
        modelIdentifier = try {
            DigitalInkRecognitionModelIdentifier.fromLanguageTag(languageTag)
        } catch (e: MlKitException) {
            Log.e(
                TAG,
                "Failed to parse language '$languageTag'"
            )
            return ""
        } ?: return "No model for language: $languageTag"

        // Initialize the model and recognizer.
        model = DigitalInkRecognitionModel.builder(modelIdentifier).build()
        recognizer = DigitalInkRecognition.getClient(
            DigitalInkRecognizerOptions.builder(model!!).build()
        )
        Log.i(
            TAG, "Model set for language '$languageTag' ('$modelIdentifier.languageTag')."
        )
        return "Model set for language: $languageTag"
    }

    fun checkIsModelDownloaded(): Task<Boolean?> {
        return remoteModelManager.isModelDownloaded(model!!)
    }

    val downloadedModelLanguages: Task<Set<String>>
        get() = remoteModelManager
            .getDownloadedModels(DigitalInkRecognitionModel::class.java)
            .onSuccessTask { remoteModels: Set<DigitalInkRecognitionModel>? ->
                val result: MutableSet<String> = HashSet()
                for (model in remoteModels!!) {
                    result.add(model.modelIdentifier.languageTag)
                }
                Log.i(
                    TAG,
                    "Downloaded models for languages:$result"
                )
                Tasks.forResult<Set<String>>(result.toSet())
            }

    fun download(): Task<String?> {
        return if (model == null) {
            Tasks.forResult("Model not selected.")
        } else remoteModelManager
            .download(model!!, DownloadConditions.Builder().build())
            .onSuccessTask {
                Log.i(
                    TAG,
                    "Model download succeeded."
                )
                Tasks.forResult("Downloaded model successfully")
            }
            .addOnFailureListener { e: Exception ->
                Log.e(
                    TAG,
                    "Error while downloading the model: $e"
                )
            }
    }

    companion object {
        private const val TAG = "MLKD.ModelManager"
    }
}