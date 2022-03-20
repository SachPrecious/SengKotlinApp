package com.example.basicsengapp.api
import com.example.basicsengapp.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

interface  UsersService {
    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{id}")
    fun getUser(@Path("id") id : String):Call<User>

}



class RetrofitConfigurations {
  companion object{
      val API_URL ="https://jsonplaceholder.typicode.com"
      val retrofit = Retrofit.Builder()
          .baseUrl(API_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build()

      val UserService = retrofit.create(UsersService::class.java)

  }
}