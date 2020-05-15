/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Text_to_Cipher;
import XOR_Operations.Operations;
/**
 *
 * @author monika
 */

public class Conversion {
    private SBox s_box= new SBox();
    private Operations xor;
    private String[][] aftermix=new String[4][4];
    private byte[] data=new byte[4];
    public Conversion(){
    }
    public String[][] substitute_bytes(String state[][]){
        for(int i=0;i<4;i++) {   
            for(int j=0;j<4;j++) {
                state[i][j]=s_box.getSbox(state[i][j]);
                 
            }
             
        }
        return state;
    }
    public String[][] shiftRows(String state[][])
    {
       for(int i=1;i<4;i++)
       {
           state=rowShift(i,state);
       }
        return state;
    }
    public String[][] rowShift(int row,String[][] state)
    {
        String temp;
        for(int num=0;num<row;num++)
        {
            temp=state[row][0];
            for(int i=0;i<3;i++)
            {
                state[row][i]=state[row][i+1];
            }
            state[row][3]=temp;
        }
        return state;
    }
    public String[][] mixColumns(String state[][]){
        
        xor =new Operations();
        aftermix=new String[4][4];
        data=new byte[4];
     
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++) {
            for(int k=0;k<4;k++)
            {
            
                data[k]=xor.Xorbytegetter(trans[i][k],state[k][j]);
            }
            aftermix[i][j]=xor.XorAll(data);
            }
       
        return aftermix;
         
         
    }
    String[][] trans=new String[][]
    {{"02","03","01","01"},
     {"01","02","03","01"},
     {"01","01","02","03"},
     {"03","01","01","02"},
    };
     
     
        
    }

