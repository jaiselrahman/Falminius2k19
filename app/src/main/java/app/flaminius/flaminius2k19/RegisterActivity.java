package app.flaminius.flaminius2k19;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.PatternsCompat;

import com.jaiselrahman.hintspinner.HintSpinner;
import com.jaiselrahman.hintspinner.HintSpinnerAdapter;
import com.ramotion.directselect.DSListView;
import com.ramotion.fluidslider.FluidSlider;

import java.util.regex.Pattern;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class RegisterActivity extends AppCompatActivity {
    private EditText name, email, phone, college;
    private DSListView eventList;

    private String department, foodPref;

    private int personCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        college = findViewById(R.id.college);

        setUpDepartmentSpinner();

        setUpFoodPrefSpinner();

        setUpPersonCountSlider();

        eventList = findViewById(R.id.event_list);
    }

    private void setUpFoodPrefSpinner() {
        final String[] foodPrefs = getResources().getStringArray(R.array.food_prefs);

        HintSpinner foodPrefSpinner = findViewById(R.id.foodPrefs);
        foodPrefSpinner.setAdapter(new HintSpinnerAdapter<>(this, foodPrefs, "Veg./Non. Veg."));
        foodPrefSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                RegisterActivity.this.foodPref = foodPrefs[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void setUpPersonCountSlider() {
        findViewById(R.id.person_count_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(RegisterActivity.this)
                        .setMessage(R.string.person_count_info)
                        .setPositiveButton(android.R.string.ok, null)
                        .show();
            }
        });

        final int min = 1;
        final int max = 5;
        final int total = max - min;

        final FluidSlider slider = findViewById(R.id.count);
        slider.setPositionListener(new Function1<Float, Unit>() {
            @Override
            public Unit invoke(Float position) {
                personCount = (int) (min + (total * position));
                slider.setBubbleText(String.valueOf(personCount));
                return null;
            }
        });
    }

    private void setUpDepartmentSpinner() {
        final String[] departments = getResources().getStringArray(R.array.departments);

        HintSpinner departmentSpinner = findViewById(R.id.department);
        departmentSpinner.setAdapter(new HintSpinnerAdapter<>(this, departments, "Select Department"));
        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                RegisterActivity.this.department = departments[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void onRegisterClick(View view) {
        if (!validate()) return;

    }

    private boolean validate() {
        String name = this.name.getText().toString();
        if (TextUtils.isEmpty(name)) {
            this.name.setError(getString(R.string.error_enter_name));
            return false;
        } else {
            this.name.setError(null);
        }

        String email = this.email.getText().toString();
        if (TextUtils.isEmpty(email) || !PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            this.email.setError(getString(R.string.error_enter_email));
            return false;
        } else {
            this.email.setError(null);
        }

        String phone = this.phone.getText().toString();
        if (TextUtils.isEmpty(phone) || phone.length() < 10 || phone.length() > 13 || !Patterns.PHONE.matcher(phone).matches()) {
            this.phone.setError(getString(R.string.error_enter_phone));
            return false;
        } else {
            this.phone.setError(null);
        }

        String college = this.college.getText().toString();
        if (TextUtils.isEmpty(college)) {
            this.college.setError(getString(R.string.error_enter_college));
            return false;
        } else {
            this.college.setError(null);
        }

        if (TextUtils.isEmpty(department)) {
            Toast.makeText(this, R.string.error_select_valid_department, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (personCount <= 0 || personCount > 5) {
            Toast.makeText(this, R.string.error_select_valid_person_count, Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(foodPref)) {
            Toast.makeText(this, R.string.error_select_valid_food_pref, Toast.LENGTH_SHORT).show();
            return false;
        }

        eventList.getSelectedItem();
        return true;
    }
}
