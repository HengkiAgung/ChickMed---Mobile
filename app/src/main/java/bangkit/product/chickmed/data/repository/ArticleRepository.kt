package bangkit.product.chickmed.data.repository

import bangkit.product.chickmed.data.local.preference.UserPreference
import bangkit.product.chickmed.data.model.ArticleModel
import bangkit.product.chickmed.data.remote.response.TemplateResponse
import bangkit.product.chickmed.util.processError
import com.product.submission1.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ArticleRepository (
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {
//    private val dataArticles: MutableStateFlow<TemplateResponse<List<ArticleModel>>> = MutableStateFlow(TemplateResponse(success = false, message = "", data = emptyList()))
//    private val articles = mutableListOf<ArticleModel>()
    fun getArticles(page: Int): Flow<TemplateResponse<List<ArticleModel>>> =
        flow {
            val articlesFromApi = apiService.getArticles(page = page)
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
            emit(TemplateResponse(success = false, message = e.message.toString(), data = emptyList()))
        }


    fun getArticleById(id: Int): Flow<TemplateResponse<ArticleModel>> =
        flow {
            val articlesFromApi = apiService.getArticleById(id = id)
            if (!articlesFromApi.isSuccessful) {
                val message = articlesFromApi.processError()
                if (message == "Unauthorized") {
                    userPreference.destroyUser()
                }
                emit(TemplateResponse(success = false, message = message, data = ArticleModel(0, "", "", "", "")))
                return@flow
            }

            articlesFromApi.body()?.apply {
                emit(this)
            }
        }.catch { e ->
            emit(TemplateResponse(success = false, message = e.message.toString(), data = ArticleModel(0, "", "", "", "")))
        }
//        return flowOf(articles.first {
//            getArticleById
//            it.id == id
//        })

    companion object {
        @Volatile
        private var instance: ArticleRepository? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference,
        ): ArticleRepository =
            instance ?: synchronized(this) {
                instance ?: ArticleRepository(userPreference, apiService)
            }.also { instance = it }
    }
}