package dev.codecraft.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import dev.codecraft.retrofittest.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var url: String = "https://jsonplaceholder.typicode.com/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getMyData()

    }

    private fun getMyData() {
        //for retrofit
        var retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(ApiInterface::class.java)
        val retrofitData=retrofitBuilder.getData()
        retrofitData.enqueue(object : retrofit2.Callback<List<MyData>?> {
            override fun onFailure(call: Call<List<MyData>?>, t: Throwable) {
                println("error")
            }



            override fun onResponse(call: Call<List<MyData>?>, response: Response<List<MyDataItem>?>) {
                val responseBody = response.body()!!
                val mystringbuilder = StringBuilder()
                for (i in responseBody) {
                    mystringbuilder.append(i.id)
                    mystringbuilder.append(i.title)
                    mystringbuilder.append(i.body)
                }
                binding.txtHello.text = mystringbuilder.toString()
                Log.d("mylog", mystringbuilder.toString())
            }
        })



    }
}