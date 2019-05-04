package com.rodak.cocktails.data

import android.util.Log
import com.rodak.cocktails.data.model.Drink
import com.rodak.cocktails.data.remote.CocktailApi
import retrofit2.Response
import java.io.IOException

class CocktailsRepository(private val api: CocktailApi) {

    suspend fun getDrinks(name: String): MutableList<Drink>? {

        val cocktailsResponse = safeApiCall(
            call = { api.getCocktailByName(name).await() },
            errorMessage = "Error fetching cocktails"
        )

        return cocktailsResponse?.drinks?.toMutableList()
    }

    private suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result: Result<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when (result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                Log.d("ApiCall", "$errorMessage & Exception - ${result.exception}")
            }
        }

        return data
    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Result<T> {
        val response = call.invoke()
        if (response.isSuccessful) return response.body()?.let { Result.Success(it) } ?: Result.Error(IOException("Call was successful but body was null"))

        return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}