package com.example.basicsengapp

import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.basicsengapp.api.UserAPIService
import com.example.basicsengapp.databinding.FragmentFirstBinding
import retrofit2.Response
import javax.security.auth.callback.Callback
import com.example.basicsengapp.model.User

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

            val editText = binding.editText.editableText
            val user = userAPIService.getUser(editText.toString());

           // val user = userAPIService.getUser("1");
            Log.i("FirstFragment","buttonFirst")

            user.enqueue(object : retrofit2.Callback<User> {
                override fun onResponse(call: retrofit2.Call<User>, response: Response<User>) {
                    val body =response.body()
                    body?.let {
                        Log.i("FirstFragment Name :",it.name)
                        binding.textviewUserID.text="User ID "+it.id
                        binding.textviewUserName.text="Name:  "+it.username
                        binding.textviewUserEmail.text="Email:  "+it.email
                        //binding.textviewUserWebsite.text="Website:  "+it.website

                    }

                }

                override fun onFailure(call: retrofit2.Call<User>, t: Throwable) {
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