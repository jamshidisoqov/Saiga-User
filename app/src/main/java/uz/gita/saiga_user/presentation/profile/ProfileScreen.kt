package uz.gita.saiga_user.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.saiga_user.R
import uz.gita.saiga_user.databinding.ScreenProfileBinding
import uz.gita.saiga_user.presenter.ProfileViewModelImpl
import uz.gita.saiga_user.utils.extensions.*

// Created by Jamshid Isoqov on 12/14/2022
@AndroidEntryPoint
class ProfileScreen : Fragment(R.layout.screen_profile) {

    private val viewBinding: ScreenProfileBinding by viewBinding()

    private val viewModel: ProfileViewModel by viewModels<ProfileViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =
        viewBinding.include {

            viewModel.loadingSharedFlow.onEach {
                if (it) showProgress() else hideProgress()
            }.launchIn(lifecycleScope)

            viewModel.errorSharedFlow.onEach {
                showErrorDialog(it)
            }.launchIn(lifecycleScope)

            viewModel.messageSharedFlow.onEach {
                showMessageDialog(it)
            }.launchIn(lifecycleScope)

            viewModel.nameFlow.onEach {
                tvUserName.text = it
            }.launchIn(viewLifecycleOwner.lifecycleScope)

            viewModel.phoneFlow.onEach {
                tvPhoneNumber.text = it
            }.launchIn(viewLifecycleOwner.lifecycleScope)

            containerMyTrips.clicks()
                .onEach {
                    viewModel.openMyTrips()
                }.launchIn(lifecycleScope)

            containerService.clicks()
                .onEach {
                    viewModel.openServices()
                }.launchIn(lifecycleScope)

            containerSettings.clicks()
                .onEach {
                    viewModel.openSettings()
                }.launchIn(lifecycleScope)
            imageEdit.clicks()
                .onEach {
                    viewModel.editProfile()
                }.launchIn(lifecycleScope)

            viewModel.getData()

        }
}