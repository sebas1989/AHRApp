package autodromo.punkmkt.com.ahrapp;

/**
 * Created by sebastianmendezgiron on 09/10/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class GraciasParticiparActivity extends Activity {
    private TextView ir_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Tracker tracker = ((MyVolleySingleton) getApplication()).getTracker(MyVolleySingleton.TrackerName.APP_TRACKER);
        tracker.setScreenName(getString(R.string.gracias_participar));
        tracker.send(new HitBuilders.ScreenViewBuilder().build());

        setContentView(R.layout.activity_gracias_participar);
        ir_home = (TextView) this.findViewById(R.id.go_to_home);

        ir_home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(GraciasParticiparActivity.this, BaseActivity.class);
                GraciasParticiparActivity.this.startActivity(myIntent);
            }
        });
    }

}
