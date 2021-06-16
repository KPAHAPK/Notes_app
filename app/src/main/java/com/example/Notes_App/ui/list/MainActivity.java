package com.example.Notes_App.ui.list;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.Notes_App.AboutFragment;
import com.example.Notes_App.R;
import com.example.Notes_App.domain.Note;
import com.example.Notes_App.ui.details.NoteDetailsFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NotesListFragment.OnNotesClicked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.settings_option) {
                    Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (item.getItemId() == R.id.about_option) {
                    Toast.makeText(MainActivity.this, "About", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.main_container, AboutFragment.newInstance(), "AboutFragment")
                            .commit();
                    return true;
                }
                return false;
            }
        });


        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .replace(R.id.main_container, NotesListFragment.newInstance(), "NotesListFragment")
                .commit();

    }

    @Override
    public void onNotesClicked(Note note) {

        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;


        if (isLandscape) {


            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.secondary_container, NoteDetailsFragment.newInstance(note), "NoteDetailsFragment")
                    .commit();

        } else {
//            Intent intent = new Intent(this, NoteDetailsActivity.class);
//            intent.putExtra(NoteDetailsActivity.KEY, note);
//            startActivity(intent);
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    .addToBackStack(null)
                    .replace(R.id.main_container, NoteDetailsFragment.newInstance(note), "NoteDetailsFragment")
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}