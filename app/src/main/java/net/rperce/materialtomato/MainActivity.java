package net.rperce.materialtomato;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File listFile = new File(getApplicationContext().getFilesDir(), "list.json");
        ArrayList<String> listArray = new ArrayList<>();
        String[] arrayTest = new String[] {"Foo", "Bar", "FizzBuzz","Over","flow","the","screen","plz","for","testing","purposes","okay","plz","now"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.todo_layout, R.id.todo_text, arrayTest);
        ListView view = (ListView)findViewById(R.id.todoList);
        view.setAdapter(adapter);
    }

    private void addDummyListItem(ArrayAdapter<String> list) {
        list.add("New list item");
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
