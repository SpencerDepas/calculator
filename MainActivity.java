package com.example.calculator;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	private SensorManager mSensorManager;
	//EditText editText;
	TextView textView;
	TextView answer;
	
	String value[] = new String[26];
	int sign[] = new int[26]; // 1 = +, 2 = -
	int valueIndex = 0;
	boolean multiplex = false;
	
	public void Toaster(String iAmString) {
		Toast.makeText(this.getApplicationContext(), iAmString,
				Toast.LENGTH_LONG).show();
	}
	

	//assumes all first numbers are positive
	
	//I think that maybe I should revise How I am doing this
	//and instead of converting it from a string break it up into 
	//numbers on each press. seperating what is seen and
	//the numbers
	
	
	//clear information after = 
	
	//add custom divide  button
	
	//add stuff so I cant add two ++ ect
	//check format
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) this.findViewById(R.id.editText1);
        textView.setFocusable(true);
        answer = (TextView) this.findViewById(R.id.editText2);
       
        //change color
        Button equals = (Button) this.findViewById(R.id.buttonEquals);
        equals.getBackground().setColorFilter(0xff888888, PorterDuff.Mode.MULTIPLY);
        
        
        //incializes string
        for(int i = 0; i < value.length; i++){
        	value[i] = ""; 	
        }
        
    }


    //1st row
    public void seven(View view){
    	textView.append("7");  	
    	value[valueIndex] = value[valueIndex].concat("7");
    }
    
    public void eight(View view){
    	textView.append("8");  
    	value[valueIndex] = value[valueIndex].concat("8");
    }
    public void nine(View view){
    	textView.append("9");  	
    	value[valueIndex] = value[valueIndex].concat("9");
    }
    
    //2nd row
    public void four(View view){
    	textView.append("4");  	
    	value[valueIndex] = value[valueIndex].concat("4");
    }
    public void five(View view){
    	textView.append("5");  	
    	value[valueIndex] = value[valueIndex].concat("5");
    }
    public void six(View view){
    	textView.append("6");  	
    	value[valueIndex] = value[valueIndex].concat("6");
    }
    
    //3rd row
    public void one(View view){
    	textView.append("1");  	
    	value[valueIndex] = value[valueIndex].concat("1");
    }
    public void two(View view){
    	textView.append("2");
    	value[valueIndex] = value[valueIndex].concat("2");
    }
    public void three(View view){
    	textView.append("3");  	
    	value[valueIndex] = value[valueIndex].concat("3");
    }
    
    //bottom row
    public void zero(View view){
    	textView.append("0");  	
    	value[valueIndex] = value[valueIndex].concat("0");
    }
    
    //equals not included here
    //
    //operands
    public void lParen(View view){
    	textView.append("(");  		
    }
    
    public void rParen(View view){
    	textView.append(")");  	
    }
 

    public void dot(View view){
    	textView.append(".");  	
    }
    public void mod(View view){
    	textView.append("%");  	
    	sign[valueIndex] = 5;
    	valueIndex++;
    }
    public void divide(View view){
    	textView.append("/"); 
    	sign[valueIndex] = 4;
    	valueIndex++;
    }
    
    public void times(View view){
    	textView.append("*");  	
    	sign[valueIndex] = 3;
    	valueIndex++;
    }
    
    public void plus(View view){
    	textView.append("+"); 
    	sign[valueIndex] = 1;
    	valueIndex++;
    }
    public void minus(View view){
    	textView.append("-");  	
    	sign[valueIndex] = 2;
    	valueIndex++;
    }
    
    
    public void ac(View view){
    	textView.setText("");
    	answer.setText("");
    	valueIndex = 0; // reset counter 
   	   //re set STring
       for(int i = 0; i < value.length; i++){
       	value[i] = ""; 	
       	sign[i] = 0;
       }
       multiplex = false;
    }
    
    
    public void equals(View view){
    	
    	String equation = textView.getEditableText().toString();
    	textView.append(" =");

    	//Toaster(equation);
    	
    	
    	int result = Calculate(equation);
    	String sResult = Integer.toString(result);
    	answer.setText(sResult);

    	multiplex = false;
    	valueIndex = 0; // reset counter 
    	 //re set STring
        for(int i = 0; i < value.length; i++){
        	value[i] = ""; 	
        	sign[i] = 0;
        }
    }
    
	//turns string to int.
	//returns answer
	
    
    public int Calculate(String equation){
    	
    	//int sum1 = Integer.parseInt(value[0]) + Integer.parseInt(value[1]);
    	int sum1 = 0;
    	
    	
    	
    	
    	//multiplys and divides
    	for(int i = 0; sign[i] > 0; i++){
    		
    		
    		
	    	switch(sign[i]){

			
			case 3: if(sum1 == 0){
						sum1 = Integer.parseInt(value[i]);
						(value[i]) = "";
					}
					sum1 *= Integer.parseInt(value[i + 1]);
					(value[i + 1]) = "";
					multiplex = true;
					break;
					
			case 4: if(sum1 == 0){
						sum1 = Integer.parseInt(value[i]);
						(value[i]) = "";
					}
					sum1 /= Integer.parseInt(value[i + 1]);
					(value[i + 1]) = "";
					multiplex = true;
					break;
			
			case 5: if(sum1 == 0){
						sum1 = Integer.parseInt(value[i]);
						(value[i]) = "";
					}
					sum1 %= Integer.parseInt(value[i + 1]);
					(value[i + 1]) = "";
					multiplex = true;
					break;
			
	    	}
	    	
    	}
    	

    	
    	//this adds and subtracts
    	for(int i = 0; sign[i] > 0; i++){
    		
	    	switch(sign[i]){
			
			case 1: if(value[i].length() > 0 && multiplex == true){//these two ifelse are for finding out the right index if multiplicvation has been used
						sum1 += Integer.parseInt(value[i]);
						(value[i]) = "";
					}else{
						if(multiplex){
							sum1 += Integer.parseInt(value[i + 1]);
							(value[i + 1]) = "";
						}
					}
					if(sum1 == 0 && multiplex == false){
						sum1 = Integer.parseInt(value[i]);
					} else{
						if(!multiplex){
							sum1 += Integer.parseInt(value[i + 1]);
						}
					}
					
					break;
			
			case 2: if(sum1 == 0){
						sum1 = Integer.parseInt(value[i]);
					} 
					if(value[i].length() > 0){
						sum1 -= Integer.parseInt(value[i]);
					}else{
						sum1 -= Integer.parseInt(value[i + 1]);
					}
					break;
	    	}
	    	
    	}	
    	
    	
    	
    	
	    	//Toaster(Integer.toString(sum1));
	    	return sum1;
    }

    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}


