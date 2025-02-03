package com.kh.sbilyhour.composestructure.data.error

import android.content.Context
import com.google.gson.Gson
import com.kh.sbilyhour.composestructure.R
import com.kh.sbilyhour.composestructure.core.utils.NetworkUtils
import com.kh.sbilyhour.composestructure.domain.model.BaseResponse
import retrofit2.HttpException
import javax.inject.Inject

class ApiErrorHandler @Inject constructor(
    private val context: Context,
    private val gson: Gson,
    private val networkUtils: NetworkUtils
) {
    suspend fun <T> handleApiCall(
        apiCall: suspend () -> T
    ): BaseResponse<T> {
        return try {
            if (!networkUtils.isConnected()) {
                return BaseResponse(
                    statusCode = 503,
                    message = context.getString(R.string.no_internet_connection),
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
                message = context.getString(R.string.unexpected_error_occurred, e.message),
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
                context.getString(R.string.unexpected_error_occurred)
            }
        } catch (e: Exception) {
            context.getString(R.string.error_parsing_error_message, e.message)
        }
    }
}


