package net.yan.kotlin.promoterapp.promoorigin.venda.optionOne


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import net.yan.kotlin.promoterapp.promoorigin.R
import net.yan.kotlin.promoterapp.promoorigin.databinding.FragmentOptionOneBinding
import net.yan.kotlin.promoterapp.promoorigin.venda.optionOne.adapter.Adapter
import net.yan.kotlin.promoterapp.promoorigin.venda.optionOne.adapter.ClienteListener


/**
 * A simple [Fragment] subclass.
 */
class OptionOneFragment : Fragment() {

    private lateinit var binding: FragmentOptionOneBinding
    private lateinit var viewModel: OptionOneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_option_one, container, false)
        val viewModelFactory = OptionOneViewModelFactory(resources)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(OptionOneViewModel::class.java)

        val adapter = Adapter(ClienteListener { nome ->
            viewModel.selecionarNome(nome)
        })

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })

        viewModel.nome.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                findNavController().navigate(
                    OptionOneFragmentDirections.actionOptionOneFragmentToOptionTwoFragment(
                        it.id,
                        it.endereco
                    )
                )
                viewModel.selecionarNomeExit()
            }

        })
        viewModel.nomesLista.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.addHeaderAndSubmitList(it)
            }
        })
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.recycler.adapter = adapter

        return binding.root
    }

}
