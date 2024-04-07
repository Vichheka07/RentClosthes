package com.example.rentclothes.Service
import ApiItem
import com.example.rentclothes.model.LoginRequest
import com.example.rentclothes.model.RegisterRequest
import com.example.rentclothes.model.ResgisterResponse
import com.example.rentclothes.model.SearchRequestBody
import com.example.rentclothes.model.SiginScreen
import com.example.rentclothes.model.postResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @GET("api/posts")
    fun loadHomeScreenList(): Call<ApiItem>

    @POST("api/login")
    fun loadSignInScreen(@Body loginRequest: LoginRequest): Call<SiginScreen>

    @POST("api/register")
    fun loadSignUpScreen(@Body regiserRequest: RegisterRequest): Call<ResgisterResponse>

    @GET("api/{category}")
    fun loadCategoryScreen(@Path("category") category: String): Call<ApiItem>

    @POST("api/posts/find")
    fun loadSearchScreen(@Body title: SearchRequestBody): Call<ApiItem>

    @Multipart
    @POST("api/posts")
    fun loadPostScreen(
        @Part images: List<MultipartBody.Part>,
        @Part("title") title: RequestBody,
        @Part("describe") describe: RequestBody,
        @Part("price") price: RequestBody,
        @Part("orgprice") orgprice: RequestBody,
        @Part("day") day: RequestBody,
        @Part("category") category: RequestBody,
        @Part("codition") condition: RequestBody,
        @Part("size") size: RequestBody,
        @Part("delivery") delivery: RequestBody
    ): Call<postResponse>


}