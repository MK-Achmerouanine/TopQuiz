package orange.odc.testapp;

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
    private Button mainButton;
    private User modelUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modelUser = new User("");

        messageTextView = findViewById(R.id.main_textview_greeting);
        EditText inputNameEditText = findViewById(R.id.main_edittext_name);
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
                    messageTextView.setText(R.string.main_textview_success);
                } else {
                    messageTextView.setText(t);
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
                // Quand on click sur le boutton
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameActivityIntent);
            }
        });
    }
}