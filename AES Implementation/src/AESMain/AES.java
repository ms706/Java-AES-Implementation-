package AESMain;

/**
 *
 * @author monika
 */
import Common.hextable;
import Common.KeyExpansion;
import Common.AddRoundKey;
import Text_to_Cipher.Conversion;
import Cipher_To_Text.ConversionToText;
public class AES {
    public static int ROUND=1;
    private String initial_text;
    private String initial_key;
    private String[][] state_text=new String[4][4];
    private String[][] dstate_text=new String[4][4];
    private String[][] key_table=new String[4][4];
    private hextable state_hex=new hextable();
    private hextable key_hex=new hextable();
    private KeyExpansion key_ex=new KeyExpansion();
    private AddRoundKey round_key=new AddRoundKey();
    private Conversion convert =new Conversion();
    private ConversionToText inv_convert=new ConversionToText(); 
    public AES(String key,String text){
        initial_key=key;
        initial_text=text;
        getHexString();
        getExpandedKey();
    }
    void getHexString(){
        key_table=key_hex.stringToHex(initial_key);
        state_text=state_hex.stringToHex(initial_text);
        dstate_text=state_text;
        MonitorPrinter.print(key_table,"Hexadecimal Key");
    }
    void getExpandedKey(){
        key_ex.keyExpansion(key_table);
    }
    
    public String aesCipherLoop(){
        Home.StepsText.append("===============CIPHER==============="+'\n');
        MonitorPrinter.print(state_text,"State Text");
        ROUND=1;
        MonitorPrinter.print(key_ex.getRoundKey(ROUND),"Round : "+ ROUND+" -- \n -- Round Key Value");
        state_text=round_key.AddRoundKey(state_text,key_ex.getRoundKey(ROUND));
        while(ROUND<=9) {
            MonitorPrinter.print(state_text,"Start of Round :");
            
            state_text=convert.substitute_bytes(state_text);
            MonitorPrinter.print(state_text,"After SubBytes");
            
            state_text=convert.shiftRows(state_text);
            MonitorPrinter.print(state_text,"After shiftrows");
            
            state_text=convert.mixColumns(state_text);
            MonitorPrinter.print(state_text,"Mix-columns");
            
            ROUND++;
            MonitorPrinter.print(key_ex.getRoundKey(ROUND),"Round  : "+ ROUND+" -- \n -- Round Key Value");
            state_text=round_key.AddRoundKey(state_text,key_ex.getRoundKey(ROUND));
        }
        state_text=convert.substitute_bytes(state_text);
        MonitorPrinter.print(state_text,"After SubBytes");
            
        state_text=convert.shiftRows(state_text);
        MonitorPrinter.print(state_text,"After shiftrows");
        
        ROUND++;
        MonitorPrinter.print(key_ex.getRoundKey(ROUND),"Round  : "+ ROUND+" -- \nRound Key Value");    
        state_text=round_key.AddRoundKey(state_text,key_ex.getRoundKey(ROUND));
        
        MonitorPrinter.print(state_text,"CIPHER TEXT");
        MonitorPrinter.print(dstate_text,"Original TEXT");
        String temp=state_hex.hexToString(state_text);
        System.out.println(temp);
        return temp;   
    }
    public String aesDecipherLoop(){
        
        MonitorPrinter.print(state_text,"CIPHER Text");
        ROUND=11;
        MonitorPrinter.print(key_ex.getRoundKey(ROUND),"Round Key Value");
        state_text=round_key.AddRoundKey(state_text,key_ex.getRoundKey(ROUND));
        MonitorPrinter.print(state_text,"After Add round key");
        ROUND--;
        while(ROUND>1){
            state_text=inv_convert.invShiftRows(state_text);
            MonitorPrinter.print(state_text,"After Inverse Shift Rows");
             
            state_text=inv_convert.invSubstituteBytes(state_text);
            MonitorPrinter.print(state_text,"After Inverse Substitution Bytes");
            
            MonitorPrinter.print(key_ex.getRoundKey(ROUND),"Round key Value");
            state_text=round_key.AddRoundKey(state_text,key_ex.getRoundKey(ROUND));
            
            MonitorPrinter.print(state_text,"After Add round key");
            state_text=inv_convert.invMixColumns(state_text);
            MonitorPrinter.print(state_text,"invMix-columns");
            ROUND--;
        }
        state_text=inv_convert.invShiftRows(state_text);
        MonitorPrinter.print(state_text,"After Inverse Shift Rows");
        state_text=inv_convert.invSubstituteBytes(state_text);
        MonitorPrinter.print(state_text,"After Inverse Substitution Bytes");
        MonitorPrinter.print(key_ex.getRoundKey(ROUND),"Round key Value");
        state_text=round_key.AddRoundKey(state_text,key_ex.getRoundKey(ROUND));
        MonitorPrinter.print(state_text,"PlainText");    
        String temp=state_hex.hexToString(state_text);
        return temp;
    }
    
}
