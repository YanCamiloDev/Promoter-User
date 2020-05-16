package net.yan.kotlin.promoterapp.promoorigin.principal

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class HomeViewModel(
    val resources: Resources
) : ViewModel() {

    val job = Job()
    val uiScope = CoroutineScope(Dispatchers.IO + job)
    val estaLogado = MutableLiveData<Boolean>()
    val newPhoto = MutableLiveData<Boolean>()

    init {
        try {
            val user = FirebaseAuth.getInstance().currentUser
            estaLogado.value = user == null
        } catch (e: FirebaseException) {
            Log.i("DADO", e.message)
        }

    }

    fun sair() {
        FirebaseAuth.getInstance().signOut()
        estaLogado.value = true
    }


    fun addLocalAndVenda() {
        newPhoto.value = true
    }

    fun addLocalAndVendaClose() {
        newPhoto.value = false
    }

    fun novaTela() {
        estaLogado.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}