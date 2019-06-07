package gt.com.traveling_application;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import gt.com.traveling_application.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

  private TextView mTextMessage;
  ViewPager viewPager;
  Context activity = MainActivity.this;
  LinearLayout sliderDotspanel;
  private int dotscount;
  private ImageView[] dots;

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_home:

          return true;
        case R.id.navigation_dashboard:

          return true;
        case R.id.navigation_notifications:

          return true;
      }
      return false;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mTextMessage = (TextView) findViewById(R.id.message);
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    viewPager = (ViewPager) findViewById(R.id.viewPager);

    sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

    viewPager.setAdapter(viewPagerAdapter);

    dotscount = viewPagerAdapter.getCount();
    dots = new ImageView[dotscount];

    for(int i = 0; i < dotscount; i++){

      dots[i] = new ImageView(this);
      dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

      params.setMargins(8, 0, 8, 0);

      sliderDotspanel.addView(dots[i], params);

    }

    dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {

        for(int i = 0; i< dotscount; i++){
          dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
        }

        dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });


  }



  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    switch (id){

      default:
        return super.onOptionsItemSelected(item);
    }
  }


}



