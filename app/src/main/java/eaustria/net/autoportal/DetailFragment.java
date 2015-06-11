package eaustria.net.autoportal;

import android.location.Location;
import android.location.LocationManager;
import android.widget.TextView;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import eaustria.net.autoportal.eaustria.net.model.Car;

/**
 * Created by bmayr on 09.03.15.
 */
public class DetailFragment extends Fragment {
    private static final String TAG = DetailFragment.class.getSimpleName();
    private TextView mId;
    private TextView mManufacturer;
    private TextView mBrand;

    private IGeoPoint mGeoPoint;
    private MapView mMapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView DetailFragment");
        View view = inflater.inflate(R.layout.detail_fragment, container);
        mId = (TextView) view.findViewById(R.id.car_id);
        mManufacturer = (TextView) view.findViewById(R.id.car_manufacturer);
        mBrand = (TextView) view.findViewById(R.id.car_brand);
        mMapView = (MapView) view.findViewById(R.id.mapview);
        initializeMap();
        return view;
    }

    public void show(int pos, Car car) {
        Log.d(TAG, "show Detailfragment " + pos + "/" + car.toString());
        mId.setText(String.valueOf(car.getId()));
        mManufacturer.setText(car.getManufacturer());
        mBrand.setText(car.getBrand());
        mGeoPoint = car.getPosition();

        mMapView.getController().setCenter(mGeoPoint);
    }

    public void initializeMap() {
        mMapView.setTileSource(TileSourceFactory.MAPNIK);
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);
        mMapView.setUseDataConnection(true);
        mMapView.getController().setZoom(15);
    }
}
