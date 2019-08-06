package app.flaminius.flaminius2k19;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jaiselrahman.hintspinner.HintSpinner;
import com.jaiselrahman.hintspinner.HintSpinnerAdapter;
import com.ramotion.fluidslider.FluidSlider;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        HintSpinner department = findViewById(R.id.department);
        List<String> departments = new ArrayList<>();
        departments.add("B.Tech IT");
        departments.add("B.E. CSE");
        department.setAdapter(new HintSpinnerAdapter<>(this, departments, "Select Department"));

        HintSpinner year = findViewById(R.id.year);
        List<String> years = new ArrayList<>();
        years.add("2nd year");
        years.add("3rd year");
        years.add("4th year");
        years.add("5th year");
        year.setAdapter(new HintSpinnerAdapter<>(this, years, "Select Year"));

        HintSpinner foodType = findViewById(R.id.foodType);
        List<String> foodTypes = new ArrayList<>();
        foodTypes.add("Veg");
        foodTypes.add("Non Veg");
        foodType.setAdapter(new HintSpinnerAdapter<>(this, foodTypes, "Veg./Non. Veg."));

        final int min = 1;
        final int max = 10;
        final int total = max - min;

        final FluidSlider slider = findViewById(R.id.count);
        slider.setPositionListener(new Function1<Float, Unit>() {
            @Override
            public Unit invoke(Float position) {
                int count = (int) (min + (total * position));
                slider.setBubbleText(String.valueOf(count));
                return null;
            }
        });
    }
}
