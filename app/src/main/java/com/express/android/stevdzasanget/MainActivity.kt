package com.express.android.stevdzasanget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.express.android.stevdzasanget.adapter.MyAdapter
import com.express.android.stevdzasanget.databinding.ActivityMainBinding
import com.express.android.stevdzasanget.model.Post
import com.express.android.stevdzasanget.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    private val myAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.myResponse2.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.d("Response", response.body()?.userId.toString())
                Log.d("Response", response.body()?.id.toString())
                Log.d("Response", response.body()?.title!!)
                binding.textView.setText(response.body()?.title!!)
                Log.d("Response", response.body()?.body!!)
            } else {
                Log.d("Response", response.errorBody().toString())
                binding.textView.setText(response.code().toString())
            }
        })

//        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
//
//        val myPost = Post(2, 2, "Stevdza-San", "Android Developer")
//        viewModel.pushPost(myPost)
//        viewModel.myCustomPosts.observe(this, Observer { response ->
//            if(response.isSuccessful) {
//                response.body()?.let {
//                    myAdapter.setData(it)
//                }
//            } else {
//                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    private fun setupRecyclerView(){
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}