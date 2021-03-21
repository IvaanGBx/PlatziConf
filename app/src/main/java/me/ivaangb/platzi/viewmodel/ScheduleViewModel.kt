package me.ivaangb.platzi.viewmodel

import androidx.lifecycle.MutableLiveData
import me.ivaangb.platzi.model.Conference
import me.ivaangb.platzi.network.Callback
import me.ivaangb.platzi.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel {
    val firestoreService = FirestoreService()
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase() {
        firestoreService.getSchedule(object: Callback<List<Conference>> {
            override fun onSuccess(result: List<Conference>?) {
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