package Repository

import dto.ResponseDTO
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FormRepository{
    @GET("water-dispensers")
    fun getCharacterByName(@Query("id") id: String, @Query("water") water: String): Call<ResponseDTO>

    @POST("dispension")
    fun postDispenser(
        @Query("nombre") name: String, @Query("cantidadAgua") waterAmount: String, @Query(
            "time"
        ) time: String
    ): Call<ResponseBody>
}