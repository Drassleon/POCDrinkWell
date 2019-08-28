package Repository

import dto.Form
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FormRepository{
        @GET("water-dispensers")
        fun getCharacterByName(@Query("id")id : String,@Query("water")water : String): Call<Form>
}