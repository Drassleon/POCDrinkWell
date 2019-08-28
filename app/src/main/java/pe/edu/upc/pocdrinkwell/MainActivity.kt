package pe.edu.upc.pocdrinkwell

import Repository.FormRepository
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dto.Form
import networking.RetrofitConfig
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
    fun pressButton(){
        submitButton.setOnClickListener {
            var host : String
            var water : String

            host = hostInput.text.toString()
            water = waterInput.text.toString()

            formRepository.getCharacterByName(host,water).enqueue(object: Callback<Form>{
                override fun onFailure(call: Call<Form>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"Could not connect to endpoint",Toast.LENGTH_SHORT).show()
                    Log.d("NetworkingError","Could not connect to endpoint",t)
                }

                override fun onResponse(call: Call<Form>, response: Response<Form>) {
                    if(response.body()==null)
                    {
                        Log.d("NetworkingSuccess", response.body().toString())
                        return
                    }
                }
            })
        }
    }
}
