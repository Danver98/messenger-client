package com.danver.messenger.data.api

import com.danver.messenger.data.entities.LoginPojo
import com.danver.messenger.data.entities.RegisterPojo
import com.danver.messenger.data.interfaces.IMessage
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface MessengerApi {

    //TODO(): authentication via Oauth2 etc
    @POST("users/login")
    //fun login(@Body email: String, @Body password: String): Call<Void>
    suspend fun login(@Body credentials: LoginPojo): Response<Void>

    @GET("users/{id}/logout")
    suspend fun logoutUser(@Path("id") id: String): Response<Void>

    @POST("users/register")
    suspend fun register(@Body credentials: RegisterPojo): Response<Void>

    @GET("users/{id}/chats")
    suspend fun getChats(@Path("id") id: String): Response<List<Chat>>

    @POST("users/{id}/chats")
    suspend fun createChat(@Path("id") id: String, @Body chat: CreateChatData)

    @GET("users/{userId}/chats/{chatId}/messages")
    suspend fun getMessages(
        @Path("userId") userId: String,
        @Path("chatId") chatId: String,
        @Query("since") date: Date,
        @Query("to") to: Date
    ): Response<List<IMessage>>

    @POST("users/{userId}/chats/{chatId}/messages")
    suspend fun sendMessage(
        @Path("userId") userId: String,
        @Path("chatId") chatId: String,
        @Body message: IMessage
    ): Response<Void>

    @GET("users/search")
    suspend fun searchUsers(@Query("searchString") searchString: String): Response<List<User>>
}