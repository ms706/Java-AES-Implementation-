/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

/**
 *
 * @author monika
 */
public class hextable {
    private String[][] hex_string=new String[4][4];
    private int left_value;
    private int right_value;
            
    int decimal;
    String hex; 
    public hextable(){
    }
    public String getHex(int decimal)
    {
         
        if(decimal<10)
            hex=Integer.toString(decimal);
        else
        {
            switch(decimal)
            {
                case 10:hex="a";break;
                case 11:hex="b";break;
                case 12:hex="c";break;
                case 13:hex="d";break;
                case 14:hex="e";break;
                case 15:hex="f";break;
                default:hex="?";break;
            }
        }
               return hex; 
    }
    public int getDecimal(char ch)
    {
         
         
            switch(ch)
            {
                case '0':decimal=0;break;
                case '1':decimal=1;break;
                case '2':decimal=2;break;
                case '3':decimal=3;break;
                case '4':decimal=4;break;
                case '5':decimal=5;break;
                case '6':decimal=6;break;
                case '7':decimal=7;break;
                case '8':decimal=8;break;
                case '9':decimal=9;break;
                case 'a':decimal=10;break;
                case 'b':decimal=11;break;
                case 'c':decimal=12;break;
                case 'd':decimal=13;break;
                case 'e':decimal=14;break;
                case 'f':decimal=15;break;
                  
                 
                 
                default:decimal=-1;break;
            }
         
               return decimal; 
    }
    public String[][] stringToHex(String str){
        for(int i=0;i<16;i++){        
            int row=i%4;
            int col=i/4;
            hex_string[row][col]=Integer.toHexString(str.charAt(i));            
            if(str.charAt(i)<16)
                hex_string[row][col]="0"+hex_string[row][col];
        }
        return hex_string;
    }
    
    public byte[] getBinaryBits(int ch) {
        byte[] bin=new byte[8];
        int tag=1;
        for(int i=0;i<8;i++) {
            bin[7-i]=(byte)((ch&((tag<<i)))>>i);
      
        }
          
        return bin;
    }
    
    public byte[] getBinaryBitsHex(int ch) {
           
        if(ch>=48&ch<=57)
            ch-=48;
        else 
            ch-=55;
          
        byte[] bin=new byte[8];
        int tag=1;
        for(int i=0;i<8;i++) {
            bin[i]=(byte)((ch&((tag<<i)))>>i);
      
        }
          
        return bin;
    }
    public String hexToString(String[][] myTable) {
        byte[] b1,b2;
        String str=null;
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++) {
             
            
            b1=getBinaryBitsHex(myTable[j][i].charAt(0));
            b2=getBinaryBitsHex( myTable[j][i].charAt(1));
            left_value=b1[3]*128+b1[2]*64+b1[1]*32+b1[0]*16;
            left_value+=b2[3]*8+b2[2]*4+b2[1]*2+b2[0]*1;
            if(str==null){
                str=Character.toString((char)left_value);
             }
             else
                 str+=Character.toString((char)left_value);
             
            }
        return str;
    }
}
