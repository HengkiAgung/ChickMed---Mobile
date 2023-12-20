package bangkit.product.chickmed.util

import android.content.Context
import android.net.Uri
import bangkit.product.chickmed.data.model.ErrorModel
import com.google.gson.Gson
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

fun Response<*>.processError(): String {
    when (this.code()) {
        400 -> {
            return "Bad request"
        }

        401 -> {
            return "Unauthorized"
        }

        403 -> {
            return "Forbidden"
        }

        404 -> {
            return "Not found"
        }

        500 -> {
            return "Internal server error"
        }
        else -> {
            val errorBody = this.errorBody()?.string()
            return if (!errorBody.isNullOrBlank()) {
                val gson = Gson()
                val errorResponse = gson.fromJson(errorBody, ErrorModel::class.java)
                try {
                    errorResponse.errors.map {
                        (it.value as List<*>)[0]
                    }.joinToString("\n")
                } catch (e: Exception) {
                    errorResponse.message
                }
            } else {
                "Something went wrong"
            }
        }

    }
}

fun createCustomTempFile(context: Context): File {
    val filesDir = context.externalCacheDir
    return File.createTempFile("profile", ".jpg", filesDir)
}

fun Uri.uriToFile(context: Context): File {
    val myFile = createCustomTempFile(context)
    val inputStream = context.contentResolver.openInputStream(this) as InputStream
    val outputStream = FileOutputStream(myFile)
    val buffer = ByteArray(1024)
    var length: Int
    while (inputStream.read(buffer).also { length = it } > 0) outputStream.write(buffer, 0, length)
    outputStream.close()
    inputStream.close()

    return myFile
}