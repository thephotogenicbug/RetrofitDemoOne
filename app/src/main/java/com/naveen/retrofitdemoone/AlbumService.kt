package com.naveen.retrofitdemoone

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {
    @GET("/albums")
    suspend fun getAlbums() : Response<Album>

    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId")userId:Int) : Response<Album>
}