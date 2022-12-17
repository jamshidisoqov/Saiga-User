package uz.gita.saiga_user.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.ldralighieri.corbind.view.clicks
import ru.ldralighieri.corbind.widget.textChanges
import uz.gita.saiga_user.R
import uz.gita.saiga_user.databinding.ContentMainBinding
import uz.gita.saiga_user.databinding.NavHeaderBinding
import uz.gita.saiga_user.databinding.ScreenMainBinding
import uz.gita.saiga_user.presentation.main.helper.MapHelper
import uz.gita.saiga_user.presentation.main.order.OrderDialog
import uz.gita.saiga_user.presenter.MainViewModelImpl
import uz.gita.saiga_user.utils.DEBOUNCE_VIEW_CLICK
import uz.gita.saiga_user.utils.extensions.*

// Created by Jamshid Isoqov on 12/14/2022
@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {

    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()

    private val viewBinding: ScreenMainBinding by viewBinding()

    private lateinit var headerBinding: NavHeaderBinding

    private var job: Job? = null

    private var boolFrom: Boolean = true

    private var boolTo: Boolean = false

    private lateinit var googleMap: GoogleMap

    private var whereFrom: String = "Nukus"

    private var whereTo: String = ""

    private lateinit var center: LatLng

    private lateinit var contentMainBinding: ContentMainBinding

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = viewBinding.include {

        mapInit()

        headerBinding = NavHeaderBinding.bind(navigationView.getHeaderView(0))

        viewModel.address.onEach {
            contentMain.inputWhereFrom.setText(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.loadingSharedFlow.onEach {
            if (it) showProgress() else hideProgress()
        }.launchIn(lifecycleScope)

        viewModel.messageSharedFlow.onEach {
            showMessageDialog(it)
        }.launchIn(lifecycleScope)

        viewModel.errorSharedFlow.onEach {
            showErrorDialog(message = it)
        }.launchIn(lifecycleScope)

        viewModel.currentLocationFlow.observe(viewLifecycleOwner) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it, 16f))
        }

        viewModel.openOrderDialogFlow.onEach {
            val dialog = OrderDialog(whereFrom, center, whereTo, false)
            dialog.show(childFragmentManager, "order dialog")
        }.launchIn(lifecycleScope)

        headerBinding.root.setOnClickListener {
            viewModel.navigateToProfile()
        }

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_care -> viewModel.navigateToCustomerCare()
                R.id.menu_favourites -> viewModel.navigateToFavouriteAddress()
                R.id.menu_trips -> viewModel.navigateToTripsHistory()
                R.id.menu_settings -> viewModel.navigateToSettings()
                R.id.menu_directional_taxi -> viewModel.navigateToDirectionalTaxi()
            }
            root.closeDrawer(GravityCompat.START)
            true
        }

        contentMain.apply {
            imageMyLocation.setOnClickListener {
                viewModel.requestCurrentLocation()
            }
            imageMenu.setOnClickListener {
                viewBinding.root.openDrawer(GravityCompat.START)
            }
            inputWhereFrom.textChanges().onEach {
                boolFrom = it.toString().isNotEmpty()
                whereFrom = it.toString()
                check()
            }.launchIn(viewLifecycleOwner.lifecycleScope)
            inputWhereTo.textChanges().onEach {
                boolTo = it.toString().isNotEmpty()
                check()
                whereTo = it.toString()
            }.launchIn(viewLifecycleOwner.lifecycleScope)

            imageOrder.clicks()
                .debounce(DEBOUNCE_VIEW_CLICK)
                .onEach {
                    viewModel.openOrderDialog()
                }.launchIn(lifecycleScope)
        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun check() {
        viewBinding.contentMain.imageOrder.apply {
            val bool = boolFrom && boolTo
            isEnabled = bool
            setBackgroundResource(if (bool) R.drawable.bg_enabled else R.drawable.bg_disabled)
        }
    }

    private fun mapInit() = contentMainBinding.include {
        val map = childFragmentManager.findFragmentById(R.id.container_map) as MapHelper
        map.getMapAsync(map)
        map.onMapReady {
            googleMap = it
            googleMap.uiSettings.apply {
                isCompassEnabled = false
                isRotateGesturesEnabled = false
                isMyLocationButtonEnabled = false
            }
            googleMap.addMarker(MarkerOptions().position(NUKUS).title("Nukus"))
            if (!this@MainScreen::center.isInitialized)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NUKUS, 16f))


            googleMap.setOnCameraIdleListener {
                job?.cancel()
                job = viewLifecycleOwner.lifecycleScope.launch {
                    delay(2000L)
                    viewModel.getAddressByLocation(googleMap.cameraPosition.target)
                }
            }
            center = googleMap.cameraPosition.target
        }
    }

    companion object {
        private val NUKUS = LatLng(42.460168, 59.607280)
    }

}