// Homework02
// Priyanka Manusanipally- 801017222
// Sai Spandana Nakireddy- 801023658


package com.example.spandananakkireddy.hw2_group32;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvtitle;
    TextView tvtaskdate;
    TextView tvtasktime;
    TextView tvtaskpriority;
    TextView tvnum;
    TextView tvnum2;
    ImageView imlast;
    ImageView imback;
    ImageView imedit, imdelete, imone, imfor, imcreate;
    Task task;
    static int reqcode = 100;
    static int reqcode_edit = 101;
    static String Main_Key = "maintask";
    String mtitle,mdate,mtime,mpriority;
    LinkedList<Task> tasklist = new LinkedList<Task>();
    int i;
    static int a;
    static int b;
    int flag = tasklist.size();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("View Tasks");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.todo);

        tvtitle = (TextView) findViewById(R.id.tvtitle);
        tvtaskdate = (TextView) findViewById(R.id.tvtaskdate);
        tvtasktime = (TextView) findViewById(R.id.tvtasktime);
        tvtaskpriority = (TextView) findViewById(R.id.tvtaskpriority);
        tvnum = (TextView) findViewById(R.id.tvnum);
        tvnum2 = (TextView) findViewById(R.id.tvnum2);
        imlast = (ImageView) findViewById(R.id.imlast);
        imlast.setOnClickListener(this);
        imback = (ImageView) findViewById(R.id.imback);
        imback.setOnClickListener(this);
        imedit = (ImageView) findViewById(R.id.imedit);
        imedit.setOnClickListener(this);
        imdelete = (ImageView) findViewById(R.id.imdelete);
        imdelete.setOnClickListener(this);
        imone = (ImageView) findViewById(R.id.imone);
        imone.setOnClickListener(this);
        imfor = (ImageView) findViewById(R.id.imfor);
        imfor.setOnClickListener(this);
        imcreate = (ImageView) findViewById(R.id.imcreate);
        imcreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int length = tasklist.size();
        if (v.getId() == R.id.imcreate) {
            Intent intent = new Intent(MainActivity.this, CreateTaskActivity.class);
            startActivityForResult(intent, reqcode);

        }
        if (v.getId() == R.id.imlast)
            try {
                if (tvnum.getText().toString().equals("")) {
                    Toast.makeText(this, "No tasks", Toast.LENGTH_SHORT).show();
                } else if (!tvnum.getText().toString().equals("")) {
                    tvnum.setText("1");
                    flag = 0;
                    setFields(flag);
                    if (tvnum2.getText().toString().equals("")) {
                        Toast.makeText(MainActivity.this, "Last Task", Toast.LENGTH_SHORT).show();
                    }

                }
            } catch (Exception e) {

            }
        if (v.getId() == R.id.imback) {
            try {
                if (tvnum.getText().toString().equals("")) {
                    Toast.makeText(this, "No tasks", Toast.LENGTH_SHORT).show();
                } else {

                    a = Integer.parseInt(tvnum.getText().toString());
                    if (a > 1 && a <= tasklist.size()) {
                        a = a - 1;
                        tvnum.setText(a + "");

                        Log.d("demo", "in loop" + a);
                        Log.d("demo", "in bCK TVNUM=" + tvnum + " a = " + a + "le" + length);
                        Log.d("len", length + "");
                        if (flag > 0 && flag <= length - 1) {
                            Log.d("demo", "in bCK aftr parse TVNUM=" + tvnum + " a = " + a);
                            flag = flag - 1;
                            setFields(flag);
                            //tvnum.setText(flag+1);
                            Log.d("demo", "in flag");

                        } else
                            Toast.makeText(getApplicationContext(), "Current task is the first task", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getApplicationContext(), "Current task is the first task", Toast.LENGTH_LONG).show();

                }
            }catch (Exception e) {

            }
        }
        if (v.getId() == R.id.imone) {
            try {

                if (tvnum.getText().toString().equals("")) {
                    Toast.makeText(this, "No tasks", Toast.LENGTH_SHORT).show();
                } else {

                    a = Integer.parseInt(tvnum.getText().toString());
                    if (a < tasklist.size() && a > 0) {

                        a = a + 1;
                        tvnum.setText(a + "");


                        Log.d("a", a + "");
                        if (flag < length - 1 && flag >= 0) {
                            Log.d("tvnum", tvnum + "");
                            Log.d("a12", a + "");
                            flag = flag + 1;
                            setFields(flag);
                        } else
                            Toast.makeText(getApplicationContext(), "Current task is the last task", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getApplicationContext(), "Current task is the last task", Toast.LENGTH_LONG).show();
                }
            }catch (Exception e) {

            }
        }
        if (v.getId() == R.id.imfor) {
            try {

                Log.d("tvnum", tvnum.getText().toString());
                if (tvnum.getText().toString().equals("")) {
                    Toast.makeText(this, "No tasks", Toast.LENGTH_SHORT).show();
                } else if (!tvnum.getText().toString().equals("")) {
                    tvnum.setText(String.valueOf(tasklist.size()));
                    flag = length - 1;
                    setFields(flag);
                }
            } catch (Exception e) {

            }
        }

        if (v.getId() == R.id.imdelete) {
            /*ArrayList<Task> removelist = new ArrayList<Task>();
            for(Task t : tasklist){
                if(t != null && task.getTitle().equals(t.getTitle())){
                    removelist.add(t);
                }
            }
            Log.d("demo", removelist + "");

            tasklist.removeAll(removelist);*/
            try {
            if (tvnum2.getText().toString().equals(""))
                Toast.makeText(getApplicationContext(), "Tasklist is empty", Toast.LENGTH_LONG).show();
            else{
            b = Integer.parseInt(tvnum.getText().toString());
            a = tasklist.indexOf(task);


                Log.d("task",task + "");
                Log.d("num", tasklist.size() + "");
                int num = Integer.parseInt(String.valueOf(tasklist.size()));
                if (num > 1) {
                    Log.d("num2", tasklist.size() + "");

                    tasklist.remove(a);
                    setFields(flag = 0);
                    tvnum.setText(1 + "");
                    int num2 = tasklist.size();
                    tvnum2.setText(num2 + "");
                    Log.d("tashh",tasklist.get(b) +"");
                    tasklist.getFirst();
                    Collections.sort(tasklist, new DateComparator());

                    /*num=num-1;
                    tvnum.setText(num +"");*/
                    if (b == 1) {
                        setFields(flag = 1);
                    }
                }
                if (num == 1 && b == 1) {
                    Log.d("sam1", num + "");
                    tasklist.remove(a);
                    //tvnum2.setText("0");
                    tvnum.setText("");
                    tvnum2.setText("");
                    tvtitle.setText("Task Title");
                    tvtaskdate.setText("Task Date");
                    tvtasktime.setText("Task Time");
                    tvtaskpriority.setText("Task Priority");
                    //setFields(flag = 0);

                }}
            } catch (Exception e) {

            }
        }
        if (v.getId() == R.id.imedit) {
            try {
                if (tvnum2.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "Tasklist is empty", Toast.LENGTH_LONG).show();
                else {
                    mtitle = tvtitle.getText().toString();
                    mdate = tvtaskdate.getText().toString();
                    mtime = tvtasktime.getText().toString();
                    mpriority = tvtaskpriority.getText().toString();
                    Task t = new Task(mtitle, mdate, mtime, mpriority);
                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    intent.putExtra(Main_Key, t);
                    intent.putExtra("index", tasklist.indexOf(t));
                    Log.d("demo", tasklist.indexOf(t) + "");
                    startActivityForResult(intent, reqcode_edit);
                }
            } catch (Exception e) {

            }

        }

    }
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == reqcode) {
        if (resultCode == RESULT_OK) {
            if (data.getExtras().getSerializable(CreateTaskActivity.Task_Key) != null) {
                task = (Task) data.getExtras().getSerializable(CreateTaskActivity.Task_Key);
                tvtitle.setText(task.getTitle());
                tvtaskdate.setText(task.getDate());
                tvtasktime.setText(task.getTime());
                tvtaskpriority.setText(task.getPriority() + " " + "Priority");
                //tvnum = tvnum2;
                tasklist.add(task);
                Collections.sort(tasklist, new DateComparator());
                Log.d("demo", String.valueOf(tasklist));
                tvnum2.setText(String.valueOf(tasklist.size()));
                tvnum.setText(String.valueOf(tasklist.indexOf(task) + 1));


                Log.d("demo", tasklist + "");
            }
        } else if (requestCode == RESULT_CANCELED) {
            Log.d("demo", "error");
        }
    }

    if (requestCode == reqcode_edit) {
        try {
            if (resultCode == RESULT_OK) {
                if (data.getExtras().getSerializable(EditActivity.Edit_Key) != null) {
                    task = (Task) data.getExtras().getSerializable(EditActivity.Edit_Key);
                    tvtitle.setText(task.getTitle());
                    tvtaskdate.setText(task.getDate());
                    tvtasktime.setText(task.getTime());
                    tvtaskpriority.setText(task.getPriority() + " " + "Priority");
                    int taskval = Integer.parseInt(tvnum.getText().toString());
                    tasklist.set(taskval - 1, task);
                    Collections.sort(tasklist,new DateComparator());

                } else if (requestCode == RESULT_CANCELED) {
                    Log.d("demo", "error");
                }
            }
        } catch (Exception e) {

        }
    }
}
        public void setFields(int key){
        tvtitle.setText(tasklist.get(key).getTitle());
            tvtaskdate.setText(tasklist.get(key).getDate());
            tvtasktime.setText(tasklist.get(key).getTime());
            tvtaskpriority.setText(tasklist.get(key).getPriority());
            //tvnum.setText(tasklist.indexOf(key) +"");

        }
        }
