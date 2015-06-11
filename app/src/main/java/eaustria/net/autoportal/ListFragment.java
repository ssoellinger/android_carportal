package eaustria.net.autoportal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

import eaustria.net.autoportal.eaustria.net.model.Car;

/**
 * Created by bmayr on 09.03.15.
 */
public class ListFragment extends Fragment {


    public static final String TAG = MainActivity.TAG;

    ListView listView;
    ArrayList<Car> items = new ArrayList<Car>();
    private OnSelectionChangedListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillArrayList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView ListFragment");
        View view = inflater.inflate(R.layout.list_fragment, container);
        listView = (ListView) view.findViewById(android.R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                entrySelected(pos);
            }
        });
        Button btn = (Button) view.findViewById(R.id.btn_show_all_cars);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       btnShowAllClick(view);
                                   }
                               }
        );
        return view;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart ListFragment");
        super.onStart();
        final ArrayAdapter<Car> adapter = new ArrayAdapter<Car>(getActivity(),
                android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if ( activity instanceof OnSelectionChangedListener ) {
            listener = (OnSelectionChangedListener) activity;
        }
    }

    public void entrySelected(int pos) {
        Car car = items.get(pos);
        if ( listener != null ) {
            listener.onSelectionChanged(pos, car);
        }
    }

    private void fillArrayList() {
        items = new ArrayList<Car>();
        items.add(new Car(1, "VM", "Passat", new GeoPoint(48.2353168, 13.8367158)));
        items.add(new Car(2, "Mercedes", "190D", new GeoPoint(48.3353168, 13.8368158)));
        items.add(new Car(3, "Audi", "A4", new GeoPoint(48.22558754, 13.94851685)));
        items.add(new Car(4, "Jeep", "Cherokee", new GeoPoint(48.15326053, 14.03915405)));
    }

    public void btnShowAllClick(View view) {
        Intent intent = new Intent(getActivity(), AllCarsActivity.class);
        intent.putExtra("ITEMS", items);
        startActivity(intent);
    }

    public interface OnSelectionChangedListener {
        public void onSelectionChanged(int pos, Car item);
    }
}
