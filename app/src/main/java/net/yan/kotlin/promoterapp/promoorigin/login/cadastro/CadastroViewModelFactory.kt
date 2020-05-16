package net.yan.kotlin.promoterapp.promoorigin.login.cadastro


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.yan.kotlin.promoterapp.promoorigin.data.FirebaseHelper
import net.yan.kotlin.promoterapp.promoorigin.databinding.FragmentCadastroBinding

class CadastroViewModelFactory(
    private val dataSource: FirebaseHelper,
    private val binding: FragmentCadastroBinding
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CadastroViewModel::class.java)) {
            return CadastroViewModel(dataSource, binding) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}