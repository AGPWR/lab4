import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class WindowAdapterDemo extends WindowAdapter{
    WindowAdapterDemo(){}
    public void windowClosing(WindowEvent e){System.exit(0);}
}


public class Pascal{
    public static void main(String[] args){
        Frame okno = new Frame("pascal");
        int rows;
        try{
            rows = Integer.parseInt(args[0]);
            Label [][] myLabels = new Label[rows][2*rows-1];
            Panel [] myPanels = new Panel[rows];

            for(int i = 0; i<rows; i++){
                myPanels[i] = new Panel();
                for(int j = 0; j<2*rows-1; j++){
                    myLabels[i][j] = new Label();
                    //myLabels[i][j].setText("");
                    myPanels[i].add(myLabels[i][j]);
                }
                okno.add(myPanels[i]);
            }

            int k = rows;
        
            for(int i = 0; i<rows; i++){
                try{
                    int counter = i+1;
                    int m = 0;
                    for(int j = k; j<2*rows && counter > 0; j +=2){
                        try{
                            Process p = Runtime.getRuntime().exec("a.exe "+ i +" "+ m);
                            
                            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                            String line = reader.readLine();
                            myLabels[i][j-1].setText(line);
                            counter--;
                            m++;
                        }catch(NumberFormatException | IOException ex){
                            System.out.println("Nieprawidlowy argument - " + m); 
                        }
                    }
                    k--;
                    counter++;
                }catch(NumberFormatException ex){
                    System.out.println("Nieprawidlowy argument - " + i);
                }
            }

            okno.setBounds(100, 100, rows*65, rows*50+30);
            okno.setLayout(new GridLayout(rows, 1));
            okno.setBackground(Color.black);
            okno.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
            okno.setForeground(Color.orange);
            okno.setVisible(true);
            okno.addWindowListener(new WindowAdapterDemo());
        
    
        }catch(NumberFormatException | NegativeArraySizeException | ArrayIndexOutOfBoundsException ex){
            System.out.println("Podano nieprawidlowy argument");
            System.exit(0);
        }
    }    
}
