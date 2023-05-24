package com.salmanTech.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.mariuszgromada.math.mxparser.*;
import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
    TextView myText ;
    Button Btn0, Btn1, Btn2, Btn3, Btn4, Btn5, Btn6, Btn7, Btn8, Btn9;
    Button eqBTn,delBTn,cancelBtn;
    Button dotBtn;
    Button plusBTn, minusBtn, multBtn, divBtn, bracketBtn1, bracketBtn2;
    Button sinBtn, cosBtn, tanBtn, arcSinBtn, arcCosBtn, arcTanBtn;
    Button piBtn, logBtn, lnBtn, powBtn, sqrBtn, sqBtn, abBtn, primeBtn, eBtn;
    Button msBtn,mrBtn,mcBtn,mplusBtn,mminusBtn;


    private TextView previousCalculation;
    private EditText display;

    String mem = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        
        initializeViewVariables();
        setOnClickListeners();

        display.setShowSoftInputOnFocus(false);
    }

    View.OnClickListener num_listener = new View.OnClickListener() {
        @Override
        public void onClick(View ob1) {
            Button mybtn1 =(Button)ob1;
            myText.append(mybtn1.getText().toString());

        }
    };

    View.OnClickListener operation_listener = new View.OnClickListener() {
        @Override
        public void onClick(View ob2) {
            Button mybtn2 =(Button)ob2;
            myText.append(mybtn2.getText().toString());
        }
    };

    View.OnClickListener mem_listener = new View.OnClickListener() {
        @Override
        public void onClick(View ob3) {
            Button mybtn3 =(Button)ob3;
            if (mybtn3.getId() == R.id.msBtn){
                mem = display.getText().toString();
                Toast.makeText(MainActivity.this, "Recorded", Toast.LENGTH_SHORT).show();
            }
            else if (mybtn3.getId() == R.id.mrBtn)
                display.setText(display.getText().toString() + mem);
            else if (mybtn3.getId() == R.id.mcBtn){
                mem = "0";
                Toast.makeText(MainActivity.this, "Memory Cleared", Toast.LENGTH_SHORT).show();
            }
            else if (mybtn3.getId() == R.id.mplusBtn)
                display.setText(mem + "+");
            else if (mybtn3.getId() == R.id.mminusBTn)
                display.setText(mem + "-");

        }
    };

    View.OnClickListener dot_listener = new View.OnClickListener() {
        @Override
        public void onClick(View ob4) {
            String sen = display.getText().toString();
            int length = display.getText().length();
            if(length ==0){
                display.setText("0.");
            }else if (display.getText().toString().contains(".")){
                if(sen.contains("+")||sen.contains("-")||sen.contains("×")||sen.contains("÷"))
                    display.setText(display.getText().toString() + ".");
                else
                    display.setText(display.getText().toString() + "");

            }else{
                display.setText(display.getText().toString() + ".");

            }
        }
    };


    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        display.setSelection(cursorPos + strToAdd.length());
    }


    private void initializeViewVariables()
    {
        myText = findViewById(R.id.editText);

        Btn0 = findViewById(R.id.zeroBtn);
        Btn1 = findViewById(R.id.oneBtn);
        Btn2 = findViewById(R.id.twoBtn);
        Btn3 = findViewById(R.id.threeBtn);
        Btn4 = findViewById(R.id.fourBtn);
        Btn5 = findViewById(R.id.fiveBtn);
        Btn6 = findViewById(R.id.sixBtn);
        Btn7 = findViewById(R.id.sevenBtn);
        Btn8 = findViewById(R.id.eightBtn);
        Btn9 = findViewById(R.id.nineBtn);
        dotBtn =findViewById(R.id.dotBtn);

        eqBTn = findViewById(R.id.equalBtn);
        delBTn = findViewById(R.id.delBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        plusBTn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        multBtn = findViewById(R.id.multBtn);
        divBtn = findViewById(R.id.divBtn);
        bracketBtn1 = findViewById(R.id.bracketBtn1);
        bracketBtn2 = findViewById(R.id.bracketBtn2);

        sinBtn = findViewById(R.id.sinBTn);
        cosBtn = findViewById(R.id.cosBtn);
        tanBtn = findViewById(R.id.tanBtn);
        arcSinBtn = findViewById(R.id.arcSinBtn);
        arcCosBtn = findViewById(R.id.arcCosBtn);
        arcTanBtn = findViewById(R.id.arcTanBtn);
        piBtn = findViewById(R.id.piBtn);
        logBtn = findViewById(R.id.logBtn);
        lnBtn = findViewById(R.id.lnBTn);
        powBtn = findViewById(R.id.powBtn);
        sqrBtn = findViewById(R.id.sqrBtn);
        sqBtn = findViewById(R.id.sqBtn);
        abBtn = findViewById(R.id.abBtn);
        primeBtn = findViewById(R.id.primeBtn);
        eBtn = findViewById(R.id.eBTn);

        msBtn = findViewById(R.id.msBtn);
        mrBtn = findViewById(R.id.mrBtn);
        mcBtn = findViewById(R.id.mcBtn);
        mplusBtn = findViewById(R.id.mplusBtn);
        mminusBtn = findViewById(R.id.mminusBTn);

        previousCalculation = findViewById(R.id.textView);
        display = findViewById(R.id.editText);
    }

    private void setOnClickListeners()
    {
        Btn0.setOnClickListener(num_listener);
        Btn1.setOnClickListener(num_listener);
        Btn2.setOnClickListener(num_listener);
        Btn3.setOnClickListener(num_listener);
        Btn4.setOnClickListener(num_listener);
        Btn5.setOnClickListener(num_listener);
        Btn6.setOnClickListener(num_listener);
        Btn7.setOnClickListener(num_listener);
        Btn8.setOnClickListener(num_listener);
        Btn9.setOnClickListener(num_listener);
        dotBtn.setOnClickListener(dot_listener);


        plusBTn.setOnClickListener(operation_listener);
        minusBtn.setOnClickListener(operation_listener);
        multBtn.setOnClickListener(operation_listener);
        divBtn.setOnClickListener(operation_listener);
        bracketBtn1.setOnClickListener(operation_listener);
        bracketBtn2.setOnClickListener(operation_listener);

        msBtn.setOnClickListener(mem_listener);
        mrBtn.setOnClickListener(mem_listener);
        mcBtn.setOnClickListener(mem_listener);
        mplusBtn.setOnClickListener(mem_listener);
        mminusBtn.setOnClickListener(mem_listener);

        eqBTn.setOnClickListener(this);
        delBTn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.equalBtn:
                String userExp = display.getText().toString();
                previousCalculation.setText(userExp);
                mXparser.setDegreesMode();
                Expression exp = new Expression(userExp);
                String result = String.valueOf(exp.calculate());
                evaluate(result);
                break;
            case R.id.delBtn:
                int cursorPos = display.getSelectionStart();
                int textLen = display.getText().length();
                if (cursorPos != 0 && textLen != 0) {
                    SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
                    selection.replace(cursorPos - 1, cursorPos, "");
                    display.setText(selection);
                    display.setSelection(cursorPos - 1);
                }
                break;
            case R.id.cancelBtn:
                display.setText("");
                previousCalculation.setText("");
                break;
        }
    }

    public void evaluate(String expression) {
        Expression exp = new Expression(expression);
        double result = exp.calculate();
        if (Double.isNaN(result) && (display.getText().toString().contains("÷0"))) {
            Toast.makeText(MainActivity.this, "The division by 0 is impossible !", Toast.LENGTH_LONG).show();
            display.getText().clear();
            previousCalculation.setText("");
        } else if(display.getText().toString().trim().isEmpty()){
            Toast.makeText(MainActivity.this, "Please enter an expression !", Toast.LENGTH_LONG).show();
            display.getText().clear();
            previousCalculation.setText("");
        }else if (Double.isNaN(result)) {
            Toast.makeText(MainActivity.this, "Syntax error !", Toast.LENGTH_LONG).show();
            display.getText().clear();
            previousCalculation.setText("");
        }else if (result == 1 && previousCalculation.getText().toString().contains("ispr")) {
            display.setText("Prime");
        }else if (result == 0 && previousCalculation.getText().toString().contains("ispr")) {
            display.setText("not Prime");
        } else {
            display.setText(String.valueOf(result));
        }
    }

   public void trigSinBTNPush(View view){
        updateText("sin(");
   }

    public void trigCosBTNPush(View view){
        updateText("cos(");
    }

    public void trigTanBTNPush(View view){
        updateText("tan(");
    }

    public void trigArcSinBTNPush(View view){
        updateText("arcsin(");
    }

    public void trigArcCosBTNPush(View view){
        updateText("arccos(");
    }

    public void trigArcTanBTNPush(View view){
        updateText("arctan(");
    }

    public void naturalLogBTNPush(View view){
        updateText("ln(");
    }

    public void logBTNPush(View view){
        updateText("log10(");
    }

    public void sqrtBTNPush(View view){
        updateText("sqrt(");
    }

    public void absBTNPush(View view){
        updateText("abs(");
    }

    public void piBTNPush(View view){
        updateText("pi");
    }

    public void eBTNPush(View view) {
        updateText("e");
    }

    public void xSquaredBTNPush(View view){
        updateText("^(2)");
    }

    public void xPowerYBTNPush(View view){
        updateText("^(");
    }

    public void primeBTNPush(View view){
        updateText("ispr(");
    }
}
