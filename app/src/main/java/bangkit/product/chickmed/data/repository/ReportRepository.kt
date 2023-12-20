package bangkit.product.chickmed.data.repository

import androidx.compose.runtime.mutableStateOf
import bangkit.product.chickmed.data.local.preference.UserPreference
import bangkit.product.chickmed.data.model.ReportModel
import bangkit.product.chickmed.data.model.SummaryModel
import bangkit.product.chickmed.data.model.UserModel
import bangkit.product.chickmed.data.remote.response.TemplateResponse
import bangkit.product.chickmed.util.processError
import com.product.submission1.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ReportRepository(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
    private val apiServiceMl: ApiService
) {
    private val dataReports = mutableListOf<ReportModel>()

    private val reports = mutableListOf<ReportModel>()
    private val summary = mutableStateOf<SummaryModel>(SummaryModel(0, 0, 0))

    fun doAnalysis(image: File) =
        flow {
            val user = userPreference.getUser().first()
            val token = user.token
            val user_id = user.id

            val multipartImage = MultipartBody.Part.createFormData(
                "image",
                image.name,
                image.asRequestBody("image/jpeg".toMediaType())
            )

            val articlesFromApi = apiServiceMl.doAnalysis(token = token, image = multipartImage, user_id = user_id)
            if (!articlesFromApi.isSuccessful) {
                val message = articlesFromApi.processError()
                if (message == "Unauthorized") {
                    userPreference.destroyUser()
                }
                emit(
                    TemplateResponse(
                        success = false,
                        message = message,
                        data = 500
                    )
                )
                return@flow
            }

            articlesFromApi.body()?.apply {
                emit(TemplateResponse(success = true, message = "Success", data = this))
            }
        }.catch {
            emit(TemplateResponse(success = false, message = "Connection timeout, Please try again.", data = it))
        }

    fun getReports(page: Int): Flow<TemplateResponse<List<ReportModel>>> =
        flow {
            val token = userPreference.getUser().first().token
            val articlesFromApi = apiService.getReports(page = page, token = token)
            if (!articlesFromApi.isSuccessful) {
                val message = articlesFromApi.processError()
                if (message == "Unauthorized") {
                    userPreference.destroyUser()
                }
                emit(TemplateResponse(success = false, message = message, data = emptyList()))
                return@flow
            }

            articlesFromApi.body()?.apply {
                emit(this)
            }
        }.catch { e ->
            emit(
                TemplateResponse(
                    success = false,
                    message = e.message.toString(),
                    data = emptyList()
                )
            )
        }

    fun getSummary(): Flow<TemplateResponse<SummaryModel>> =
        flow {
            val token = userPreference.getUser().first().token
            val articlesFromApi = apiService.getSummary(token = token)
            if (!articlesFromApi.isSuccessful) {
                val message = articlesFromApi.processError()
                if (message == "Unauthorized") {
                    userPreference.destroyUser()
                }
                emit(
                    TemplateResponse(
                        success = false,
                        message = message,
                        data = SummaryModel(0, 0, 0)
                    )
                )
                return@flow
            }

            articlesFromApi.body()?.apply {
                emit(this)
            }
        }.catch { e ->
            emit(
                TemplateResponse(
                    success = false,
                    message = e.message.toString(),
                    data = SummaryModel(0, 0, 0)
                )
            )
        }

    fun getReportById(id: Int): Flow<TemplateResponse<ReportModel>> =
        flow {
            val token = userPreference.getUser().first().token
            val articlesFromApi = apiService.getReportById(id = id, token = token)
            if (!articlesFromApi.isSuccessful) {
                val message = articlesFromApi.processError()
                if (message == "Unauthorized") {
                    userPreference.destroyUser()
                }
                emit(
                    TemplateResponse(
                        success = false,
                        message = message,
                        data = ReportModel(0, "", "", "", emptyList())
                    )
                )
                return@flow
            }

            articlesFromApi.body()?.apply {
                emit(this)
            }
        }.catch { e ->
            emit(
                TemplateResponse(
                    success = false,
                    message = e.message.toString(),
                    data = ReportModel(0, "", "", "", emptyList())
                )
            )
        }

    companion object {
        @Volatile
        private var instance: ReportRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService,
            apiServiceMl: ApiService
        ): ReportRepository =
            instance ?: synchronized(this) {
                instance ?: ReportRepository(userPreference, apiService, apiServiceMl)
            }.also { instance = it }
    }
}