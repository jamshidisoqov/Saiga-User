package uz.gita.saiga_user.presentation.directional_taxi.details

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.saiga_user.R
import uz.gita.saiga_user.databinding.ScreenDirectionalDetailBinding
import uz.gita.saiga_user.utils.extensions.*
import java.util.*

// Created by Jamshid Isoqov on 12/14/2022
@AndroidEntryPoint
class DirectionalDetailScreen : Fragment(R.layout.screen_directional_detail) {

    private val args: DirectionalDetailScreenArgs by navArgs()

    private val viewBinding: ScreenDirectionalDetailBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.include {
        val data = args.directionData
        val address = data.directionsData
        tvFirstAddress.text = address.addressFrom.title
        tvSecondAddress.text = address.addressTo.title
        // TODO tvDriverName.text = data
        tvMoney.text = data.price.getFinanceType()
        tvSchedule.text = getCurrentDate(Date(data.scheduleTime.time))
        imageBack.setOnClickListener {
            findNavController().navigateUp()
        }
        fabCall.setOnClickListener {
            hasPermission(Manifest.permission.CALL_PHONE, {
                //TODO call
            }) {
                toast(resources.getString(R.string.please_enable_call))
            }
        }
    }
}