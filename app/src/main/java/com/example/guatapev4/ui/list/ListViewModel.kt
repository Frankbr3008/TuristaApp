package com.example.guatapev4.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guatapev4.data.GuatapeRepository
import com.example.guatapev4.model.Guatape
import com.example.guatapev4.model.GuatapeItem
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStream

class ListViewModel: ViewModel() {

    private var guatapeLoad : MutableLiveData<ArrayList<GuatapeItem>> = MutableLiveData()
    val onguatapeLoaded: LiveData<ArrayList<GuatapeItem>> = guatapeLoad

    private val repository = GuatapeRepository()

    fun getguatapeFromServer(){
        GlobalScope.launch(Dispatchers.IO) {
            guatapeLoad.postValue(repository.getGuatape())
        }
    }
}