package com.example.practicalfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The main screen of the Chinese Municipalities application. The user types the
 * name of a municipality and presses Enter. The input is validated by
 * {@link TextProcessor}; if it is valid an explicit Intent opens
 * {@link Municipality}, otherwise an error message is shown.
 */
public class MainActivity extends AppCompatActivity {

    private EditText editTextMunicipality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMunicipality = findViewById(R.id.editTextMunicipality);

        // React to the "Done" action on the keyboard.
        editTextMunicipality.setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                submit();
                return true;
            }
            return false;
        });
    }

    /**
     * Reads the input, validates and normalises it with TextProcessor and, if
     * valid, launches the Municipality screen passing the name as an extra.
     */
    private void submit() {
        String rawInput = editTextMunicipality.getText().toString();
        String municipality = TextProcessor.processMunicipality(rawInput);

        if (municipality == null) {
            // Distinguish an empty/whitespace input from an unsupported name.
            String message = rawInput.trim().isEmpty()
                    ? getString(R.string.error_empty)
                    : getString(R.string.error_invalid);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(MainActivity.this, Municipality.class);
        intent.putExtra(TextProcessor.EXTRA_MUNICIPALITY, municipality);
        startActivity(intent);
    }
}
