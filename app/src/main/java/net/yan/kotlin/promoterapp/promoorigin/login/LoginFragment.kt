package net.yan.kotlin.promoterapp.promoorigin.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import net.yan.kotlin.promoterapp.promoorigin.Permissao
import net.yan.kotlin.promoterapp.promoorigin.R
import net.yan.kotlin.promoterapp.promoorigin.data.FirebaseHelper
import net.yan.kotlin.promoterapp.promoorigin.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        activity?.let { Permissao.validarPermissoes(it) }


        val firebase = FirebaseHelper()
        val viewModelFactory = LoginViewModelFactory(firebase, binding)
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.mudouTela()

        viewModel.isLogado.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(R.id.homeFragment)
                viewModel.mudouTelaHome()
            }
        })
        viewModel.cadastrar.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToCadastroFragment())
                viewModel.mudouTela()
            }
        })

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.finish()
                }
            })
        return binding.root
    }

}
