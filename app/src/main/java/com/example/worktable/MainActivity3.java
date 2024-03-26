package com.example.worktable;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    private TableLayout tableLayout;

    public static TableRow row;
    private int rowCount = 4;
    private int colCount = 3;

    private GestureDetector gestureDetector;

    private Button addLine, addColumn, delLine, delColumn;
    public static final int MENU_ADD_COL = R.id.add_column;
    public static final int MENU_ADD_LINE = R.id.add_line;
    public static final int MENU_DEL_COL = R.id.delete_column;
    public static final int MENU_DEL_LINE = R.id.delete_line;

    public static final int merge_column_up = R.id.merge_column_up;
    public static final int merge_column_down = R.id.merge_column_down;
    public static final int merge_line_left = R.id.merge_line_left;
    public static final int merge_line_right = R.id.merge_line_right;

    public static int rowIndex = 0;
    public static int columnIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);



        tableLayout = findViewById(R.id.tableLayout);
        addColumn = findViewById(R.id.addColumn);
        addLine = findViewById(R.id.addLine);
        delLine = findViewById(R.id.delLine);
        delColumn = findViewById(R.id.delColumn);
        populateTable();
        //registerForContextMenu(tableLayout);

        // Создание GestureDetector
        gestureDetector = new GestureDetector(this, new SwipeMain3(this));

        gestureDetector.setOnDoubleTapListener(new DoubleTapListener(this));

        addLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRow();

            }
        });

        addColumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addColumn();
            }
        });

        delLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeRow();
            }
        });

        delColumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeColumn();
            }
        });
    }

    private void populateTable() {
        tableLayout.removeAllViews();
        for (int i = 0; i < rowCount; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < colCount; j++) {
                /*if(i == 0 && j == 0){
                    j++;
                }*/
                TextView textView = new TextView(this);
                textView.setText(j + ", " + i);
                textView.setPadding(10, 10, 10, 10);

                // Добавляем границы для каждой ячейки
                GradientDrawable border = new GradientDrawable();
                border.setStroke(1, Color.BLACK);
                textView.setBackground(border);

                registerForContextMenu(textView);

                tableRow.addView(textView);
            }
            tableLayout.addView(tableRow);
        }

        /*// Добавляем слушатель для каждой ячейки таблицы
        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            View rowView = tableLayout.getChildAt(i);
            if (rowView instanceof TableRow) {
                TableRow row = (TableRow) rowView;
                for (int j = 0; j < row.getChildCount(); j++) {
                    View cellView = row.getChildAt(j);
                    cellView.setOnTouchListener(new DoubleTapListener(this));
                }
            }
        }*/
    }


  /*  @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Передача события касания в GestureDetector
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }*/

    // Обработка двойного нажатия на границы таблицы
   /* @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // Получаем координаты касания
            float x = event.getX();
            float y = event.getY();

            // Находим View, на которую пользователь нажал
            View view = tableLayout.findChildViewUnder(x, y);
            if (view != null) {
                // Определяем границы ячейки, на которую нажал пользователь
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                int left = location[0];
                int right = left + view.getWidth();
                int top = location[1];
                int bottom = top + view.getHeight();

                // Проверяем, находится ли касание на границе таблицы
                if (x > left && x < right && y > top && y < bottom) {
                    // Обработка двойного нажатия на границу таблицы
                    if (event.getEventTime() - event.getDownTime() < ViewConfiguration.getDoubleTapTimeout()) {
                        // Двойное нажатие обнаружено
                        // Здесь можно реализовать логику объединения строк и столбцов
                        // Например, показать диалоговое окно с опциями объединения
                        Toast.makeText(this, "Двойное нажатие на границу таблицы", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
            }
        }
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }*/
    public void addRow() {
        rowCount++;
        Toast.makeText(this, "Добавлена строка", Toast.LENGTH_SHORT).show();
        //populateTable();

        TableRow tableRow = new TableRow(this);
        for (int j = 0; j < colCount; j++) {
            TextView textView = new TextView(this);
            textView.setText(j + ", " );
            textView.setPadding(10, 10, 10, 10);

            // Добавляем границы для каждой ячейки
            GradientDrawable border = new GradientDrawable();
            border.setStroke(1, Color.BLACK);
            textView.setBackground(border);

            registerForContextMenu(textView);

            tableRow.addView(textView);
        }
        tableLayout.addView(tableRow);
    }

    public void addColumn() {
        colCount++;
        Toast.makeText(this, "Добавлен столбец", Toast.LENGTH_SHORT).show();
       // populateTable();

        for(int i = 0; i < rowCount; i++) {
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < 1; j++) {
                TextView textView = new TextView(this);
                textView.setText(j + ", " + i);
                textView.setPadding(10, 10, 10, 10);

                // Добавляем границы для каждой ячейки
                GradientDrawable border = new GradientDrawable();
                border.setStroke(1, Color.BLACK);
                textView.setBackground(border);

               // registerForContextMenu(textView);

                tableRow.addView(textView);
            }
        }

    }

    public void removeRow() {
        if(rowCount == 1){
            return;
        }else {
            rowCount--;
            Toast.makeText(this, "Удалена строка", Toast.LENGTH_SHORT).show();
            //populateTable();

            View rowToRemove = tableLayout.getChildAt(rowCount);
            tableLayout.removeView(rowToRemove);
        }
    }

    public void removeColumn() {
        if (colCount == 1) {
            return;
        } else {
            colCount--;
            Toast.makeText(this, "Удален столбец", Toast.LENGTH_SHORT).show();
            // populateTable();

            for(int i = 0; i < rowCount; i++) {
                TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
                for (int j = 0; j < 1; j++) {
                    /*TextView textView = new TextView(this);
                    textView.setText(j + ", " + i);
                    textView.setPadding(10, 10, 10, 10);

                    // Добавляем границы для каждой ячейки
                    GradientDrawable border = new GradientDrawable();
                    border.setStroke(1, Color.BLACK);
                    textView.setBackground(border);

                    registerForContextMenu(textView);*/

                    View columnToRemove = tableRow.getChildAt(colCount);
                    tableRow.removeView(columnToRemove);
                }
            }
        }

    }

    // Создание контекстного меню
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v instanceof TextView) {
            TextView textView = (TextView) v;
            row = (TableRow) textView.getParent();
            TableLayout tableLayout = (TableLayout) row.getParent();

            // Определяем индексы строки и столбца
            rowIndex = tableLayout.indexOfChild(row);
            columnIndex = row.indexOfChild(textView);

            // Используйте rowIndex и columnIndex по вашему усмотрению
            // Например, добавьте информацию в контекстное меню
            menu.setHeaderTitle("Row: " + rowIndex + ", Column: " + columnIndex);
            getMenuInflater().inflate(R.menu.table_menu, menu);
        }
    }

    // Обработка выбора пункта контекстного меню
    //@SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == merge_column_up) {
            mergeColumnUp(tableLayout);
            return true;
        } else if (itemId == merge_column_down) {
            mergeColumnDown(tableLayout);
            return true;
        } else if (itemId == merge_line_left) {
            mergeRowToLeft(row);
            return true;
        } else if (itemId == merge_line_right) {
            mergeRowToRight(row);
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    // Ваш код для объединения строки слева
    private void mergeRowToLeft(TableRow tableRow) {
        if (columnIndex > 0) { // Проверяем, что у нас есть ячейка слева
            TextView currentCell = (TextView) tableRow.getChildAt(columnIndex);
            TextView leftCell = (TextView) tableRow.getChildAt(columnIndex - 1);

            // Получаем LayoutParams для текущей ячейки
            TableRow.LayoutParams params = (TableRow.LayoutParams) currentCell.getLayoutParams();

            // Увеличиваем параметр span на 1, чтобы объединить текущую ячейку с ячейкой слева
            params.span++;

            // Удаляем ячейку слева из родительской строки
            tableRow.removeView(leftCell);

            // Обновляем параметры текущей ячейки
            currentCell.setLayoutParams(params);
        }
    }

    // Ваш код для объединения строки справа
    private void mergeRowToRight(TableRow tableRow) {
        int childCount = tableRow.getChildCount();

        if (columnIndex < childCount - 1) { // Проверяем, что у нас есть ячейка справа
            TextView currentCell = (TextView) tableRow.getChildAt(columnIndex);
            TextView rightCell = (TextView) tableRow.getChildAt(columnIndex + 1);

            // Получаем LayoutParams для текущей ячейки
            TableRow.LayoutParams params = (TableRow.LayoutParams) currentCell.getLayoutParams();

            // Увеличиваем параметр span на 1, чтобы объединить текущую ячейку с ячейкой справа
            params.span++;

            // Удаляем ячейку справа из родительской строки
            tableRow.removeView(rightCell);

            // Обновляем параметры текущей ячейки
            currentCell.setLayoutParams(params);
        }
    }

    // Метод для объединения столбца вверху
    private void mergeColumnUp(TableLayout tableLayout) {
        if (rowIndex > 0) { // Проверяем, что у нас есть строка сверху
            TableRow currentRow = (TableRow) tableLayout.getChildAt(rowIndex);
            TableRow upperRow = (TableRow) tableLayout.getChildAt(rowIndex - 1);

            View currentCell = currentRow.getChildAt(columnIndex);
            View upperCell = upperRow.getChildAt(columnIndex);

            // Увеличиваем параметр layout_span на 1, чтобы объединить текущий столбец со столбцом сверху
            if (currentCell instanceof TableLayout && upperCell instanceof TableLayout) {
                TableRow.LayoutParams params = (TableRow.LayoutParams) currentCell.getLayoutParams();
                params.span++;
                currentCell.setLayoutParams(params);

                // Удаляем верхний TableLayout из родительской строки
                upperRow.removeView(upperCell);
            }
        }
    }

    // Метод для объединения столбца внизу
    private void mergeColumnDown(TableLayout tableLayout) {
        int rowCount = tableLayout.getChildCount();

        if (rowIndex < rowCount - 1) { // Проверяем, что у нас есть строка снизу
            TableRow currentRow = (TableRow) tableLayout.getChildAt(rowIndex);
            TableRow lowerRow = (TableRow) tableLayout.getChildAt(rowIndex + 1);

            View currentCell = currentRow.getChildAt(columnIndex);
            View lowerCell = lowerRow.getChildAt(columnIndex);
            /*Toast.makeText(this, "Добавлена строка", Toast.LENGTH_SHORT).show();*/
            // Увеличиваем параметр layout_span на 1, чтобы объединить текущий столбец со столбцом снизу
            if (currentCell instanceof TableLayout && lowerCell instanceof TableLayout) {
                TableRow.LayoutParams params = (TableRow.LayoutParams) currentCell.getLayoutParams();
                params.span++;
                currentCell.setLayoutParams(params);
                Toast.makeText(this, "Добавлена строка", Toast.LENGTH_SHORT).show();
                // Удаляем нижний TableLayout из родительской строки
                lowerRow.removeView(lowerCell);
            }
        }
    }



}