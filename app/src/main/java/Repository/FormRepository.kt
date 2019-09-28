package Repository

import dto.ResponseDTO
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface FormRepository{
    @PUT("water-dispensers/{id}")
    fun getCharacterByName(@Path("id") id: String, @Query("water") water: String): Call<ResponseDTO>

    @POST("dispension")
    fun postDispenser(
        @Query("nombre") name: String, @Query("cantidadAgua") waterAmount: String, @Query(
            "time"
        ) time: String
    ): Call<ResponseBody>

    @POST("dispension")
    fun testDispenser(
        @Query("nombre") name: String, @Query("cantidadAgua") waterAmount: String, @Query(
            "time"
        ) time: String
    ): Call<ResponseBody>
}