package pe.edu.upc.pocdrinkwell

import Repository.FormRepository
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dto.Form
import dto.ResponseDTO
import networking.RetrofitConfig
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var submitButton : Button
    lateinit var waterInput : EditText
    lateinit var hostInput : EditText
    var hostGlob : String = ""
    var userName : String = "UserName"

    private val formRepository = RetrofitConfig().getRetrofitInstance().create(FormRepository::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitButton = findViewById(R.id.submitButton)
        waterInput = findViewById(R.id.waterInput)
        hostInput = findViewById(R.id.hostInput)
        pressButton()
    }
    private fun pressButton(){
        submitButton.setOnClickListener {

            var host : String
            var water : String

            host = "AndroidAP"
            water = waterInput.text.toString()
            userName = hostInput.text.toString()
            Log.d("Networking","Button Pressed")
            getHost(host,water)

        }
    }
    private fun getHost(host: String, water: String){
        formRepository.getCharacterByName(host,water).enqueue(object: Callback<ResponseDTO>{
            override fun onFailure(call: Call<ResponseDTO>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Could not connect to endpoint",Toast.LENGTH_SHORT).show()
                Log.d("Networking","Could not connect to endpoint",t)
            }

            override fun onResponse(call: Call<ResponseDTO>, response: Response<ResponseDTO>) {
                if(response.body()==null)
                {
                    Log.d("NetworkingGet", "Body is Null")
                    Toast.makeText(this@MainActivity,"Connected to endpoint successfully",Toast.LENGTH_SHORT).show()
                    return
                }
                else
                {
                    Log.d("NetworkingGet", response.toString())
                    Toast.makeText(this@MainActivity,"Connected to endpoint successfully",Toast.LENGTH_SHORT).show()

                    var aux = response.body() as ResponseDTO

                    var obj = aux.obj as Form
                    hostGlob = obj.host as String
                    Log.d("NetworkingGet", host)
                    var time: String = "Null"
                    if (aux.time != null) {
                        time = aux.time as String
                    }


                    postDispenserEvent(time)
                    return
                }
            }
        })
    }

    private fun postDispenserEvent(time: String) {
        val hostRepository = RetrofitConfig().getRetrofitInstanceWithHost(hostGlob)
            .create(FormRepository::class.java)

        hostRepository.postDispenser(
            name = userName,
            waterAmount = waterInput.text.toString(),
            time = time
        ).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("NetworkingPost", "Post Successful")
                Log.d("NetworkingPost", response.raw().toString())
                Toast.makeText(this@MainActivity,"Post Successful",Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Post failed!",Toast.LENGTH_SHORT).show()
                Log.d("NetworkingPost", "URL called ${call.request()}")
                Log.d("NetworkingPost", "Post Failed!", t)
            }
        })
    }
}
