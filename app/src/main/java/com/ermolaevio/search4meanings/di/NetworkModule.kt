package com.ermolaevio.search4meanings.di

import com.ermolaevio.search4meanings.BuildConfig
import com.ermolaevio.search4meanings.data.remote.Api
import com.ermolaevio.search4meanings.data.remote.NetworkConstants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {



    @Provides
    @Singleton
    fun provideSovcomRetrofitApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @Provides
    @Singleton
    fun provideSovcomRetrofitBuilder(httpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setLenient().create()

        return Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpManager(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        // TODO(FIX)
        builder.connectTimeout(NetworkConstants.CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        builder.readTimeout(NetworkConstants.READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor) // только в дебаге должно быть
        }

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }
}