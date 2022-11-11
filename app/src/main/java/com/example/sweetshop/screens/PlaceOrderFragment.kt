package com.example.sweetshop.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sweetshop.R
import com.example.sweetshop.database.RoomViewModel
import com.example.sweetshop.databinding.FragmentCatalogBinding
import com.example.sweetshop.databinding.FragmentPlaceOrderBinding
import com.example.sweetshop.repository.Repository
import com.example.sweetshop.viewmodel.MainViewModel
import com.example.sweetshop.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_place_order.*
import kotlinx.coroutines.InternalCoroutinesApi
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.URL
import java.net.URLConnection


@InternalCoroutinesApi
class PlaceOrderFragment : Fragment() {

    private var _binding: FragmentPlaceOrderBinding?=null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository)
    private lateinit var mTaskViewModel: RoomViewModel

    lateinit var name:String
    lateinit var phone:String
    lateinit var address:String
    lateinit var comment: String
    var goods: String =""
    var price=0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPlaceOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)



        takeOrder()

    }

    fun takeOrder(){
        binding.goBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnMakeOrder.setOnClickListener {
            mTaskViewModel = ViewModelProvider(this)[RoomViewModel::class.java]

            with(binding){
                name = editName.text.toString()
                phone = editPhone.text.toString()
                address = editAddress.text.toString()
                comment = editComment.text.toString()

            }

            mTaskViewModel.getAllTasks.observe(viewLifecycleOwner, Observer {
                //var good = ""
                it.forEach { goods += "\nНазвание:${it.name} \nЦена:${it.price} \nКоличество: ${it.presence} \nСумма: ${(it.price.toInt() * it.presence!!.toInt())}\n"  }
                it.forEach { price += (it.price.toInt()*it.presence!!.toInt()) }
                //goods = good

                viewModel.sendOrder(
                    //bot_token = "5587769300:AAGWOLosPA-L6JNxEns1jQqH3ve49DzDQTY",
                    text = "Имя:$name \nНомер телефона: $phone \nАдрес: $address \nКомментарий: $comment " +
                            "\nТовары: \n$goods \nИтоговая сумма: $price",
                    chat_id = "-709573560"
                )
            })



            viewModel.sendOrderResponse.observe(this, Observer { response->
                if(response.isSuccessful)
                {
                    Toast.makeText(context,"Запрос прошел успешно",Toast.LENGTH_LONG).show()

                }else{
                    //Toast.makeText(context,response.body(),Toast.LENGTH_LONG).show()
                    Log.d("Order",response.message().toString())
                    Log.d("Order",response.code().toString())
                    Log.d("Order",response.body().toString())

                }
            })


        }
    }

}