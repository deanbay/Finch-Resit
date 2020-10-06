import java.util.Scanner;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.util.Arrays;
//import javax.swing.*;
import java.util.DoubleSummaryStatistics;
import java.util.Collections;


public class NumConv {
	private static final String[] args = null;
	public static int Binindex1;
	public static int choices;
	public static int UserDecNumberInput;
	public static String OctalStr = "";
	public static String HexStr2 = "";
	
	public static int BinaryArrayList[] = new int[8]; 
	public static Scanner Scannerinput = new Scanner(System.in);
	
	public static String binaryNum;
	public static String binaryRevNum;
	
	private static Finch DeansFinch = new Finch();
	static DoubleSummaryStatistics TimeCollector = new DoubleSummaryStatistics(); 
	
    public static void main(String[] args) {
    	
		
    	System.out.println("-----------------------* Welcome to the Finch Number Conversion. *-----------------------");
    	System.out.println("Note: Any Alphabetical and Symbol inputs at this point will be deteced upon entry and stop the program");
    	System.out.println("Note: Actions of the finch involve moving, buzzing and green/red Reverse Binary Light Patterns.");
    	try {
    	while(true) { // this statement allows us to loop over again as many times as the user likes.
    			System.out.println("Enter a positive integer to convert");
        		UserDecNumberInput = Scannerinput.nextInt();//first point of data entry
        		binaryNum = Integer.toBinaryString(UserDecNumberInput);
        		binaryRevNum = ReverseOnBinary(binaryNum); //binaryRevNum = the return value of the reverse function, binary value is added in for reverse
        		//System.out.println("DEBUG: You entered : " + UserDecNumberInput + " The binary of that = " + binaryNum);
        		//System.out.println("DEBUG: The reverse of " + binaryNum + " is " + binaryRevNum);
        	    
        		
        		//String StraightNum = reverse(binaryRevNum);
        	//	System.out.println("DEBUG: Reverse of the reverse is= " + StraightNum);
        		
        		if (UserDecNumberInput <= 0) // if the given value is lower than or equal to 0
        		{
        			System.out.println("Error: Value too low, it is lesser than or equal to 0");
        		}
        		else { // the first else that allows the program to work if the number is more than 0 
        			if (UserDecNumberInput > 255) { //if the value is too high
        				System.out.println("Error: Value too high, it is more than 255");
        			} 
        			else { // the second else statement that allows the program to proceed if the number is less than 255
        					
        				System.out.println("Do you want the conversion or Both conversion & Finch Actions? ");
        				System.out.println("(0 for Conversion or 1 for conversion, Reverse Binary colour pattern and moving)");
        				choices = Scannerinput.nextInt();  
        		
        				if (choices == 0) {
        					
        					System.out.println();
        					System.out.println("Entered Decimal Number is: " + UserDecNumberInput);
        					System.out.println();
        					System.out.print("Conversion to binary is: ");
        					printingBinaryform(UserDecNumberInput);
        					System.out.println("");
        					System.out.print("Conversion to hexadecimal is: ");
        					printingHexform(UserDecNumberInput);
        					System.out.println(HexStr2);
        					System.out.print("Conversion to octal is: ");
        					printingOctform(UserDecNumberInput);
        					System.out.println(OctalStr);
        					System.out.println("");
        					System.out.println("NOTE: Alphabetical inputs can now be recieved at this section");
        					Choices();

        				}
        				else if (choices == 1) {
        					
        					System.out.println();
        					System.out.println("Entered Decimal Number is: " + UserDecNumberInput);
        					System.out.println();
        					System.out.print("Conversion to binary is: ");
        					printingBinaryform(UserDecNumberInput);
        					System.out.println("");
        					System.out.print("Conversion to hexadecimal is: ");
        					printingHexform(UserDecNumberInput);
        					System.out.println(HexStr2);
        					System.out.print("Conversion to octal is: ");
        					printingOctform(UserDecNumberInput);
        					System.out.println(OctalStr);
        					System.out.println("The reverse of " + binaryNum + " is " + binaryRevNum);
        					myMethodForFinchTask();
        					
        					System.out.println("");
        		    		// Task 3: buzzer task
        			        int BuzzOctString = Integer.parseInt(OctalStr); // parsing in the integer with the octal string for the finch being able to buzz
        			        DeansFinch.buzz(BuzzOctString, 1000); // Frequency of the buzz takes the the int with the time done in 1000
        			        //end of task 3
        			        System.out.println("");
        			        System.out.println("-----------------------* CHOICE SECTION *-----------------------");
        			        System.out.println("NOTE: Alphabetical inputs can now be received at this section");
        			        Choices();
        					
        				}
        				else if(!(choices == 1 ) && !(choices == 0)  ){
        					System.out.println("Error: Wrong number entered, it is out of range.");
        					System.out.println("Please choose Either 0 or 1 to progress with either Conversion or Converting, Reverse Binary colour pattern and moving ");
        					System.out.println("");
        				}
        			}
        		} // first container for the first else statement
        	}
    	}
    	catch(Exception e) { //this is to catch any non numeric entries and terminate the programs as we have exited the while statement.
    		System.out.println("");
    		System.out.println("Sorry, As stated, Alphabetical and Symbol inputs arent allowed. Terminating program.");
    		System.out.println("Please enter number next time.");
    
    	}
    	
      }
    
    public static void myMethodForFinchTask() {
        int hexl = HexStr2.length();
    	// calculating the length of the hexadecimal number output for determining the amount of acceleration of the finch
        long timestamp = System.nanoTime(); // start timer to recording the time that the finch takes
		
    	if (UserDecNumberInput < 50) //statement for decimal number entered less than 50
    		UserDecNumberInput = UserDecNumberInput + 50; //the result for a number inputed less than 50 will set the speed as +50 + the addition of the decimal number entered. eg 15 + 50 = 65
    	if (hexl<2) {
    		DeansFinch.setWheelVelocities(UserDecNumberInput, UserDecNumberInput, 1000); //finch movement
    	} else {
    		DeansFinch.setWheelVelocities(UserDecNumberInput, UserDecNumberInput, 2000);
    	}
    	long endstamp = System.nanoTime(); // end timer
		long totaltime = endstamp - timestamp; // total timer
		int totaltimeinseconds = (int) (totaltime / Math.pow(10, 9));
		TimeCollector.accept(totaltimeinseconds);
		if (totaltimeinseconds == 2) {
			System.out.println("Duration of Finch Movement: " + totaltimeinseconds +" Seconds as a Double-digit hex was detected");
			//System.out.println("Speed is +" + UserNumberInput);
			
		}
		else {
			System.out.println("Duration of Finch Movement: " + totaltimeinseconds +" Seconds as a Single-digit hex was detected");
			//System.out.println("Speed is +" + UserNumberInput);
		}
		if(!(UserDecNumberInput >= 50)) { // statement explaining the user
		}
		else {
			System.out.println("Speed of finch wheel is +" + UserDecNumberInput);
		}
    	// Task 2
    	int i;
        char x;
    	 // iterating over an array 
        Collections.reverse(Arrays.asList(BinaryArrayList)); //reversing statement
    	for (i = 0; i < binaryRevNum.length(); i++) { //prints the reveres statement
    		// accessing each element of array 
    		x = binaryRevNum.charAt(i); 
    		System.out.print(x);
    		if (x == '0') { //reading the string for 0
    			DeansFinch.setLED(0,255,0,500); // green light
    			DeansFinch.sleep(250);
    		}
    		if (x == '1') { // reading the string for 1
    			DeansFinch.setLED(255,0,0,500); //red light
    			DeansFinch.sleep(250);
    		}
    	}		
    }
    
    private static String ReverseOnBinary(String preRev) { 
    	String reverse = ""; 
    	for(int i = preRev.length()-1; i >= 0; i--) { //for statement for iterating over the statement from the end of the Array until it reaches to the first element in the array. keeps decrementing once it reaches the end where nothing can be read.
    		reverse = reverse + preRev.charAt(i); // how the reverse string will be populated.
    	}
    	return reverse; //returns to reverse to fill in the string
    	
    }
      public static void Choices() {
        //users choice from here will use the 
    	  System.out.println("");
			Scanner ChoiceScanner = new Scanner(System.in); //construction of scanner
			//System.out.println("NOTE: Alphabetical inputs can now be received at this section");
			//jumps and links JAL, relates to assembly code
		      System.out.println("To return to the program, Please enter ' return ' ");
		      System.out.println("To Terminate the program, Please enter ' quit ' ");
		      System.out.println("any other inputs will be counted as an incorrect input");
		      String model = ChoiceScanner.next();
		      model = model.toLowerCase();// prevents user from entering in text in caps
		      switch (model) {
		         case "return":
		        	 
		        	System.out.println("\nReturning");
		        	main(args);
		        	
		            break;
		         case "quit":
		        	 System.out.println("\nTerminating");
		    			System.exit(1);
		    			break;
		         default:
		         	 System.out.println("");
		        	 System.out.println("Incorrect input");
		             System.out.println("Please make one of the the given chocies");
		             Choices();
		             break;
		      		}   
		      }
    private static void printingBinaryform(int number) {// binary format will start with 1 as 1 and 255 as 11111111, the bigger the number, the bigger the binary format will display.
	    	Binindex1 = 0;
	    	while(number > 0) {
	    		BinaryArrayList[Binindex1++] = number%2; //modulo to check if it is a zero or a one
	    		number = number/2; 	
	    	}
	    	for(int i = Binindex1-1;i >= 0;i--) { //loop from the back (), keep loops from the back & decrement the iterator variable [i] from that list
	    		System.out.print(BinaryArrayList[i]);
	    	}

   
	    	
    }
    
    private static void printingHexform(int number) {
    	// storing remainder int
        int Hexrem;
        // result string 
        HexStr2=""; 
        // Digits in hexadecimal number system
        char HexUnits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(number>0)
        { //example input = 50 //3
          Hexrem=number%16; //*P1* 50 % 16 = 2 //P2 3 % 16 = 3  
          HexStr2=HexUnits[Hexrem]+HexStr2; //filling in the string with hex chars and hex remainder // *P1* HexUnits[2] = '2' // HexStr2 = '2', //*P2* HexUnits[3] = '3' + '2' // HexStr2 = "32" 
          number=number/16; //P1 50 / 16 = 3 //P2 3 / 16 = 0
          													//fin
        }
    }
    private static void printingOctform(int number) {
    	// For storing remainder
        int OctRemainder;
     
        // For storing result
        OctalStr=""; 
   
        // the units found in Octal number system
        char OctUnits[]={'0','1','2','3','4','5','6','7'};
     
        while(number>0)
        {
        	//example input = 50
        	//modulous
           OctRemainder=number%8; //P1 50 % 8 = 2 //P2 6 % 8 = 6
       	 //  System.out.println("NUMBER: " + number + " OctRemainder: " + OctRemainder + " OctUnits: " + Arrays.toString(OctUnits) );
  
           OctalStr=OctUnits[OctRemainder]+OctalStr; //filling in the string with hex chars and hex remainder //P1 OctUnits[2] = '2' //P2 OctUnits[6] = '6' + '2' // OctStr = "62"
      
          // System.out.println(" OctRemainder: " + OctRemainder + " OctUnit: " + OctUnits[OctRemainder] + " OctalStr = " + OctalStr);
           number=number/8; //same as with printing hexform but only dividing by 8 //P1 number = 50/8 = 6 due to integer rounding //P2 6/8 = 0 due to integer rounding  
           																																								//fin
         //  System.out.println("NUMBER: " + number + " OctUnit: " + OctUnits[OctRemainder] + " OctalStr = " + OctalStr);
        }
    }
}
