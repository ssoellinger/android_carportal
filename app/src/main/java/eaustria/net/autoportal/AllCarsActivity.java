package eaustria.net.autoportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;

import eaustria.net.autoportal.eaustria.net.model.Car;

/**
 * Created by bmayr on 09.03.15.
 */
public class AllCarsActivity extends Activity {
    MapView mMapView;
    List<Car> mCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allcars);
        mMapView = (MapView) findViewById(R.id.mapview);
        Intent intent =  getIntent();
        mCars = (List<Car>) intent.getSerializableExtra("ITEMS");
        initializeMap();
    }

    private void initializeMap() {
        mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);
        mMapView.setUseDataConnection(true);
        mMapView.getController().setZoom(15);
        mMapView.getController().setCenter(mCars.get(1).getPosition());

        addMarkers();
        mMapView.postInvalidate();
    }

    private void addMarkers() {
        OverlayItem overlayItem;
        List<OverlayItem> items = new ArrayList<>();
        for ( Car car : mCars) {
            overlayItem = new OverlayItem(String.valueOf(car.getId()), car.getBrand() + ", " +
                    car.getManufacturer(), car.getPosition());
            overlayItem.setMarker(getResources().getDrawable(R.drawable.car_icon));
            items.add(overlayItem);
        }
        ItemizedIconOverlay<OverlayItem> overlayItems = new ItemizedIconOverlay<>(
                items, getResources().getDrawable(R.drawable.car_icon),
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(int index, OverlayItem item) {
                        return false;
                    }

                    @Override
                    public boolean onItemLongPress(int index, OverlayItem item) {
                        return false;
                    }
                },
                new DefaultResourceProxyImpl(this));

        mMapView.getOverlays().add(overlayItems);
    }
}
