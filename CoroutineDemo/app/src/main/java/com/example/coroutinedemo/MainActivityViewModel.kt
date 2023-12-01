package com.example.coroutinedemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel() {
    private var userRepository  = UserRepository()
    var users: MutableLiveData<List<User>?> = MutableLiveData()
    // This Job instance is used to figure out the context of the coroutine scope in order to clean up the running
    // coroutines
    // private val myJob = Job()
    // This view model scope is bounded to view model lifecycle
    // private val myScope = CoroutineScope(Dispatchers.IO + myJob)

    fun getUserData() {
        /* Rather than creating custom scope and managing it with a Job , we can create a view model scope like this
        Any coroutine created in this view model scope will automatically be cleared when this view model is cleared
        easy peazy
        */

        viewModelScope.launch {
            // write code here
            var result : List<User>? = null
            withContext(Dispatchers.IO) {
                result = userRepository.getUsers()
            }
            users.value = result
        }
        /*
        myScope.launch {
            // write some code
        }
         */
    }

    /*
    override fun onCleared() {
        super.onCleared()
        // cancel all the co-routines created in this ViewModel
        myJob.cancel()
    }
     */
}