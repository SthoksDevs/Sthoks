package com.sthoksdevs.sthoks.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.sthoksdevs.sthoks.R;

public class AboutActivity extends AppCompatActivity {
    CardView licencesCard;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_about);
        this.licencesCard = (CardView) findViewById(R.id.open_source_card);
        this.licencesCard.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                AboutActivity.this.startActivity(new Intent(AboutActivity.this, LicenceDetailsActivity.class));
                AboutActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
