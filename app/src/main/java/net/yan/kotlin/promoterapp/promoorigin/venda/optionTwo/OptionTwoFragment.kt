package net.yan.kotlin.promoterapp.promoorigin.venda.optionTwo


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
import androidx.navigation.fragment.navArgs
import net.yan.kotlin.promoterapp.promoorigin.R
import net.yan.kotlin.promoterapp.promoorigin.databinding.OptionTwoFragmentBinding


class OptionTwoFragment : Fragment() {


    private lateinit var viewModel: OptionTwoViewModel
    private lateinit var binding: OptionTwoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.option_two_fragment, container, false)
        val viewModelFactory = OptionTwoViewModelFactory(resources)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(OptionTwoViewModel::class.java)
        val argments by navArgs<OptionTwoFragmentArgs>()
        val adapter = AdapterTwo(Clique {
            viewModel.selecionarNome(it)
        })
        viewModel.nomesLista.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.adicionarLista(it)
            }
        })
        viewModel.cidade.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                findNavController().navigate(
                    OptionTwoFragmentDirections.actionOptionTwoFragmentToVendaFragment(
                        cidade = it.id,
                        cliente = argments.cliente,
                        cidadeNome = it.local,
                        clienteNome = argments.clienteNome
                    )
                )
                viewModel.selecionarNomeExit()
            }
        })
        binding.search2.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
        binding.recycler2.adapter = adapter
        binding.toolbar2.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

}
