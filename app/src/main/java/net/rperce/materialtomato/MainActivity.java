package net.rperce.materialtomato;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MaterialTomato/Main";

    private void errLog(String msg, Throwable thr) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        Log.e(TAG, msg, thr);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BufferedReader br;
        final ArrayList<String> listArray = new ArrayList<>();
        try {
            File listFile = new File(getApplicationContext().getFilesDir(), "list.json");
            try {
                if (!listFile.exists()) {
                    boolean success = listFile.createNewFile();
                    if (!success) throw new IOException("File didn't exist, but then did");
                }
            } catch(IOException ioe) {
                errLog("Error creating list file", ioe);
            }
            br = new BufferedReader(new FileReader(listFile));

            String line;
            while ((line = br.readLine()) != null) {
                listArray.add(line);
            }
            try {
                br.close();
            } catch(IOException ioe) {
                errLog("Error closing list file reader", ioe);
            }
        } catch(IOException ioe) {
            errLog("Error reading list file", ioe);
        }
        // TODO KILL
        listArray.add("testing drr");

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.todo_layout, R.id.todo_text, listArray);

        ListView view = (ListView)findViewById(R.id.todoList);
        view.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        view.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.new_item_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListableEditText newText = (ListableEditText) findViewById(R.id.new_item_text);
                if (newText.getCurrentTextColor() == getResources().getColor(R.color.textColorPrimaryLight)
                    || newText.getText().toString().equals(""))
                    return;
                adapter.add(newText.getText().toString());
                adapter.notifyDataSetChanged();

                newText.setText("");
            }
        });

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "view", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.fab).requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
