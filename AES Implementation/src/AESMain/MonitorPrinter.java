/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AESMain;

/**
 *
 * @author monika
 */

public class MonitorPrinter {
    public MonitorPrinter(){
    }
    public static void print(String[][] str,String label) {
        Home.StepsText.append("\n -- "+label+" -- "+'\n');
      
        for(int i=0;i<str.length ;i++) {
            Home.StepsText.append("| ");
            for(int j=0;j<str[0].length;j++) {
                 
                Home.StepsText.append(str[i][j]+" ");
            }
            Home.StepsText.append("| "+'\n');
        }
    }
}
