package com.sm.dongelek.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber

class MainViewModel: ViewModel() {

    private  val db = Firebase.firestore

    private val _gags = MutableLiveData<List<Gag>>()
    val gags: LiveData<List<Gag>> = _gags

    private val _loadingGags = MutableLiveData(false)
    val loadingGags: LiveData<Boolean> = _loadingGags

    fun load(){
        _loadingGags.value = true
        db.collection("Gags").get().addOnSuccessListener {
            val list = mutableListOf<Gag>()
            it.documents.forEach {
//                Timber.d("Success: %s", it.data)
                if ( _gags.value?.contains(Gag(it.getString("text"), it.getString("image"),
                                it.getBoolean("isFav") != true, 1)) == true){
                    list.add(Gag(it.getString("text"), it.getString("image"),
                            it.getBoolean("isFav") != true, 1))
                } else  {
                    if ( _gags.value?.contains(Gag(it.getString("text"), it.getString("image"),
                                    it.getBoolean("isFav") == true, 0)) == true){
                        list.add(Gag(it.getString("text"), it.getString("image"),
                                it.getBoolean("isFav") == true, 0))
                    } else {
                        list.add(Gag(it.getString("text"), it.getString("image"),
                                it.getBoolean("isFav") == true, 0))
                    }
                }

            }
            _gags.value = list
        }.addOnFailureListener {
            Timber.d(it, "Failure")
        }.addOnCompleteListener {
            _loadingGags.value = false
        }
    }
/*
    fun load(){
        _loadingGags.value = true
        db.collection("Gags").get().addOnSuccessListener {
            val list = mutableListOf<Gag>()
            it.documents.forEach {
//                Timber.d("Success: %s", it.data)
                list.add(Gag(it.getString("text"), it.getString("image"),
                    it.getBoolean("isFav") == true, 0))
            }
            _gags.value = list
        }.addOnFailureListener {
            Timber.d(it, "Failure")
        }.addOnCompleteListener {
            _loadingGags.value = false
        }
    }*/

    init {
        load()
    }
}