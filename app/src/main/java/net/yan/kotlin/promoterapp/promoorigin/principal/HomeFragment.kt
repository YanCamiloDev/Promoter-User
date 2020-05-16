package net.yan.kotlin.promoterapp.promoorigin.principal


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.navigation.NavigationView
import net.yan.kotlin.promoterapp.promoorigin.R
import net.yan.kotlin.promoterapp.promoorigin.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModelFactory = HomeViewModelFactory(resources)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

        viewModel.estaLogado.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
            }
        })

        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN) {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                    } else {
                        activity?.finish()
                    }
                }
            })
        viewModel.newPhoto.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToOptionOneFragment())
                viewModel.addLocalAndVendaClose()
            }

        })


        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.navigationView.setNavigationItemSelectedListener(object :
            NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                if (item.itemId == R.id.sair) {
                    viewModel.sair()
                }
                return true
            }
        })
        val linear = binding.bottomDrawer
        bottomSheetBehavior = BottomSheetBehavior.from(linear)
        binding.bar.setNavigationOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED

        }
        binding.bar.setNavigationIcon(R.drawable.ic_menu_black_24dp)

        return binding.root
    }


}
