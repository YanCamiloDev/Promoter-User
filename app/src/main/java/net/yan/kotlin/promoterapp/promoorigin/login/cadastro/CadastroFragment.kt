package net.yan.kotlin.promoterapp.promoorigin.login.cadastro

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import net.yan.kotlin.promoterapp.promoorigin.R
import net.yan.kotlin.promoterapp.promoorigin.data.FirebaseHelper
import net.yan.kotlin.promoterapp.promoorigin.databinding.FragmentCadastroBinding


/**
 * A simple [Fragment] subclass.
 */
class CadastroFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCadastroBinding>(
            inflater,
            R.layout.fragment_cadastro,
            container,
            false
        )
        val firebase = FirebaseHelper()
        val viewModelFactory = CadastroViewModelFactory(firebase, binding)
        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CadastroViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.isLogado.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(
                    CadastroFragmentDirections.actionCadastroFragmentToContinuacaoFragment(
                        binding.loginCad.text.toString(),
                        binding.senhaCad.text.toString()
                    )
                )
                viewModel.mudouTela()
            }
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            if (it.first == 1) {
                binding.loginCad.error = it.second
            } else {
                binding.senhaCad.error = it.second
            }
        })
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar1)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_cadastro, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.back) {
            findNavController().popBackStack()
        }
        return true
    }

}
