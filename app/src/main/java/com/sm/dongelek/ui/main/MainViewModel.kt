package com.sm.dongelek.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sm.dongelek.data.likes.LikesMemoryDataSource
import com.sm.dongelek.data.likes.LikesRepository
import com.sm.dongelek.data.models.Gag
import timber.log.Timber

class MainViewModel: ViewModel() {

    private val likesRepository = LikesRepository(LikesMemoryDataSource(), LikesMemoryDataSource()) {
        // TODO: Update livedata
    }

    private  val db = Firebase.firestore

    private val _textGags = MutableLiveData<List<Gag>>()
    val textGags: LiveData<List<Gag>> = _textGags

    private val _imageGags = MutableLiveData<List<Gag>>()
    val imageGags: LiveData<List<Gag>> = _imageGags

    private val _loadingGags = MutableLiveData(false)
    val loadingGags: LiveData<Boolean> = _loadingGags

    fun load(){
        _loadingGags.value = true
        db.collection("Gags").get().addOnSuccessListener {
            val textList = mutableListOf<Gag>()
            val imageList = mutableListOf<Gag>()
            it.documents.forEach {
//                Timber.d("Success: %s", it.data)
                if (it.getString("text").isNullOrEmpty()){
                    imageList.add(Gag(it.getString("text"), it.getString("image")))
                } else {
                    textList.add(Gag(it.getString("text"), it.getString("image")))
                }
            }
            _imageGags.value = imageList
            _textGags.value = textList
        }.addOnFailureListener {
            Timber.d(it, "Failure")
        }.addOnCompleteListener {
            _loadingGags.value = false
        }
    }

    init {
        load()
    }
}