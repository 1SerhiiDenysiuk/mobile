package com.example.mobiletasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class EquipmentDetailsActivity extends AppCompatActivity {

    private TextView equipmentType;
    private TextView equipmentName;
    private TextView price;
    private TextView workingArea;
    private TextView description;
    private ImageView imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_details);

        getIncomingIntent();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("equipment_type") &&
                getIntent().hasExtra("equipment_name") &&
                getIntent().hasExtra("price") &&
                getIntent().hasExtra("working_area") &&
                getIntent().hasExtra("description")) {
            String equipmentTypeInfo = getIntent().getStringExtra("equipment_type");
            String equipmentNameInfo = getIntent().getStringExtra("equipment_name");
            String priceInfo = getIntent().getStringExtra("price");
            String workingAreaInfo = getIntent().getStringExtra("working_area");
            String descriptionInfo = getIntent().getStringExtra("description");
            String imageUrlInfo = getIntent().getStringExtra("image");

            setInfo(equipmentTypeInfo, equipmentNameInfo, priceInfo, workingAreaInfo, descriptionInfo, imageUrlInfo);
        }
    }

    private void initViews(){
        equipmentType = findViewById(R.id.equipment_details_type);
        equipmentName = findViewById(R.id.equipment_details_name);
        price = findViewById(R.id.equipment_details_price);
        workingArea = findViewById(R.id.equipment_details_working_area);
        description = findViewById(R.id.equipment_details_description);
        imageUrl = findViewById(R.id.equipment_details_image_view);
    }

    private void setInfo(String equipmentTypeInfo, String equipmentNameInfo,
                         String priceInfo, String workingAreaInfo,
                         String descriptionInfo, String imageUrlInfo) {
        initViews();
        equipmentType.setText(equipmentTypeInfo);
        equipmentName.setText(equipmentNameInfo);
        price.setText(priceInfo);
        workingArea.setText(workingAreaInfo);
        description.setText(descriptionInfo);
        Picasso.get().load(imageUrlInfo).into(imageUrl);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(EquipmentDetailsActivity.this, SignIn.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
