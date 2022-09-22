package com.example.moviebuster.network

import retrofit2.Response

data class SimpleResponse<T>(
    val status: Status,
    val data: Response<T>?,
    val exception: Exception?)
{
    // Status types
    sealed class Status {
        object Success:Status()
        object Failure: Status()
    }

    //Defining attributes for the response

    val isFailure:Boolean get()= this.status == Status.Failure // is my status of type failure?
    val isSucessful: Boolean get() = this.data?.isSuccessful == true && !isFailure // is my status success and I have a body
    val body: T get() =this.data!!.body()!! // get response body (success IS SURE)
    val bodyNullable: T? get() = this.data?.body() // get response body (Might be null)

    companion object {

        // This function allows me to construct my Response in case of a successful Network Request
        fun <T> success (data:Response<T>): SimpleResponse<T>{
            return SimpleResponse(
                status = Status.Success,
                data =data,
                exception = null
            )
        }

        // This function allows me to construct my Response in case of a failed Network Request
        fun <T> failure (exception: Exception): SimpleResponse<T>{
            return SimpleResponse(
                status = Status.Failure,
                data =null,
                exception = exception
            )
        }

    }

}
