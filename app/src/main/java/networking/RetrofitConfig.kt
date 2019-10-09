package networking

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    companion object {
        private var BASE_URL: String =
            "http://drinkwell-env.jjap2p8ksx.us-east-2.elasticbeanstalk.com/"
        private lateinit var retrofit: Retrofit
    }

    fun getRetrofitInstance(): Retrofit {

        val builder = GsonBuilder()
        builder.excludeFieldsWithoutExposeAnnotation()
        val gson = builder.create()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }

    fun getRetrofitInstanceWithHost(host: String): Retrofit {
        val builder = GsonBuilder()
        builder.excludeFieldsWithoutExposeAnnotation()
        val gson = builder.create()

        retrofit = Retrofit.Builder()
            .baseUrl("http://${host}/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }
}

