package com.mrtan.data

/**
 * @author mrtan on 8/30/17.
 */
import android.arch.persistence.room.Room
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mrtan.data.local.AppDatabase
import com.mrtan.data.remote.Api
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author mrtan on 17-3-14.
 */
@Module class NetModule {
  @Provides @Singleton internal fun ProvideGson(): Gson {
    return GsonBuilder()
//        .registerTypeAdapterFactory(GsonAdaptersDomain())
        .create()
  }

  @Provides @Singleton internal fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor().setLevel(BuildConfig.OKHTTP_LOG_LEVEL))
        .build()
  }

  @Provides @Singleton internal fun provideApi(client: OkHttpClient, gson: Gson): Api {
    return Retrofit.Builder().baseUrl(Api.BASE_URL)
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .validateEagerly(BuildConfig.DEBUG)
        .build()
        .create(Api::class.java)
  }

  @Provides
  @Singleton internal fun database(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
  }

}