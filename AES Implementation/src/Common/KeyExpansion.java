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
import AESMain.MonitorPrinter;
import Text_to_Cipher.SBox;
import XOR_Operations.Operations;
public class KeyExpansion {
    String [][] words=new String[44][4];
    String [] temp=new String[4];
    String [] round_constant_table=new String[]  {"01","02","04","08","10","20","40","80","1b","36"};
    String [] round_constant=new  String[] {"00","00","00","00",};
    
    
    public KeyExpansion(){
    }
    
    
    public String[] rotateWord(String[] word) {
        String[] newword=new String[4]; 
        for(int i=0;i<3;i++)
            newword[i]=word[i+1];
        newword[3]= word[0];
        return newword;
    }
    
    
    public String[] substituteWord(String[] word) {
        String [] newword=new String[4];
        SBox sbox=new SBox();
        //System.out.println("Sbox ");
        for(int i=0;i<4;i++) {
            newword[i]=sbox.getSbox(word[i]);
        }
        return newword;
    }
    public String[] xor2words(String[] word1,String[] word2) {
        String [] newword=new String[4];
        Operations xor=new Operations();
        for(int i=0;i<4;i++) {
            newword[i]= xor.xor2hex(word1[i],word2[i]);
        }
        return newword;
    }
    public String[] RCxor(String[] word,int i) {
        round_constant[0]=round_constant_table[i-1];
        String[] w= xor2words(round_constant,word);
        return w;
    }
    
    
    public void keyExpansion(String[][] mykey){
        words[0]=mykey[0];
        words[1]=mykey[1];
        words[2]=mykey[2];
        words[3]=mykey[3]; 
        for(int i=4;i<44;i++) {
            temp=words[i-1];
            if(i%4==0){
                temp= RCxor((substituteWord(rotateWord(temp))),i/4);     
            }
            words[i]=xor2words(words[i-4],temp);
             
        }
        MonitorPrinter.print(words,"Expanded Key");
    }
    
    public String[][] getRoundKey(int round) {
        String[][] roundkey=new String[4][4];
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++) {
            roundkey[j][i]=words[i+((round-1)*4)][j];
            }
        return roundkey;
    }
}
