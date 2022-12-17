package uz.gita.saiga_user.presentation.directional_taxi

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import ru.ldralighieri.corbind.widget.textChanges
import uz.gita.saiga_user.R
import uz.gita.saiga_user.databinding.ScreenDirectionalTaxiBinding
import uz.gita.saiga_user.presentation.directional_taxi.adapter.DirectionalTaxiAdapter
import uz.gita.saiga_user.presenter.DirectionalTaxiViewModelImpl
import uz.gita.saiga_user.utils.DEBOUNCE_TEXT_CHANGES
import uz.gita.saiga_user.utils.extensions.*

// Created by Jamshid Isoqov on 12/14/2022
@AndroidEntryPoint
class DirectionalTaxiScreen : Fragment(R.layout.screen_directional_taxi) {

    private val adapter: DirectionalTaxiAdapter by lazy(LazyThreadSafetyMode.NONE) {
        DirectionalTaxiAdapter()
    }

    private val viewModel: DirectionalTaxiViewModel by viewModels<DirectionalTaxiViewModelImpl>()

    private val viewBinding: ScreenDirectionalTaxiBinding by viewBinding()

    private val navController: NavController by lazy(LazyThreadSafetyMode.NONE) {
        findNavController()
    }

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.include {

        listDirections.adapter = adapter

        viewModel.loadingSharedFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.errorSharedFlow.onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.messageSharedFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.allDirectionalTaxis.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            viewModel.navigateToDirectionalDetail(it)
        }
        imageFilter.clicks()
            .onEach {
                //Todo filter findNavController().navigate()
            }.launchIn(lifecycleScope)

        inputSearchTaxi.textChanges()
            .debounce(DEBOUNCE_TEXT_CHANGES)
            .onEach {
                viewModel.search(it.toString())
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.getAllDirectionalData()
    }

}