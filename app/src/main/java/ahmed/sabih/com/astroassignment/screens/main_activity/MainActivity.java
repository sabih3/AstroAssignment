package ahmed.sabih.com.astroassignment.screens.main_activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ahmed.sabih.com.astroassignment.R;
import ahmed.sabih.com.astroassignment.screens.tv_guide.TVGuideFragment;
import ahmed.sabih.com.astroassignment.utils.NavigationController;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityView,
        NavigationView.OnNavigationItemSelectedListener,TVGuideFragment.OnTvGuideFragmentInteraction{

    @BindView(R.id.drawer_layout)DrawerLayout drawerLayout;
    @BindView(R.id.drawer_list)NavigationView drawerList;

    private MainActivityPresenter presenter;
    private ArrayAdapter<String> drawerAdapter;

    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainActivityPresenter(this);

        presenter.initializeDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(mDrawerToggle);

        drawerList.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return true;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.channel_list:
                drawerLayout.closeDrawers();
                getSupportActionBar().setTitle("Channels");
                NavigationController.showChannelListScreen(MainActivity.this,getSupportFragmentManager());
            break;

            case R.id.tv_guide:
                drawerLayout.closeDrawers();
                getSupportActionBar().setTitle("TV Guide");
                NavigationController.showTVGuideScreen(this,getSupportFragmentManager());
            break;

            case R.id.favourites:
                drawerLayout.closeDrawers();
                getSupportActionBar().setTitle("Favourites");
                NavigationController.showFavouritesScreen(this,getSupportFragmentManager());
            break;

        }
        return true;
    }

    @Override
    public void onFragmentInteraction() {

    }
}

