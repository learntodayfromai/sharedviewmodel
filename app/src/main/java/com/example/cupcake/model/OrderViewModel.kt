package com.example.cupcake.model

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import java.util.Calendar
import java.util.Locale
import java.util.logging.SimpleFormatter

class OrderViewModel : ViewModel(){
    private var _quantity = MutableLiveData<Int>()
    val quantity get() = _quantity

    private val _flavor = MutableLiveData<String>()
    val flavor = _flavor

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> = _price

    val dateOptions = getPickupOptions()

    init{
        resetOrder()
    }

    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

    fun getPickupOptions() : List<String>{
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()

        repeat(4){
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE,1)
        }
        return options
    }
    fun setQuantity(numberOfCupcake:Int){
        _quantity.value=numberOfCupcake
    }

    fun hasNoFlavorSet() : Boolean{
        return _flavor.value.isNullOrEmpty()
    }

    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate
    }
    override fun onCleared() {
        super.onCleared()
    }
}