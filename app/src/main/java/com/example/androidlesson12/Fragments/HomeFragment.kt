package com.example.androidlesson12.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ActionMode.Callback
import androidx.navigation.fragment.findNavController
import androidx.transition.Visibility
import com.example.androidlesson12.API.ApiUtils
import com.example.androidlesson12.Adapters.TodoAdapter
import com.example.androidlesson12.Models.Todo
import com.example.androidlesson12.Models.TodoListResponse
import com.example.androidlesson12.R
import com.example.androidlesson12.databinding.FragmentHomeBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val todoAdapter=TodoAdapter()

    private val api = ApiUtils.createApi()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visibility=View.GONE
        binding.textView2.visibility=View.GONE

        binding.rvHome.adapter=todoAdapter

        Picasso.get().load("https://lh3.googleusercontent.com/COxitqgJr1sJnIDe8-jiKhxDx1FrYbtRHKJ9z_hELisAlapwE9LUPh6fcXIfb5vwpbMl4xl9H9TRFPc5NOO8Sb3VSgIBrfRYvW6cUA").into(binding.imageView);

        getTodo()

        todoAdapter.onClickItem={item ->

            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(item))
        }


    }


    private  fun getTodo()
    {
        binding.progressBar.visibility=View.VISIBLE

        api.getTodo().enqueue(object : retrofit2.Callback<TodoListResponse> {

            override fun onResponse(call: Call<TodoListResponse>, response: Response<TodoListResponse>) {

                //  response.code()==200

                if (response.isSuccessful) {


                    response.body()?.let { item ->

                        item.todos?.let {TodoList ->

                            binding.progressBar.visibility=View.GONE

                            todoAdapter.updateList(TodoList.toCollection(ArrayList<Todo>()))
                        }

                    }
                    Log.i("todo", response.body().toString())



                } else {

                    Log.e("todo", "Response unsuccessful: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<TodoListResponse>, t: Throwable) {

                binding.progressBar.visibility=View.GONE
                binding.rvHome.visibility=View.GONE
                binding.textView2.visibility=View.VISIBLE


                binding.textView2.text= t.message


                Log.e("todo", "Failed to get todo: ${t.message}")
            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}