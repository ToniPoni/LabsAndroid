package learning.labwork2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sayHelloClick(View view) {
        EditText editName = (EditText)findViewById(R.id.editName);
        TextView textViewHello = (TextView)findViewById(R.id.textViewHello);

        String helloText = String.format("Здравствуйте, %s!", editName.getText());
        textViewHello.setText( helloText );
    }

    public void aboutProgramm(View view) {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }
}
