package com.example.worktable;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    // Набор цветов
    private int[] colors = {
            Color.parseColor("#FF5733"), // Оранжевый
            Color.parseColor("#FFC300"), // Желтый
            Color.parseColor("#C70039"), // Красный
            Color.parseColor("#900C3F"), // Розовый
            Color.parseColor("#FF3F51B5"), // Синий (повтор)
            Color.parseColor("#FF009688")  // Зеленый (повтор)
    };

    private GestureDetector gestureDetector;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        // Создание GestureDetector
        gestureDetector = new GestureDetector(this, new SwipeGestureListener(this));

        // Создание 4 строк и 6 столбцов
        for (int i = 0; i < 6; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < 4; j++) {
                TextView textView = new TextView(this);
                textView.setText("Row " + j + ", Col " + i);
                textView.setPadding(10, 10, 10, 10);

                // Используем цвета из массива colors
                //textView.setBackgroundColor(colors[i]);

                // Добавляем границы для каждой ячейки
                GradientDrawable border = new GradientDrawable();
                border.setColor(colors[i]);
                border.setStroke(1, Color.BLACK);
                textView.setBackground(border);

                tableRow.addView(textView);
            }
            tableLayout.addView(tableRow);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Передача события касания в GestureDetector
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }
}
