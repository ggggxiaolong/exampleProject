package com.mrtan.data.remote

import com.mrtan.data.BuildConfig
import com.mrtan.data.domain.Authorization
import com.mrtan.data.domain.Category
import com.mrtan.data.domain.Feed
import com.mrtan.data.domain.FeedCreate
import com.mrtan.data.domain.FeedUpdater
import com.mrtan.data.domain.Register
import com.mrtan.data.domain.RegisterRP
import com.mrtan.data.domain.User
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author mrtan on 8/30/17.
 * https://github.com/drakeet/rebase-api
 */
interface Api {

  companion object {
    const val BASE_URL: String = BuildConfig.API
  }

  @GET("users/{username}")
  fun user(@Path("username") username: String): Single<User>

  @Headers("Content-Type: application/json")
  @POST("user")
  fun register(@Body user: Register): Single<RegisterRP>

  @GET("authorizations/{owner}")
  fun authorization(
    @Path("owner") username: String, @Query(
        "password"
    ) password: String
  ): Single<Authorization>

  @GET("categories/{owner}")
  fun categories(@Path("owner") username: String): Single<List<Category>>

  @GET("/categories/{owner}/{category}/feeds")
  fun feeds(
    @Path("owner") username: String,
    @Path("category") category: String
  ): Single<List<Feed>>

  @Headers("Content-Type: application/json")
  @POST("categories/{owner}/{category}/feeds")
  fun addFeed(
    @Path("owner") username: String,
    @Body feedCreate: FeedCreate,
    @Path("category") category: String = feedCreate.category()
  ): Single<Feed>

  @Headers("Content-Type: application/json")
  @POST("categories/{owner}/{category}/feeds/{_id}")
  fun updateFeed(
    @Body feedUpdater: FeedUpdater,
    @Path("owner") owner: String = feedUpdater.owner(),
    @Path("category") category: String = feedUpdater.category()
  ): Single<Feed>

  @DELETE("categories/{owner}/{category}/feeds/{id}")
  fun deleteFeed(
    @Path("owner") owner: String,
    @Path("category") category: String,
    @Path("id") id: String
  ): Maybe<String>

}