package com.rodak.cocktails.data.remote

import com.rodak.cocktails.data.model.Cocktails
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CocktailApi {

    @GET("filter.php?c=Cocktail")
    fun getCocktail(): Deferred<Response<Cocktails>>
}