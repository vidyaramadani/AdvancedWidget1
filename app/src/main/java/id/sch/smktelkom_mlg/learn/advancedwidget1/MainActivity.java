package id.sch.smktelkom_mlg.learn.advancedwidget1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner spjumlah;
    LinearLayout llanak;
    TextView tvhasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spjumlah = (Spinner) findViewById(R.id.spinnerjumlahanak);
        Integer[] arjumlah = new Integer[10];
        for (int i = 0; i < 10; i++) {
            arjumlah[i] = i + 1;
        }
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arjumlah);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spjumlah.setAdapter(adapter);

        llanak = (LinearLayout) findViewById(R.id.linearlayoutanak);
        tvhasil = (TextView) findViewById(R.id.textviewhasil);

        findViewById(R.id.buttonproses).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doProses();
            }
        });
        spjumlah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                addEditText((int) spjumlah.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });
    }

    private void addEditText(int jumlah) {
        llanak.removeAllViews();
        for (int i = 1; i <= jumlah; i++) {
            View v = LayoutInflater.from(this).inflate(R.layout.layout_anak, llanak, false);
            v.setTag("Anak" + i);
            llanak.addView(v);
        }

    }

    private void doProses() {
        int jumlah = (int) spjumlah.getSelectedItem();
        String hasil = "";
        for (int i = 1; i <= jumlah; i++) {
            LinearLayout llNow = (LinearLayout) llanak.findViewWithTag("Anak" + i);

            EditText etNama = (EditText) llNow.findViewById(R.id.edittextnama);
            EditText etUmur = (EditText) llNow.findViewById(R.id.edittextumur);

            String nama = etNama.getText().toString().trim();
            String umur = etUmur.getText().toString();

            if (umur.isEmpty())
                umur = "0";
            if (!nama.isEmpty())
                hasil += "Anak ke-" + i + ": " + nama + " umur " + umur + " tahun\n";
        }

        tvhasil.setText(hasil);
    }
}
