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

import XOR_Operations.Operations;
public class AddRoundKey {
    public AddRoundKey() {
    }
    public String[][]  AddRoundKey(String[][] state_text,String[][] key) {
        Operations xor=new Operations();
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                state_text[i][j]=xor.xor2hex(state_text[i][j],key[i][j]);
        return state_text;
    }
}
