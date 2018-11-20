package com.mrtan.data

/**
 * @author mrtan on 8/30/17.
 */
import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.serializationConverterFactory
import com.mrtan.data.local.AppDatabase
import com.mrtan.data.remote.Api
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.JSON
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author mrtan on 17-3-14.
 */
@Module
class NetModule {

  @Provides
  @Singleton internal fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(BuildConfig.OKHTTP_LOG_LEVEL)
        )
        .build()
  }

  @Provides
  @Singleton internal fun provideApi(client: OkHttpClient): Api {
    return Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .client(client)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(
            serializationConverterFactory(MediaType.get("application/json"), JSON.nonstrict)
        )
        .validateEagerly(BuildConfig.DEBUG)
        .build()
        .create(Api::class.java)
  }

  @Provides
  @Singleton internal fun database(context: Application): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
        .build()
  }
}