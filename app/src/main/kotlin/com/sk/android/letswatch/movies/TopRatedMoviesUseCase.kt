package com.sk.android.letswatch.movies

import com.sk.android.letswatch.data.source.MovieRepository
import com.sk.android.letswatch.data.source.remote.MovieWebServiceRequest
import com.sk.android.letswatch.data.source.remote.MovieWebServiceResponse
import com.sk.android.letswatch.vo.Resource
import javax.inject.Inject

class TopRatedMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    UseCase<MovieWebServiceRequest, MovieWebServiceResponse>() {

    override suspend fun run(request: MovieWebServiceRequest): Resource<MovieWebServiceResponse> {
        return movieRepository.getTopRatedMovies(request.page)
    }
}