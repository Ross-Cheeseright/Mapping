
package uk.co.twobeardedguys.ross.mapping;

import android.view.View;
import android.view.View.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements OnClickListener
{

    MapView mv;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_main);

        //Button button = (Button)findViewById(R.id.btn1);
        //button.setOnClickListener(this);
        mv = (MapView)findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(16);
        mv.getController().setCenter(new GeoPoint(51.05,-0.72));
    }

    @Override
    public void onClick(View v) {
        //EditText et1 = (EditText)findViewById(R.id.et1);
       // EditText et2 = (EditText)findViewById(R.id.et2);
        //double lat = Double.parseDouble(et1.getText().toString());
        //double lon = Double.parseDouble(et2.getText().toString());
        //mv.getController().setCenter(new GeoPoint(lat,lon));
    }


    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.choosemap) {
            Intent intent = new Intent(this, MapChooseActivity.class);
            startActivityForResult(intent,0);
            return true;

        } else if (item.getItemId() == R.id.setlocation) {
            Intent intent = new Intent(this, LayoutChooseActivity.class);
            startActivityForResult(intent,1);
            return true;
        }
        return false;

    }


    protected void onActivityResult(int requestCode,int resultCode,Intent intent)
    {

        if(requestCode==0)
        {

            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                boolean hikebikemap = extras.getBoolean("uk.co.twobeardedguys.ross.hikebikemap");
                if(hikebikemap==true)
                {
                    mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
                }
                else
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }
        }

        if(requestCode==1) {
            if(resultCode==RESULT_OK) {
                Bundle extras = intent.getExtras();
                double lat = extras.getDouble("uk.co.twobeardedguys.ross.lat");
                double lon = extras.getDouble("uk.co.twobeardedguys.ross.lon");
                mv.getController().setCenter(new GeoPoint(lat,lon));
            }
        }
    }


}