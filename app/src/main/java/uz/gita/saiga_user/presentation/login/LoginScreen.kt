package uz.gita.saiga_user.presentation.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.saiga_user.R
import uz.gita.saiga_user.databinding.ScreenLoginBinding
import uz.gita.saiga_user.presenter.LoginViewModelImpl
import uz.gita.saiga_user.utils.extensions.include

// Created by Jamshid Isoqov on 12/14/2022
@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.screen_login) {
    private val viewModel: LoginViewModel by viewModels<LoginViewModelImpl>()

    private val viewBinding: ScreenLoginBinding by viewBinding(ScreenLoginBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.include {



    }

}