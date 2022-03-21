package com.example.basicsengapp

//import javax.security.auth.callback.Callback

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.basicsengapp.api.UserAPIService
import com.example.basicsengapp.databinding.FragmentFirstBinding
import com.example.basicsengapp.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val userAPIService= UserAPIService.create()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFirst.setOnClickListener{

           val editText = binding.inputTextId.editableText
           val user = userAPIService.getUser(editText.toString());


            // val user = userAPIService.getUser("1");
            Log.i("FirstFragment","buttonFirst")

            user.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val body =response.body()
                    body?.let {
                        Log.i("FirstFragment Name :",it.name)

                        binding.textviewName.text = it.name
                        binding.textviewEmail.text = it.email
                        binding.textviewUsername.text=it.username;
                        binding.textviewId.text= it.id.toString()

                    }

                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.i("FirstFragment Name :",t.message!!)
                }


            })

        }





     /*   binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}