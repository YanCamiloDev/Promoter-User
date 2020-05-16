package net.yan.kotlin.promoterapp.promoorigin.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.yan.kotlin.promoterapp.promoorigin.data.FirebaseHelper
import net.yan.kotlin.promoterapp.promoorigin.databinding.FragmentLoginBinding

class LoginViewModelFactory(
    private val dataSource: FirebaseHelper,
    private val binding: FragmentLoginBinding
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(dataSource, binding) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
