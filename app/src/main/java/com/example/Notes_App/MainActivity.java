package com.example.Notes_App;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import com.example.Notes_App.domain.AppRouteManger;
import com.example.Notes_App.domain.Note;
import com.example.Notes_App.ui.about.AboutFragment;
import com.example.Notes_App.ui.auth.AuthFragment;
import com.example.Notes_App.ui.creator.NoteCreatorFragment;
import com.example.Notes_App.ui.details.NoteDetailsFragment;
import com.example.Notes_App.ui.editor.NoteEditorFragment;
import com.example.Notes_App.ui.list.NotesListFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements AppRouteManger {

    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.my_notes_option) {
                showNotesList();
                return true;
            }

            if (item.getItemId() == R.id.settings_option) {
                //TODO: setting option
                Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
            }
            if (item.getItemId() == R.id.about_option) {
                showAbout();
                return true;
            }
            return false;
        });


        if (savedInstanceState == null) {
            showAuth();
        }

        getSupportFragmentManager().setFragmentResultListener(AuthFragment.AUTH_RESULT, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull  String requestKey, @NonNull Bundle result) {
                showAuth();
            }
        });

    }
    //TODO: Landscape orientation mode


    @Override
    public void showNotesList() {
        fragmentManager.beginTransaction()
                .replace(R.id.main_container, NotesListFragment.newInstance(), NotesListFragment.TAG)
                .commit();
    }

    @Override
    public void showNoteDetails(Note note) {
        fragmentManager.beginTransaction()
                .addToBackStack(NoteDetailsFragment.TAG)
                .replace(R.id.main_container, NoteDetailsFragment.newInstance(note), NoteDetailsFragment.TAG)
                .commit();
    }

    @Override
    public void showAbout() {
        fragmentManager
                .beginTransaction()
                .addToBackStack(AboutFragment.TAG)
                .replace(R.id.main_container, AboutFragment.newInstance(), AboutFragment.TAG)
                .commit();
    }

    @Override
    public void showNoteCreator() {
        fragmentManager
                .beginTransaction()
                .addToBackStack(NoteCreatorFragment.TAG)
                .replace(R.id.main_container, NoteCreatorFragment.newInstance(), NoteCreatorFragment.TAG)
                .commit();
    }

    @Override
    public void showNoteEditor(Note note) {
        fragmentManager
                .beginTransaction()
                .addToBackStack(NoteCreatorFragment.TAG)
                .replace(R.id.main_container, NoteEditorFragment.newInstance(note), NoteEditorFragment.TAG)
                .commit();
    }

    @Override
    public void showAuth() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.main_container, AuthFragment.newInstance(), AuthFragment.TAG)
                .commit();
    }

    @Override
    public void back() {
        fragmentManager.popBackStack();
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