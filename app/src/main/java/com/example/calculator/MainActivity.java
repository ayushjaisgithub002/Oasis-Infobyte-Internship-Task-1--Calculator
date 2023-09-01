package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView t1,t2;
    MaterialButton button_c,button_open_bracket,button_close_bracket;
    MaterialButton button_add,button_sub,button_mul,button_div;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton button_ac,button_dot,button_equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.t_input);
        t2=findViewById(R.id.t_output);

        assignId(button_c,R.id.bc);
        assignId(button_open_bracket,R.id.open_bracket);
        assignId(button_close_bracket,R.id.close_bracket);
        assignId(button_close_bracket,R.id.close_bracket);
        assignId(button_add,R.id.add);
        assignId(button_sub,R.id.sub);
        assignId(button_mul,R.id.mul);
        assignId(button_div,R.id.divide);
        assignId(button0,R.id.b0);
        assignId(button1,R.id.b1);
        assignId(button2,R.id.b2);
        assignId(button3,R.id.b3);
        assignId(button4,R.id.b4);
        assignId(button5,R.id.b5);
        assignId(button6,R.id.b6);
        assignId(button7,R.id.b7);
        assignId(button8,R.id.b8);
        assignId(button9,R.id.b9);
        assignId(button_ac,R.id.b_clear);
        assignId(button_dot,R.id.bdot);
        assignId(button_equal,R.id.bequal);

    }
    void assignId(MaterialButton btn,int id)
    {
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        MaterialButton button=(MaterialButton)view;
        String buttonText=button.getText().toString();
        String dateToCalculate=t1.getText().toString();

        if(buttonText.equals("AC"))
        {
            t1.setText("");
            t2.setText("0");
            return;
        }
        if(buttonText.equals("="))
        {
            t2.setText(t2.getText());
            return;
        }
        if(buttonText.equals("X"))
        {
            dateToCalculate=dateToCalculate.substring(0,dateToCalculate.length()-1);
        }
        else
        {
            dateToCalculate=dateToCalculate+buttonText;
        }
        t1.setText(dateToCalculate);

        String finalResult=getResult(dateToCalculate);

        if(!finalResult.equals("Err"))
        {
            t2.setText(finalResult);
        }
    }
    String getResult(String data)
    {
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"JavaScript",1,null).toString();
            if(finalResult.endsWith(".0"))
            {
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e) {
            return "Err";
        }
    }
}