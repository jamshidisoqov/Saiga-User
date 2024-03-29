package uz.gita.saiga_user.presentation.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import ru.ldralighieri.corbind.widget.textChanges
import uz.gita.saiga_user.R
import uz.gita.saiga_user.databinding.ScreenLoginBinding
import uz.gita.saiga_user.presenter.LoginViewModelImpl
import uz.gita.saiga_user.utils.DEBOUNCE_TEXT_CHANGES
import uz.gita.saiga_user.utils.DEBOUNCE_VIEW_CLICK
import uz.gita.saiga_user.utils.extensions.*

// Created by Jamshid Isoqov on 12/14/2022
@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {
    private val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()

    private val viewBinding: ScreenLoginBinding by viewBinding(ScreenLoginBinding::bind)

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.include {

        viewModel.loadingSharedFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.errorSharedFlow.onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.messageSharedFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        tvToRegister.clicks().debounce(DEBOUNCE_VIEW_CLICK)
            .onEach {
                viewModel.navigateToRegister()
            }.launchIn(lifecycleScope)

        inputPhone.textChanges()
            .debounce(DEBOUNCE_TEXT_CHANGES)
            .onEach {
                btnLogin.isEnabled = it.length == 17
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        btnLogin.clicks()
            .debounce(DEBOUNCE_VIEW_CLICK)
            .onEach {
                viewModel.login(inputPhone.text.toString())
            }.launchIn(lifecycleScope)

    }

}