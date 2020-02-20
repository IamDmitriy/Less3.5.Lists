package com.example.lesslists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main();
    }

    private void main() {
        List<Map<String, String>> content = new ArrayList<>();

        Map<String, String> firstRowMap = new HashMap<>();
        firstRowMap.put("left", "a long");
        firstRowMap.put("right", "time ago");
        content.add(firstRowMap);
        Map<String, String> secondRowMap = new HashMap<>();
        secondRowMap.put("left", "in a galaxy");
        secondRowMap.put("right", "far, far away");
        content.add(secondRowMap);

        final SimpleAdapter adapter = new SimpleAdapter(this, content, R.layout.list_item,
                new String[]{ "left", "right" }, new int[]{ R.id.left_text, R.id.right_text });

/*        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                return false;
            }
        });*/

        ListView listView = findViewById(R.id.list_view);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Ткнули на " + position + " пункт",
                        Toast.LENGTH_SHORT).show();
            }
        });

//Выдаёт ошибку
/*        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Ткнули) - обработчик обычной вьюшки",
                        Toast.LENGTH_SHORT).show();
            }
        });*/

        swipeLayout = findViewById(R.id.swiperefresh);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            // Будет вызван, когда пользователь потянет список вниз
            @Override
            public void onRefresh() {
                Toast.makeText(MainActivity.this, "Обновление",
                        Toast.LENGTH_SHORT).show();
                swipeLayout.setRefreshing(false);
            }
        });
    }


}
