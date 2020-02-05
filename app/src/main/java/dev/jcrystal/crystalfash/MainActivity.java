package dev.jcrystal.crystalfash;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import jcrystal.mobile.entities.Cart;
import jcrystal.mobile.net.controllers.ManagerCart;
import jcrystal.mobile.net.utils.On1SuccessListener;
import jcrystal.mobile.net.utils.OnErrorListener;
import jcrystal.mobile.net.utils.OnVoidSuccessListener;
import jcrystal.mobile.net.utils.RequestError;

public class MainActivity extends AppCompatActivity implements On1SuccessListener, OnErrorListener, OnVoidSuccessListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ManagerCart.createCart(this, this, this);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public void onSuccess(Object o) {
        Toast.makeText(this, "Carrito creado", Toast.LENGTH_LONG).show();
        Cart carrito = (Cart) o;
        //Listo para usar el carrito
    }

    @Override
    public void onError(RequestError error) {
        Toast.makeText(this.getBaseContext(), error.mensaje, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "Exito", Toast.LENGTH_LONG).show();
    }
}
