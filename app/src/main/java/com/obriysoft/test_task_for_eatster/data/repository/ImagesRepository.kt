package com.example.testtaskfore.data.repository

import javax.inject.Inject

const val TAG = "MovieRepository"


class MoviesRepository @Inject constructor(
    /* private val network: RemoteDataSource,
     private val database: MovieLocalDataSource*/
) {

    /*   val movies: Flow<List<SlideModel>> =
           database.getAllMovies()
               .map { it.asDomainModel() }

       suspend fun refreshMovies() {
           withContext(Dispatchers.IO) {
               when (val response = network.getMovies()) {
                   is ApiResult.Success -> {
                       // Retrieve movies from network
                       val moviesList = response.data.results
                       // Update database
                       database.deleteAll()
                       database.insertAll(moviesList.fromNetworkToDatabaseModel())
                   }

                   is ApiResult.Error -> Log.e(TAG, "${response.code} ${response.message}")
                   is ApiResult.Exception -> Log.e(TAG, "${response.e.cause} ${response.e.message}")
               }
           }
       }*/
}