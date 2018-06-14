package com.edgar.lilyhouse.UI.MainScene;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.edgar.lilyhouse.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "====================" + MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
                this,
                findViewById(R.id.main_fragment_container),
                new AccelerateDecelerateInterpolator(),
                getResources().getDrawable(R.drawable.lily_logo_menu), // Menu open icon
                getResources().getDrawable(R.drawable.ic_close))); // Menu close icon

        // Set cut corner background for API 23+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            findViewById(R.id.main_fragment_container).setBackground(getDrawable(R.drawable.corner_cut_background_shape));
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_fragment_container, MangaListFragment.newInstance());
        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
