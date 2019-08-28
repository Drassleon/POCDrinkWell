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

    private val formRepository = RetrofitConfig().getRetrofitInstance().create(FormRepository::class.java)
    var id : String = ""

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

            host = hostInput.text.toString()
            water = waterInput.text.toString()
            Log.d("Networking","Button Pressed")
            formRepository.getCharacterByName(host,water).enqueue(object: Callback<ResponseDTO>{
                override fun onFailure(call: Call<ResponseDTO>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"Could not connect to endpoint",Toast.LENGTH_SHORT).show()
                    Log.d("NetworkingError","Could not connect to endpoint",t)
                }

                override fun onResponse(call: Call<ResponseDTO>, response: Response<ResponseDTO>) {
                    if(response.body()==null)
                    {
                        Log.d("NetworkingSuccess", "Body is Null")
                        Toast.makeText(this@MainActivity,"Connected to endpoint successfully",Toast.LENGTH_SHORT).show()
                        return
                    }
                    else
                    {
                        Log.d("NetworkingSuccess", response.toString())
                        Toast.makeText(this@MainActivity,"Connected to endpoint successfully",Toast.LENGTH_SHORT).show()

                        var aux = response.body() as ResponseDTO

                        var obj = aux.obj as Form
                        var host = obj.host as String
                        Log.d("NetworkingSuccess",host)

                        return
                    }
                }
            })
        }
    }
}
