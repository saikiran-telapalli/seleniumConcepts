package selenium_Concepts;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;


public class GenerateRandonData {
	

	    public static final String ALPHA_CAPS  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    public static final String ALPHA   = "abcdefghijklmnopqrstuvwxyz";
	    public static final String NUM     = "0123456789";
	    public static final String SPL_CHARS   = "@$";

	    public static ArrayList<String> domain;
	 public static Random randomGenerator;

	    //Reusable Method for generate Domain names randomly.

	    public static String pickDomain()
	 
	     {
	      domain= new ArrayList<String>();
	         randomGenerator= new Random();
	        
	         domain.add(".com");
	         domain.add(".net");
	         domain.add(".org");
	         domain.add(".in");
	         domain.add(".co");
	         domain.add(".edu");
	         int index = randomGenerator.nextInt(domain.size());
	         return domain.get(index);
	     }
	  
	     //Reusable Method for Generating Random Email 
	 
	    public static String generateEmail()
	     {
	      String email=null;
	      char[] name = generateRandomData(3, 20, 1, 1, 0);
	      char[] domain = generateRandomData(3, 20, 1, 1, 0);
	     
	      email=new String(name)+"@"+new String(domain)+pickDomain();
	     
	     return email;
	     }
	  
	      //Reusable Method for Generating Random Mobile Number

	   public static String generateMobileNumber()
	       {
	        String mobileNumber=null;
	        char[] number = generateRandomData(9, 9, 0, 9, 0);
	       
	       mobileNumber= "9"+new String(number);
	       
	       return mobileNumber;
	       }

	      //Reusable Method for Generate Random Number between maximum and minimum values
	 
	   public static int getRandomNumberBetween(int min, int max) {
	        
	        Random foo = new Random();
	           int randomNumber = foo.nextInt(max - min) + min;
	           if(randomNumber == min) {
	               // Since the random number is between the min and max values, simply add 1
	               return min + 1;
	           }
	           else {
	               return randomNumber;
	           }
	 
	       }
	 
	    //Reusable Method for Generate Random Date from Start date to End Date
	 
	   public static String generateRandomDate(String Format,String startDate,String endDate) throws java.text.ParseException
	    {
	     DateFormat formatter = new SimpleDateFormat(Format);
	     Calendar cal=Calendar.getInstance();
	     cal.setTime(formatter.parse(startDate));
	     Long value1 = cal.getTimeInMillis();
	 
	     cal.setTime(formatter.parse(endDate));
	     Long value2 = cal.getTimeInMillis();
	 
	     long value3 = (long)(value1 + Math.random()*(value2 - value1));
	     cal.setTimeInMillis(value3);
	     return formatter.format(cal.getTime());
	        }
	 
	     //Reusable Method for Generating Random String

	      public static char[] generateRandomData(int minLen, int maxLen, int noOfCAPSAlpha, int noOfDigits,int noOfSplChars)
	      {
	       
	          if(minLen > maxLen)
	              throw new IllegalArgumentException("Min. Length > Max. Length!");
	          if( (noOfCAPSAlpha + noOfDigits +noOfSplChars) > minLen )
	              throw new IllegalArgumentException
	              ("Min. Length should be atleast sum of (CAPS, DIGITS, SPL CHARS) Length!");
	          Random rnd = new Random();
	          int len = rnd.nextInt(maxLen - minLen + 1) + minLen;
	          char[] pswd = new char[len];
	          int index = 0;
	          for (int i = 0; i < noOfCAPSAlpha; i++)
	          {
	              index = getNextIndex(rnd, len, pswd);
	              pswd[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
	          }
	          for (int i = 0; i < noOfDigits; i++)
	          {
	              index = getNextIndex(rnd, len, pswd);
	              pswd[index] = NUM.charAt(rnd.nextInt(NUM.length()));
	          }
	          for (int i = 0; i < noOfSplChars; i++)
	          {
	              index = getNextIndex(rnd, len, pswd);
	              pswd[index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
	          }
	          for(int i = 0; i < len; i++)
	          {
	              if(pswd[i] == 0)
	              {
	                  pswd[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
	              }
	          }
	          return pswd;
	      }
	  
	      public static int getNextIndex(Random rnd, int len, char[] pswd) {
	          int index = rnd.nextInt(len);
	          while(pswd[index = rnd.nextInt(len)] != 0);
	          return index;
	      }
	  
	                 //Usage

	     public static void main(String args[]) throws ParseException  {
	       
	       System.out.println(GenerateRandonData.pickDomain());
	       System.out.println(GenerateRandonData.generateRandomDate("dd MMM yyyy", "10 Aug 2016", "01 Sep 2017"));
	       System.out.println(GenerateRandonData.generateEmail());
	       System.out.println(GenerateRandonData.getRandomNumberBetween(5, 41));
	       System.out.println(GenerateRandonData.generateMobileNumber());
	       System.out.println(GenerateRandonData.generateRandomData(10, 11, 1, 1, 1));
	       
	      }
	     
	 
	}



