package com.example.worktable;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TableLayout tableLayout = findViewById(R.id.tableRedactor);

        // Создание GestureDetector
        gestureDetector = new GestureDetector(this, new SwipeMain2(this));

        // Создание 4 строк и 6 столбцов
        for (int i = 0; i < 6; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < 4; j++) {
                EditText editText = new EditText(this);
                editText.setHint("Введите текст");
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                editText.setPadding(10, 10, 10, 10);

                // Устанавливаем размеры EditText
                TableRow.LayoutParams params = new TableRow.LayoutParams(
                        0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                editText.setLayoutParams(params);

                // Добавляем границы для каждой ячейки
                GradientDrawable border = new GradientDrawable();
                border.setStroke(1, Color.BLACK);
                editText.setBackground(border);

                tableRow.addView(editText);
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