package com.naveen.retrofitdemoone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.naveen.retrofitdemoone.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retService : AlbumService
   private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
          retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)
        getRequestWithQueryParameters()
//        getRequestWithPathParameters()

    }


    private fun getRequestWithQueryParameters(){
        val responseLiveData: LiveData<Response<Album>> = liveData {
            val response = retService.getSortedAlbums(3)
            emit(response)
        }

        responseLiveData.observe(this, Observer {
            val albumsList:MutableListIterator<AlbumItem>? = it.body()?.listIterator()
            if(albumsList!=null){
                while (albumsList.hasNext()){
                    val albumItem = albumsList.next()
                    Log.i("MYTAG", albumItem.title)
                    val result = " "+"Album Title : ${albumItem.title}"+"\n"
                    " "+"Album id : ${albumItem.id}"+"\n\n"
                    " "+"User Id : ${albumItem.userId}"+"\n\n\n"
                    binding.textView.append(result)
                }
            }
        })
    }

    private fun getRequestWithPathParameters(){
        // path parameter example
//        val pathResponse : LiveData<Response<AlbumItem>> = liveData {
//            val response = retService.getAlbum(3)
//            emit(response)
//        }
//        pathResponse.observe(this, Observer {
//            val title:String? = it.body()?.title
//            Toast.makeText(applicationContext,title,Toast.LENGTH_LONG).show()
//        })
    }

}