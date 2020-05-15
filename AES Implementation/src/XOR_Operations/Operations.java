/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XOR_Operations;

/**
 *
 * @author shikha
 */
import Common.hextable;
public class Operations {
    private byte[] elements;
    private byte[] left2;
    private byte[] right2;
    private byte[] left;
    private byte[] right;
    private int left_value;
    private int right_value;    
    
    hextable ht=new hextable(); 
    public Operations(){
    
    }
    
    public String xor2hex(String s1,String s2) {
        //System.out.println(s1+" "+s2);
        int left1=ht.getDecimal(s1.charAt(0));
        int right1=ht.getDecimal(s1.charAt(1));
        int value1=left1*16+right1;
        int l2=ht.getDecimal(s2.charAt(0));
        int r2=ht.getDecimal(s2.charAt(1));//correction
        int value2=l2*16+r2;
        int xor=(value1^value2);
        
        String temp=Integer.toHexString(xor);
        if(xor<16)
            temp="0"+temp;
        //System.out.println("XOR Return"+temp);
        return temp;
    }
    public byte[] getBinaryBitsHex(int ch) {
           
             if(ch>=48&ch<=57)
                 ch-=48;
             else ch-=55;
          
        byte[] bin=new byte[8];
        int tag=1;
        for(int i=0;i<8;i++) {
            bin[i]=(byte)((ch&((tag<<i)))>>i);
      
        }
          
        return bin;
    }
    public byte[] getBinaryBits(int ch) {
        byte[] bin=new byte[8];
        int tag=1;
        for(int i=0;i<8;i++) {
            bin[7-i]=(byte)((ch&((tag<<i)))>>i);
      
        }
        return bin;
    }
        
    public byte Xorbytegetter(String cell,String cell2) {
        
        elements=new byte[4];
        elements[0]=(byte)0;
        elements[1]=(byte)0;
        elements[2]=(byte)0;
        elements[3]=(byte)0;
        left= getBinaryBitsHex(cell.charAt(0));
        right=getBinaryBitsHex(cell.charAt(1));
        
        byte cellbyte=(byte)(left[3]*128+left[2]*64+left[1]*32+left[0]*16+right[3]*8+right[2]*4+right[1]*2+right[0]*1);
         
        left2=getBinaryBitsHex((cell2.charAt(0)));
        right2=getBinaryBitsHex(cell2.charAt(1));
     
        byte cellbyte2=(byte)(left2[3]*128+left2[2]*64+left2[1]*32+left2[0]*16+right2[3]*8+right2[2]*4+right2[1]*2+right2[0]*1);
         
         
        byte xorbyte;
          
        if(right[0]==1) {
           
            System.out.println("1");
            elements[0]=cellbyte2;
        }
        if(right[1]==1) {
            System.out.println("2");
            elements[1]=xorByIndex(cellbyte2,1);
        }
        if(right[2]==1) {
            System.out.println("3");
            elements[2]=xorByIndex(cellbyte2,2);
        }
        if(right[3]==1) {
            System.out.println("4");
            elements[3]=xorByIndex(cellbyte2,3);
        }
         
        System.out.println(right[0]+" "+right[1]+" "+right[2]+" "+right[3]);
      
        return XorAllBytes(elements);
         
    }
   
     public byte xorByIndex(byte mydata,int shifts) {
        byte returnbyte=mydata;
        byte[] bits=new byte[8];
        byte constant=27;
        
        while(shifts>0) {
            bits=getBinaryBits(returnbyte);
            returnbyte=(byte)(returnbyte<<1);
            if(bits[0]==1) {
                returnbyte=(byte)((returnbyte) ^ constant);
            }
            shifts--;
        }
        return returnbyte;
    }
     
    public byte XorAllBytes(byte[] data) {
        byte allxor=(byte)(((data[0]^data[1])^(data[2])^data[3]));
        return allxor;
    }
    
    public String XorAll(byte[] data) {
        
       
        byte allxor=(byte)(((data[0]^data[1])^(data[2])^data[3]));
         
        byte[] bits=getBinaryBits(allxor);
        left_value=bits[3]*1+bits[2]*2+bits[1]*4+bits[0]*8;
        right_value=bits[7]*1+bits[6]*2+bits[5]*4+bits[4]*8;
          
          
        
        return ht.getHex(left_value)+ht.getHex(right_value);
    }
}
