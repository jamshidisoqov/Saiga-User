package uz.gita.saiga_user.presentation.main.order

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ldralighieri.corbind.view.clicks
import uz.gita.saiga_user.R
import uz.gita.saiga_user.data.remote.response.enums.CarType
import uz.gita.saiga_user.databinding.DialogOrderBinding
import uz.gita.saiga_user.presenter.OrderDialogViewModelImpl
import uz.gita.saiga_user.utils.DEBOUNCE_VIEW_CLICK
import uz.gita.saiga_user.utils.extensions.*

// Created by Jamshid Isoqov on 12/17/2022
@AndroidEntryPoint
class OrderDialog(
    private val whereFrom: String,
    private val whereFromLatLng: LatLng,
    private val whereTo: String,
    private val isFavourite: Boolean
) : DialogFragment(R.layout.dialog_order) {

    private val viewModel: OrderDialogViewModel by viewModels<OrderDialogViewModelImpl>()

    private val viewBinding: DialogOrderBinding by viewBinding()

    private var carType: Int = 0

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.include {

        viewModel.setFavouriteOrder(isFavourite)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        viewModel.loadingSharedFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.errorSharedFlow.onEach {
            showErrorDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.messageSharedFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.dismissDialog.onEach {
            dismiss()
        }.launchIn(lifecycleScope)

        viewModel.isFavouriteOrder.onEach {
            if (it) imageFavourite.gone()
            else imageFavourite.visible()
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        imageFavourite.setOnClickListener {
            viewModel.addFavourite(whereFrom, whereFromLatLng, whereTo)
        }

        btnOrder.clicks()
            .debounce(DEBOUNCE_VIEW_CLICK)
            .onEach {
                viewModel.order(whereFrom, whereFromLatLng, whereTo, CarType.values()[carType])
            }.launchIn(lifecycleScope)

        for (i in 0 until containerCarTypeLl.childCount) {
            val frame = containerCarTypeLl.getChildAt(i)
            frame.tag = i
            frame.setOnClickListener {
                viewModel.setSelectedCarType(it.tag.toString().toInt())
            }
        }

        viewModel.selectedCarType.onEach { type ->
            for (i in 0 until containerCarTypeLl.childCount) {
                val frame = containerCarTypeLl.getChildAt(i)
                if (type == i) {
                    frame.setBackgroundResource(R.drawable.bg_car_type)
                } else {
                    frame.setBackgroundResource(R.drawable.bg_car_type_unselected)
                }
            }
            carType = type
        }.launchIn(viewLifecycleOwner.lifecycleScope)


    }

}