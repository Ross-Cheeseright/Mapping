package uk.co.twobeardedguys.ross.mapping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 2cheer65 on 05/02/2018.
 */

public class LayoutChooseActivity extends Activity implements View.OnClickListener {

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_choose);




        Button button = (Button)findViewById(R.id.btn1);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();

            EditText et1 = (EditText)findViewById(R.id.et1);
            EditText et2 = (EditText)findViewById(R.id.et2);
            double lat=0;
            double lon=0;
            if (v.getId()==R.id.btn1)
            {
                lat = Double.parseDouble(et1.getText().toString());
                lon = Double.parseDouble(et2.getText().toString());
            }
            bundle.putDouble("uk.co.twobeardedguys.ross.setLat",lat);
            bundle.putDouble("uk.co.twobeardedguys.ross.setLat",lon);
            intent.putExtras(bundle);
            setResult(RESULT_OK,intent);
            finish();

        }
    }
}
