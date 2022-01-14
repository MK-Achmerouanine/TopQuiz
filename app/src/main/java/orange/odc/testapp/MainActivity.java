package orange.odc.testapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

import orange.odc.testapp.model.Question;
import orange.odc.testapp.model.QuestionBank;
import orange.odc.testapp.model.User;

public class MainActivity extends AppCompatActivity {
    private TextView messageTextView;
    private EditText inputNameEditText;
    private Button mainButton;
    private User modelUser;

    private static final  int REQUEST_GAME_ACTIVITY_REQUEST_CODE = 42;
    private static final  String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    private static final  String SHARED_PREF_USER_INFO_NAME = "SHARED_PREF_USER_INFO_NAME";
    private static final  String SHARED_PREF_USER_INFO_SCORE = "SHARED_PREF_USER_INFO_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modelUser = new User("");

        messageTextView = findViewById(R.id.main_textview_greeting);
        inputNameEditText = findViewById(R.id.main_edittext_name);
        mainButton = findViewById(R.id.main_button_start);
        String t = messageTextView.getText().toString();
        // Désactiver le boutton
        mainButton.setEnabled(false);
        // Changer l'état du boutton par rapport à la valeur de l'input
        inputNameEditText.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()) {
                    mainButton.setEnabled(true);
                    //messageTextView.setText(R.string.main_textview_success);
                } else {
                    //messageTextView.setText(t);
                    mainButton.setEnabled(false);
                }
            }
        });
        // Changer d'activité lors du click sur le boutton
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Modifier le nom du User
                modelUser.setNomComplet(inputNameEditText.getText().toString());
                getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                        .edit()
                        .putString(SHARED_PREF_USER_INFO_NAME, modelUser.getNomComplet())
                        .apply();
                // Quand on click sur le boutton
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                //startActivity(gameActivityIntent);
                startActivityForResult(gameActivityIntent, REQUEST_GAME_ACTIVITY_REQUEST_CODE);
            }
        });
        greetUser();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean check = REQUEST_GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode && data != null;
        if(check){
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                    .edit()
                    .putInt(SHARED_PREF_USER_INFO_SCORE, score)
                    .apply();
            greetUser();
        }
    }

    private void greetUser(){
        String prefNomComplet = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                .getString(SHARED_PREF_USER_INFO_NAME, null);
        int prefScore = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                .getInt(SHARED_PREF_USER_INFO_SCORE, -1);

        if(prefNomComplet !=null){
            if (prefScore != -1){
                messageTextView.setText(getString(R.string.welcome_back_with_score, prefNomComplet, prefScore));
            }else {
                messageTextView.setText(getString(R.string.welcome_back, prefNomComplet));
            }
            inputNameEditText.setText(prefNomComplet);
        }
    }
}