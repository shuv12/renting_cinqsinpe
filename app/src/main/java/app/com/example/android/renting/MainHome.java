package app.com.example.android.renting;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.com.example.android.renting.model.Rent;
import jp.wasabeef.blurry.Blurry;

public class MainHome extends AppCompatActivity{

    private boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2;
    private Animation fab_open, fab_close, fab_rotate_forward, fab_rotate_backward;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private List<Rent> rentList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private String mTitle;
    private SearchView searchView;

    private boolean blurred = false;

    String[] daysLeft = new String[]{"98 days left", "22 days left"};
    String[] description = new String[]{"Subidha yukta flat vadma","Only for office"};
    String[] propertImage = new String[]{"http://plymouthpropertyinventories.co.uk/wp-content/uploads/2013/06/beautiful-property.jpg","http://www.the8000internationalbusinessclub.com/images/business/propertyworks/nice-property.jpg"};
    String[] profilepic = new String[]{"http://topnews.in/files/Keanu-Reeves-2.jpg","https://pbs.twimg.com/profile_images/703568535436398592/pYhDuLWB.jpg"};
    String[] profilename = new String[]{"Shuvam", "Akshay"};
    String[] price = new String[]{"22,000/mo","30,000/mo"};
    String[] favs = new String[]{"22","34"};
    String[] views = new String[]{"65","87"};
    String[] title = new String[]{"Residental flat for rental in New Road", "Office flat for rental in Baneshwor"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        this.rentList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Rent rent = new Rent();
            rent.setImage(this.propertImage[i]);
            rent.setDaysLeft(this.daysLeft[i]);
            rent.setDescription(this.description[i]);
            rent.setPrice(this.price[i]);
            rent.setFavs(this.favs[i]);
            rent.setTitle(this.title[i]);
            rent.setUserProfileName(this.profilename[i]);
            rent.setUserProfilePic(this.profilepic[i]);
            rent.setViews(this.views[i]);
            this.rentList.add(rent);
        }

        layoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewPost);
        final RentAdapter rentAdapter = new RentAdapter(getApplicationContext(),this.rentList);
        recyclerView.setAdapter(rentAdapter);
        recyclerView.setLayoutManager(layoutManager);


        /*favImageButton = (ImageButton) findViewById(R.id.postFavImage);
        favImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favImageButton.setImageResource(R.drawable.ic_favorite_black);
            }
        });*/

        searchView = (SearchView) findViewById(R.id.search_bar);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.maindrawer);
        //mDrawerList = (ListView) findViewById(R.id.drawerlist);
        mTitle = getTitle().toString();
        navigationView = (NavigationView) findViewById(R.id.nvView);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //ActionBar myactionbar = (ActionBar) findViewById(R.id.my_actionbar);
        //setSupportActionBar(myactionbar);

        //addDrawerItems();
        setupDrawer(navigationView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        fab_open = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward_plus_icon);
        fab_rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward_plus_icon);


        SearchView searchView = (SearchView) findViewById(R.id.search_bar);
        searchView.setQueryHint("Search by location");


        fab = (FloatingActionButton) findViewById(R.id.fab);
        CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        p.setBehavior(new ScrollAwareFABBehavior(getApplicationContext(),null));
        fab.setLayoutParams(p);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFabOpen){
                    fab.startAnimation(fab_rotate_backward);
                    fab1.startAnimation(fab_close);
                    fab2.startAnimation(fab_close);
                    CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
                    p.setBehavior(new ScrollAwareFABBehavior(getApplicationContext(),null));
                    fab.setLayoutParams(p);
                    fab2.setClickable(false);
                    fab1.setClickable(false);
                    isFabOpen = false;
                }else {
                    fab.startAnimation(fab_rotate_forward);
                    fab1.startAnimation(fab_open);
                    fab2.startAnimation(fab_open);
                    fab1.setClickable(true);
                    fab2.setClickable(true);
                    CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
                    p.setBehavior(null);
                    fab.setLayoutParams(p);
                    isFabOpen = true;
                }


                if(blurred){
                    Blurry.delete((ViewGroup) findViewById(R.id.nestedViewPost));
                }
                else {
                    Blurry.with(getApplicationContext()).radius(15).sampling(2).async().
                            animate(500).onto((ViewGroup) findViewById(R.id.nestedViewPost));
                }
                blurred = !blurred;
            }
        });
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        params.setBehavior(new ScrollAwareFABBehavior(getApplicationContext(),null));
        fab.setLayoutParams(params);


        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewPost.class);
                startActivity(intent);
            }
        });


        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFabOpen){
                    fab.startAnimation(fab_rotate_backward);
                    fab1.startAnimation(fab_close);
                    fab2.startAnimation(fab_close);
                    CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
                    p.setBehavior(new ScrollAwareFABBehavior(getApplicationContext(),null));
                    fab.setLayoutParams(p);
                    fab2.setClickable(false);
                    fab1.setClickable(false);
                    isFabOpen = false;

                }
                if(blurred){
                    Blurry.delete((ViewGroup) findViewById(R.id.nestedViewPost));
                }
                blurred = !blurred;
            }
        });

    }

    /*private void addDrawerItems() {
        mDrawerListName = getResources().getStringArray(R.array.list_drawer_items);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDrawerListName);
        navigationView.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    private void setupDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_closed) {

            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //Blurry.with(getApplicationContext()).radius(15).sampling(2).async()
                  //      .onto((ViewGroup) findViewById(R.id.nestedViewPost));
                //getSupportActionBar().setTitle("Navigation!");
                //fab.setVisibility(View.INVISIBLE);
                //fab.hide();
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /**
             * Called when a drawer has settled in a completely closed state.
             */
            public void onDrawerClosed(View view) {
                Blurry.delete((ViewGroup) findViewById(R.id.nestedViewPost));
                super.onDrawerClosed(view);
                //getSupportActionBar().setTitle(mTitle);
                CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
                p.setBehavior(new ScrollAwareFABBehavior(getApplicationContext(),null));
                fab.setLayoutParams(p);
                fab.setVisibility(View.VISIBLE);
                //fab.show();

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setBackgroundColor(getResources().getColor(R.color.orange));
        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.orange));
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    public void selectDrawerItem(MenuItem menuItem){
        //Create fragment for various item selected
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                final RentAdapter rentAdapter = new RentAdapter(getApplicationContext(),this.rentList);
                recyclerView.setAdapter(rentAdapter);
                recyclerView.setLayoutManager(layoutManager);
                Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_notification:
                Toast.makeText(getApplicationContext(),"Notification",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_favorites:
                final FavouritesAdapter favouritesAdapter = new FavouritesAdapter(getApplicationContext(),this.rentList);
                recyclerView.setAdapter(favouritesAdapter);
                recyclerView.setLayoutManager(layoutManager);

                Toast.makeText(getApplicationContext(),"Favorite",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_myprofile:
                Toast.makeText(getApplicationContext(),"Profile",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_setting:
                Intent intent = new Intent(getApplicationContext(),Setting.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Setting",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                Intent i = new Intent(getApplicationContext(),login.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Logout",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_newpost:
                Intent intent1 = new Intent(getApplicationContext(),NewPost.class);
                startActivity(intent1);
                Toast.makeText(getApplicationContext(),"NewPost",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_rentseeker:
                Toast.makeText(getApplicationContext(),"Viewing as rent seeker!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_owner:
                Toast.makeText(getApplicationContext(),"Viewing as Owner!",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();
        }
        /*try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();*/

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        //setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawerLayout.closeDrawers();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
          //  return true;
       // }

        if (id == R.id.show_marker_map) {
            Intent i = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.show_filter){
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            Blurry.with(getApplicationContext()).radius(15).sampling(2).async()
                        .onto((ViewGroup) findViewById(R.id.nestedViewPost));
            CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            p.setBehavior(null);
            //p.setAnchorId(View.NO_ID);
            fab.setLayoutParams(p);
            fab.setVisibility(View.INVISIBLE);
            //fab.hide();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}