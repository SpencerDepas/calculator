package com.example.calculator;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements
		SensorEventListener{

	//this is for the onShake
	private SensorManager mSensorManager;
	private boolean mInitialized;
	private Sensor mAccelerometer;
	private final float NOISE = (float) 10.0;
	

	//EditText editText;
	TextView textView;
	TextView answer;
	
	String checkValue = ""; //this is to check format before to stop things like "++" or "4+=" ect
	String value[] = new String[26];
	int sign[] = new int[26]; // 1 = +, 2 = -
	int valueIndex = 0;
	boolean multiplex = false;
	boolean firstIntNegative = false;
	
	public void Toaster(String iAmString) {
		Toast.makeText(this.getApplicationContext(), iAmString,
				Toast.LENGTH_LONG).show();
	}
	
	
	
	

	//assumes all first numbers are positive
	//i need to add full division capacty.use type other than int
	//when you shake it does onClear
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) this.findViewById(R.id.editText1);
        answer = (TextView) this.findViewById(R.id.editText2);
       
       
        //change color of button
        
       
        Drawable d = findViewById(R.id.buttonEquals).getBackground();  
        PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);  
        d.setColorFilter(filter); 
        
        
        Drawable a = findViewById(R.id.buttonMod).getBackground();  
        PorterDuffColorFilter filter2 = new PorterDuffColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_ATOP);  
        a.setColorFilter(filter2); 
        
        
        Drawable b = findViewById(R.id.buttonAC).getBackground();  
        PorterDuffColorFilter filter3 = new PorterDuffColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_ATOP);  
        b.setColorFilter(filter3); 
        
        
        Drawable c = findViewById(R.id.buttonPlus).getBackground();  
        PorterDuffColorFilter filter4 = new PorterDuffColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_ATOP);  
        c.setColorFilter(filter4); 
        
        
        Drawable e = findViewById(R.id.buttonMinus).getBackground();  
        PorterDuffColorFilter filter5 = new PorterDuffColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_ATOP);  
        e.setColorFilter(filter5); 
        
        
        Drawable f = findViewById(R.id.buttonTimes).getBackground();  
        PorterDuffColorFilter filter6 = new PorterDuffColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_ATOP);  
        f.setColorFilter(filter6); 
        
        
        Drawable g = findViewById(R.id.buttonDivide).getBackground();  
        PorterDuffColorFilter filter7 = new PorterDuffColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_ATOP);  
        g.setColorFilter(filter7); 
        
        
        Drawable h = findViewById(R.id.buttonLParen).getBackground();  
        PorterDuffColorFilter filter8 = new PorterDuffColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_ATOP);  
        h.setColorFilter(filter8); 
        
       
        Drawable z = findViewById(R.id.buttonRParen).getBackground();  
        PorterDuffColorFilter filter9 = new PorterDuffColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_ATOP);  
        z.setColorFilter(filter9); 
        
  
        
        //incializes string
        for(int i = 0; i < value.length; i++){
        	value[i] = ""; 	
        }
        
        
        //onShake stuff
        mInitialized = false;
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
        
    }
  
    
    
    public void buttonBusiness(String buttonNumber){
    	if(multiplex == true){
    		textView.setText("");
        	answer.setText("");
            multiplex = false;
    	}
    	textView.append(buttonNumber);  
    	checkValue = checkValue.concat(buttonNumber);
    	value[valueIndex] = value[valueIndex].concat(buttonNumber);
    }
    
    
    //numbers

    public void seven(View view){
    	buttonBusiness("7");
    }
    
    public void eight(View view){
    	buttonBusiness("8");
    }
    public void nine(View view){
    	buttonBusiness("9");
    }
    public void four(View view){
    	buttonBusiness("4");
    }
    public void five(View view){
    	buttonBusiness("5");
    }
    public void six(View view){
    	buttonBusiness("6");
    }
    
    public void one(View view){
    	buttonBusiness("1");
    }
    public void two(View view){
    	buttonBusiness("2");
    }
    public void three(View view){
    	buttonBusiness("3");
    }
    
    public void zero(View view){
    	buttonBusiness("0");
    }
    
    //operands
    public void lParen(View view){
    	//textView.append("(");  
    	Toaster("I dont work yet");
    }  
    public void rParent(View view){
    	//textView.append(")");  
    	Toaster("I dont work yet");
    }

    public void dot(View view){
    	//textView.append("."); 
    	Toaster("I dont work yet");
    }
    
    public void operandBusiness(String operand, int operandSign){
    	char lastChar = checkValue.charAt(checkValue.length() - 1);
    	if(lastChar == '+' || lastChar == '=' || lastChar == '/' || lastChar == '÷' || lastChar == '*' || lastChar == '-'){
	    	//this is for if they press +++++++
    	}else{
	    	textView.append(operand);  	
	    	checkValue = checkValue.concat(operand);
	    	sign[valueIndex] = operandSign;
	    	valueIndex++;
    	} 	
    }
    
    
    
    public void mod(View view){
    	operandBusiness("%", 5);
    }

    public void divide(View view){
    	operandBusiness("/", 4);
    }
    
    public void times(View view){
    	operandBusiness("*", 3);
    }
    
    public void plus(View view){
    	operandBusiness("+", 1);
    }
    public void minus(View view){
    	//this is for if you press '-' before a number
    	if(textView.length() < 1){
			textView.append("-"); 
			firstIntNegative = true;
		}
    	operandBusiness("-", 2);
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
    	char lastChar = checkValue.charAt(checkValue.length() - 1);
    	if(lastChar == '+' || lastChar == '-' || lastChar == '=' || lastChar == '/' || lastChar == '÷' || lastChar == '*'){
	    	//then nothing
    	}else{
    	
	    	String equation = textView.getEditableText().toString();
	    	textView.append(" =");
	
	    	
	    	int result = Calculate(equation);
	    	String sResult = Integer.toString(result);
	    	answer.setText(sResult);
	
	    	checkValue = "+";
	    	firstIntNegative = false;
	    	valueIndex = 0; // reset counter 
	    	//re set STring
	        for(int i = 0; i < value.length; i++){
	        	value[i] = ""; 	
	        	sign[i] = 0;
	        }
    	}
    }
    
    
    public int Calculate(String equation){
    	
    	//int sum1 = Integer.parseInt(value[0]) + Integer.parseInt(value[1]);
    	int sum1 = 0;
    	
    	//multiplys and divides mod
    	for(int i = 0; sign[i] > 0; i++){
    		
    		
    		
	    	switch(sign[i]){

			
			case 3: if(sum1 == 0){
					if(firstIntNegative && sign[0] == 3){
						firstIntNegative = false;
						sum1 -= Integer.parseInt(value[i]);
						(value[i]) = "";
					}else
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
			//add
			case 1:if(value[i].length() > 0 && multiplex == true && firstIntNegative){//these two ifelse are for finding out the right index if multiplicvation has been used
						sum1 -= Integer.parseInt(value[i]);
						firstIntNegative = false;
						(value[i]) = "";
					}else if(value[i].length() > 0 && multiplex == true){//these two ifelse are for finding out the right index if multiplicvation has been used
						sum1 += Integer.parseInt(value[i]);
						(value[i]) = "";
					}else{
						if(multiplex){
							sum1 += Integer.parseInt(value[i + 1]);
							(value[i + 1]) = "";
						}
					}
					if(sum1 == 0 && multiplex == false && firstIntNegative){
						firstIntNegative = false;
						sum1 -= Integer.parseInt(value[i]);
						sum1 += Integer.parseInt(value[i + 1]);
					}else if(sum1 == 0 && multiplex == false){
						sum1 = Integer.parseInt(value[i]);
						sum1 += Integer.parseInt(value[i + 1]);
					}else{
						if(!multiplex){
						sum1 += Integer.parseInt(value[i + 1]);
							}
					}
					
					break;
			//subtract
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

    @Override
	protected void onResume() {
		super.onResume();

		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
	}


    @Override
	protected void onPause() {
		super.onPause();

		mSensorManager.unregisterListener(this);
	}
    
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
		float mLastX = 0;
		float mLastY = 0;
		float mLastZ = 0;
		
		
		if (!mInitialized) {
			mLastX = x;
			mLastY = y;
			mLastZ = z;
			mInitialized = true;
		} else {
			float deltaX = Math.abs(mLastX - x);
			float deltaY = Math.abs(mLastY - y);
			float deltaZ = Math.abs(mLastZ - z);
			if (deltaX < NOISE)
				deltaX = (float) 0.0;
			if (deltaY < NOISE)
				deltaY = (float) 0.0;
			if (deltaZ < NOISE)
				deltaZ = (float) 0.0;
			mLastX = x;
			mLastY = y;
			mLastZ = z;
		
			if (deltaX > deltaY) {
				textView.setText("");
		    	answer.setText("");
		    	valueIndex = 0; // reset counter 
		   	   //re set STring
		       for(int i = 0; i < value.length; i++){
		       	value[i] = ""; 	
		       	sign[i] = 0;
		       }
		       multiplex = false;
			} else if (deltaY > deltaX) {
				textView.setText("");
		    	answer.setText("");
		    	valueIndex = 0; // reset counter 
		   	   //re set STring
		       for(int i = 0; i < value.length; i++){
		       	value[i] = ""; 	
		       	sign[i] = 0;
		       }
		       multiplex = false;
			} else {
				//do nothing
			}
		}
	}





	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}


}


