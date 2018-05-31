package inficare.smadhu.example.com.inficare.quiz;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import inficare.smadhu.example.com.inficare.R;
import inficare.smadhu.example.com.inficare.quiz.helper.DatabaseHelper;

public class Quiz extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnsubmit, btncheck;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
                radioGroup =(RadioGroup)findViewById(R.id.radioGroup);
                btnsubmit=(Button)findViewById(R.id.btnSubmit);
                btncheck=(Button)findViewById(R.id.btnCheck);
                openHelper=new DatabaseHelper(this);
                btnsubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int SelectedId= radioGroup.getCheckedRadioButtonId();
                        radioButton=(RadioButton)findViewById(SelectedId);
                        String text = radioButton.getText().toString();
                        db=openHelper.getWritableDatabase();
                        db=openHelper.getReadableDatabase();
                        String qry = "SELECT " +DatabaseHelper.COL_ANS_2+ " FROM " +DatabaseHelper.TABLE_ANS+"";
                        cursor=db.rawQuery(qry, null);
                        if(cursor!=null){
                            if(cursor.getCount()>0){
                                cursor.moveToNext();
                            }
                        }else {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(DatabaseHelper.COL_ANS_2, "8848");
                            long i = db.insert(DatabaseHelper.TABLE_ANS, null, contentValues);
                        }

                        insertdata(text);
                        cursor = db.rawQuery("SELECT " +DatabaseHelper.TABLE_ANS+ "."
                                +DatabaseHelper.COL_ANS_2+ "," +DatabaseHelper.TABLE_SA+ "."
                                +DatabaseHelper.COL_SA_2+ " FROM " +DatabaseHelper.TABLE_ANS+
                                " INNER JOIN (SELECT " +DatabaseHelper.COL_SA_2+ " FROM "
                                +DatabaseHelper.TABLE_SA+ " ORDER BY ID DESC LIMIT 1)"
                                +DatabaseHelper.TABLE_SA+ " ON " +DatabaseHelper.TABLE_SA+ "."
                                +DatabaseHelper.COL_SA_2+ "=" +DatabaseHelper.TABLE_ANS+ "."
                                +DatabaseHelper.COL_ANS_2+"", null);
                        if(cursor!=null){
                            if(cursor.getCount()>0){
                                cursor.moveToNext();
                                ContentValues contentValues = new ContentValues();
                                contentValues.put(DatabaseHelper.COL_COM_2, "CORRECT");
                                long dd = db.insert(DatabaseHelper.TABLE_COM, null, contentValues);
                            }else{
                                ContentValues contentValues = new ContentValues();
                                contentValues.put(DatabaseHelper.COL_COM_2, "INCORRECT");
                                long dd = db.insert(DatabaseHelper.TABLE_COM, null, contentValues);
                            }
                        }

                        Toast.makeText(getApplicationContext(), "the answer is submitted successfully and the submitted value is"+radioButton.getText(), Toast.LENGTH_LONG).show();

                    }
                });

                btncheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db = openHelper.getReadableDatabase();
                        String check = "SELECT " +DatabaseHelper.COL_COM_2+ " FROM " +DatabaseHelper.TABLE_COM+ " ORDER BY ID DESC LIMIT 1";
                        cursor= db.rawQuery(check, null);
                        if(cursor!=null){
                            if(cursor.getCount()>0){
                                cursor.moveToNext();
                                String vall = cursor.getString(cursor.getColumnIndex("value"));
                                Toast.makeText(getApplicationContext(), "your submitted answer is "+vall, Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
            public void insertdata(String value){
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.COL_SA_2, value);
                long id = db.insert(DatabaseHelper.TABLE_SA, null, contentValues);
            }

    /**
     * Created by forhad on 1/28/18.
     */

    public static class QuestionModel {

        public QuestionModel(String questionString, String answer) {
            QuestionString = questionString;
            Answer = answer;
        }

        public String getQuestionString() {
            return QuestionString;
        }

        public void setQuestionString(String questionString) {
            QuestionString = questionString;
        }

        public String getAnswer() {
            return Answer;
        }

        public void setAnswer(String answer) {
            Answer = answer;
        }

        private String QuestionString;
        private String Answer;


    }
}
