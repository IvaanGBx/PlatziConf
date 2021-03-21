package me.ivaangb.platzi.viewmodel

import androidx.lifecycle.MutableLiveData
import me.ivaangb.platzi.model.Speaker
import me.ivaangb.platzi.network.Callback
import me.ivaangb.platzi.network.FirestoreService
import java.lang.Exception

class SpeakerViewModel {
    val firestoreService = FirestoreService()
    var listSchedule: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getSpeakerFromFirebase()
    }

    fun getSpeakerFromFirebase() {

        firestoreService.getSpeaker(object: Callback<List<Speaker>> {

            override fun onSuccess(result: List<Speaker>?) {
                listSchedule.postValue(result)
                processFinished()

            }

            override fun onFailed(exception: Exception) {
                processFinished()

            }

        })

    }

    fun processFinished() {
        isLoading.value = true
    }

}