package com.example.practicalfive;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The second screen of the Chinese Municipalities application. It reads the
 * municipality name passed from {@link MainActivity}, re-validates it with
 * {@link TextProcessor}, and then customises its content and appearance for
 * that municipality: the name, image, information summary, colours and the
 * official website opened by the Visit site button.
 */
public class Municipality extends AppCompatActivity {

    private String municipalityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        TextView textViewName = findViewById(R.id.textViewName);
        ImageView imageViewMunicipality = findViewById(R.id.imageViewMunicipality);
        TextView textViewInfo = findViewById(R.id.textViewInfo);
        Button buttonVisitSite = findViewById(R.id.buttonVisitSite);

        // Retrieve and re-validate the name passed from MainActivity.
        String extra = getIntent().getStringExtra(TextProcessor.EXTRA_MUNICIPALITY);
        municipalityName = TextProcessor.processMunicipality(extra);

        if (municipalityName == null) {
            // Missing or invalid data: handle it safely and close the screen.
            Toast.makeText(this, R.string.error_no_data, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        String[] summaries = getResources().getStringArray(R.array.municipalities_array);
        int index = getSummaryIndex(municipalityName);
        int colour = getColourResource(municipalityName);
        int imageResource = getImageResource(municipalityName);

        // Name: correct capitalisation, coloured background, light text.
        textViewName.setText(municipalityName);
        textViewName.setBackgroundColor(getColor(colour));
        textViewName.setTextColor(getColor(R.color.color_text_light));

        // Image: the corresponding municipality photo.
        imageViewMunicipality.setImageResource(imageResource);

        // Information: the matching summary from the string array.
        textViewInfo.setText(summaries[index]);
        textViewInfo.setTextColor(getColor(colour));

        // Visit site button: same colour as the municipality theme.
        buttonVisitSite.setBackgroundColor(getColor(colour));
        buttonVisitSite.setOnClickListener(v -> openWebsite());
    }

    /**
     * Opens the official website of the displayed municipality using an
     * implicit ACTION_VIEW Intent, handled by a suitable application.
     */
    private void openWebsite() {
        Uri website = getWebsite(municipalityName);
        Intent intent = new Intent(Intent.ACTION_VIEW, website);

        // Handle the case where no suitable application is available.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.error_no_browser, Toast.LENGTH_SHORT).show();
        }
    }

    /** Index into municipalities_array (0=Beijing, 1=Shanghai, 2=Chongqing, 3=Tianjin). */
    private int getSummaryIndex(String name) {
        switch (name) {
            case "Beijing":
                return 0;
            case "Shanghai":
                return 1;
            case "Chongqing":
                return 2;
            default:
                return 3; // Tianjin
        }
    }

    /** Named colour resource used for the name, text and button backgrounds. */
    private int getColourResource(String name) {
        switch (name) {
            case "Beijing":
                return R.color.municipality_beijing;
            case "Shanghai":
                return R.color.municipality_shanghai;
            case "Chongqing":
                return R.color.municipality_chongqing;
            default:
                return R.color.municipality_tianjin;
        }
    }

    /** Drawable resource showing the selected municipality. */
    private int getImageResource(String name) {
        switch (name) {
            case "Beijing":
                return R.drawable.beijing;
            case "Shanghai":
                return R.drawable.shanghai;
            case "Chongqing":
                return R.drawable.chongqing;
            default:
                return R.drawable.tianjin;
        }
    }

    /** Official website of the selected municipality. */
    private Uri getWebsite(String name) {
        switch (name) {
            case "Beijing":
                return Uri.parse("https://english.beijing.gov.cn/");
            case "Shanghai":
                return Uri.parse("https://english.shanghai.gov.cn/");
            case "Chongqing":
                return Uri.parse("https://english.cq.gov.cn/");
            default:
                return Uri.parse("https://en.tj.gov.cn/");
        }
    }
}
