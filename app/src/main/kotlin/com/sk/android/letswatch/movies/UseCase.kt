package com.sk.android.letswatch.movies

import com.sk.android.letswatch.vo.Resource
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

abstract class UseCase<Request, Response> {

    abstract suspend fun run(params: Request): Resource<Response>

    operator fun invoke(request: Request, onResult: (Resource<Response>) -> Unit) {
        val job = async(CommonPool) {
            run(request)
        }
        launch(UI) {
            onResult(job.await())
        }
    }

}