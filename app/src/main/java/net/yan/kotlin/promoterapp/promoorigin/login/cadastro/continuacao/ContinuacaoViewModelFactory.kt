import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.yan.kotlin.promoterapp.promoorigin.data.FirebaseHelper
import net.yan.kotlin.promoterapp.promoorigin.databinding.FragmentContinuacaoBinding
import net.yan.kotlin.promoterapp.promoorigin.login.cadastro.continuacao.ContinuacaoViewModel


class ContinuacaoViewModelFactory(
    private val dataSource: FirebaseHelper,
    private val binding: FragmentContinuacaoBinding
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContinuacaoViewModel::class.java)) {
            return ContinuacaoViewModel(dataSource, binding) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}