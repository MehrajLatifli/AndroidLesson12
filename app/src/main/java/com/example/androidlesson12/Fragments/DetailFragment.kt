package com.example.androidlesson12.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.androidlesson12.API.ApiUtils
import com.example.androidlesson12.Adapters.TodoAdapter
import com.example.androidlesson12.Models.Todo
import com.example.androidlesson12.Models.TodoListResponse
import com.example.androidlesson12.R
import com.example.androidlesson12.databinding.FragmentDetailBinding
import com.example.androidlesson12.databinding.FragmentHomeBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    val args: DetailFragmentArgs by navArgs()

    private val api = ApiUtils.createApi()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.progressBar.visibility=View.GONE
        binding.textView2.visibility=View.GONE


        val id=args.id

        api.getTodoDetail(id).enqueue(object :Callback<Todo>{
            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {

                if(response.isSuccessful){
                    response.body()?.let {Todo ->

                        binding.textView.text =Todo.toString()
                    }
                }
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {

                binding.progressBar.visibility=View.GONE
                binding.textView2.visibility=View.VISIBLE

                binding.textView2.text= t.message

            }

        })



    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}