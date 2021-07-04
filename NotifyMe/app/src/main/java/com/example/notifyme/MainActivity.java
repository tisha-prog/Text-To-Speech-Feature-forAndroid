package com.example.notifyme;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText txt;
    Button b;
    String s;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (EditText)findViewById(R.id.input);
        b = (Button)findViewById(R.id.button);
    }

    public void notify(View view) {//for creating tts activity
        tts = new TextToSpeech(getApplicationContext(), status -> {
            s = txt.getText().toString();
            if (status == TextToSpeech.SUCCESS) {
                int result=tts.setLanguage(Locale.getDefault());
                if (result == TextToSpeech.LANG_MISSING_DATA
                        || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("error", "This Language is not supported");
                }
                tts.setSpeechRate(1.0f);
                tts.speak(s, TextToSpeech.QUEUE_FLUSH, null, null);
                Toast.makeText(getApplicationContext(),"Here it is",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Something unexpected happened", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub

        if (tts != null) {

            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }

}