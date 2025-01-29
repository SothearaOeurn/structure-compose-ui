package com.kh.sbilyhour.composestructure.utils
import com.google.gson.Gson
import com.kh.sbilyhour.composestructure.domain.model.BaseResponse
import retrofit2.HttpException
import javax.inject.Inject

class ApiErrorHandler @Inject constructor(private val gson: Gson, private val networkUtils: NetworkUtils) {
    suspend fun <T> handleApiCall(
        apiCall: suspend () -> T
    ): BaseResponse<T> {
        return try {
            if (!networkUtils.isConnected()) {
                return BaseResponse(
                    statusCode = 503,
                    message = "No internet connection.",
                    data = null
                )
            }

            val data = apiCall()
            BaseResponse(
                statusCode = 200,
                message = "Success",
                data = data
            )
        } catch (exception: HttpException) {
            val errorMessage = parseErrorMessage(exception)
            BaseResponse(
                statusCode = exception.code(),
                message = errorMessage,
                data = null
            )
        } catch (e: Exception) {
            BaseResponse(
                statusCode = 500,
                message = "Unexpected error occurred: ${e.message}",
                data = null
            )
        }
    }

    private fun parseErrorMessage(exception: HttpException): String {
        return try {
            val errorBody = exception.response()?.errorBody()?.string()
            if (!errorBody.isNullOrEmpty()) {
                val errorResponse = gson.fromJson(errorBody, BaseResponse::class.java)

                errorResponse.message
            } else {
                "Unknown error occurred."
            }
        } catch (e: Exception) {
            "Error parsing error message: ${e.message}"
        }
    }
}


