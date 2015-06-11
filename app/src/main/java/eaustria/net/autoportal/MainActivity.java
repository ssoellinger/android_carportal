package eaustria.net.autoportal;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;


import eaustria.net.autoportal.eaustria.net.model.Car;

/**
 * Created by bmayr on 09.03.15.
 */
public class MainActivity extends Activity implements ListFragment.OnSelectionChangedListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static LocationManager locMan = null;
    private DetailFragment detailFragment;
    private boolean showDetail = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locMan = (LocationManager) getSystemService(LOCATION_SERVICE);

        detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detail_fragment);
        showDetail = detailFragment != null && detailFragment.isInLayout();
    }
    public void onLocationChanged(Location location)
    {
        if(location == null) return;
        returnLocation(location);
    }
    public double[] returnLocation(Location loc)
    {
        double[] positions = new double[2];
        positions[0] = loc.getLongitude();
        positions[1] = loc.getLatitude();
        return positions;
    }
    @Override
    public void onSelectionChanged(int pos, Car item) {
        if (showDetail)
            detailFragment.show(pos, item);
        else
            callFragmentActivity(pos, item);
    }

    private void callFragmentActivity(int pos, Car item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("POS", pos);
        intent.putExtra("ITEM", item);
        startActivity(intent);
    }

}
