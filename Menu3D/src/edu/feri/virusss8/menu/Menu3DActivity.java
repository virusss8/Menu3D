package edu.feri.virusss8.menu;

import com.badlogic.gdx.backends.android.AndroidApplication;

import android.os.Bundle;

public class Menu3DActivity extends AndroidApplication {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        OdpriActivity oa = new OdpriActivity(this);
        initialize(new MyPyramide(oa), false);
    }
}