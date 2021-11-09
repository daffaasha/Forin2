package com.example.forin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forin.adapter.ListFoodAdapter;
import com.example.forin.datamodel.Food;
import com.example.forin.datamodel.FoodData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvFoods;
    private ArrayList<Food> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnPesan = findViewById(R.id.btn_pesan);

        rvFoods = findViewById(R.id.rv_foods);
        rvFoods.setHasFixedSize(true);

        foodList.addAll(FoodData.getListData());
        showFoodList();

        if (!ListFoodAdapter.ListViewHolder.isExceed) {
            btnPesan.setOnClickListener(v -> {
                    Intent moveToPesanan = new Intent(MainActivity.this, PesananActivity.class);
                    moveToPesanan.putParcelableArrayListExtra(PesananActivity.EXTRA_ITEM, foodList);
                    startActivity(moveToPesanan);
            });
        }
    }

    private void showFoodList() {
        rvFoods.setLayoutManager(new LinearLayoutManager(this));
        ListFoodAdapter listFoodAdapter = new ListFoodAdapter(foodList);
        rvFoods.setAdapter(listFoodAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode (int selectedMode) {
        switch (selectedMode) {
            case R.id.action_admin:
                break;
        }
    }
}