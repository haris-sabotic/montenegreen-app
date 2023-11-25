package com.fiveg.montenegreen.ui.mapa

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.fiveg.montenegreen.R
import com.fiveg.montenegreen.databinding.FragmentMapaBinding
import com.fiveg.montenegreen.models.MarkerModel
import org.osmdroid.config.Configuration.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay


class MapaFragment : Fragment() {
    private var map: MapView? = null

    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                setupMap()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Location permission is necessary in order to use the map",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    private var _binding: FragmentMapaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapaBinding.inflate(inflater, container, false)

        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                setupMap()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) -> {
                Toast.makeText(
                    requireContext(),
                    "Location permission is necessary in order to use the map",
                    Toast.LENGTH_SHORT
                ).show()

                requestLocationPermission()
            }
            else -> {
                requestLocationPermission()
            }
        }

        return binding.root
    }

    fun setupMap() {
        getInstance().load(requireContext(), requireContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE))
        map = binding.mapaMap
        map?.setTileSource(TileSourceFactory.MAPNIK)

        val mapController = map?.controller
        mapController?.setZoom(10.0)
        val startPoint = GeoPoint(42.429603330704495, 19.292775938348417)
        mapController?.setCenter(startPoint)

        val myLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), map)
        myLocationOverlay.enableMyLocation()
        map?.overlays?.add(myLocationOverlay)

        // MARKERS
        val markers = arrayOf(
            MarkerModel("NeurobotX", GeoPoint(42.4442581680344, 19.24835785766236), ContextCompat.getDrawable(requireContext(), R.drawable.marker_neurobotx)!!)
        )

        map?.let {
            for (markerModel in markers) {
                val marker = Marker(it)
                marker.title = markerModel.title
                marker.position = markerModel.latlong
                marker.icon = markerModel.iconDrawable
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)

                it.overlays.add(marker)
            }
        }
    }

    fun requestLocationPermission() {
        requestPermissionLauncher.launch(
            android.Manifest.permission.ACCESS_FINE_LOCATION)
    }

    override fun onResume() {
        super.onResume()
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map?.onResume() //needed for compass, my location overlays, v6.0.0 and up
    }

    override fun onPause() {
        super.onPause()
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map?.onPause()  //needed for compass, my location overlays, v6.0.0 and up
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}