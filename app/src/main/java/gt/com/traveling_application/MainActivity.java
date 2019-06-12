package gt.com.traveling_application;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;
import gt.com.traveling_application.adapter.StoreItemsAdapter;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
    DiscreteScrollView.OnItemChangedListener, View.OnClickListener {

  private TextView mTextMessage;
  ViewPager viewPager;
  Context activity = MainActivity.this;
  StoreItems storeItems;
  List<Item> data;
  InfiniteScrollAdapter infiniteAdapter;
  DiscreteScrollView itemPicker;
  TextView currentItemName;
  ImageView imageView;
  StoreItemsAdapter storeItemsAdapter;


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

    storeItems = StoreItems.get();
    currentItemName = findViewById(R.id.itemName);
    itemPicker = findViewById(R.id.picker);
    data = storeItems.getData();
    imageView = findViewById(R.id.image);
    storeItemsAdapter = new StoreItemsAdapter(data);
    infiniteAdapter = InfiniteScrollAdapter.wrap(storeItemsAdapter);
    itemPicker.setAdapter(infiniteAdapter);
    itemPicker.setOffscreenItems(2);
    itemPicker.setItemTransformer(new ScaleTransformer.Builder()
        .setMinScale(0.8f)
        .build());

    itemPicker.setSlideOnFling(true);

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


  @Override
  public void onClick(View v) {

  }

  @Override
  public void onCurrentItemChanged(@Nullable ViewHolder viewHolder, int adapterPosition) {
    int positionInDataSet = infiniteAdapter.getRealPosition(adapterPosition);
    Toast.makeText(this, String.valueOf(positionInDataSet), Toast.LENGTH_SHORT).show();

  }

  @Override
  public void onPointerCaptureChanged(boolean hasCapture) {

  }
}



