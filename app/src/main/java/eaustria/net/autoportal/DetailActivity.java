package eaustria.net.autoportal;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import eaustria.net.autoportal.eaustria.net.model.Car;

/**
 * Created by bmayr on 09.03.15.
 */
public class DetailActivity extends Activity {
    public final static String TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //exit, if in landscape mode
        int orientation = getResources().getConfiguration().orientation;
        if ( orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        setContentView(R.layout.activity_detail);
        handleIntent();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if ( intent == null ) return;
        DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.frag_detail);
        int pos = intent.getIntExtra("POS", -1);
        Car car = null;
        if ( intent.hasExtra("ITEM") ) car = (Car) intent.getSerializableExtra("ITEM");
        if ( car != null && detailFragment != null )
            detailFragment.show(pos, car);
        else
            Log.d(TAG, "car item == null");
    }
}
