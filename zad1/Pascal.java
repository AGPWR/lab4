import java.awt.*;
import java.awt.event.*;


class Exception1 extends Exception{};
class Exception2 extends Exception{};

class WierszTrojkataPascala{
    private int[] tablica;

    int silnia(int n){
        int suma = 1;
        for(int i = n; i>1; i--)
            suma *= i;
        return suma;
    }

    int dwumianNewtona(int n, int k){
        return silnia(n) / (silnia(k)* silnia(n-k));
    }

    WierszTrojkataPascala(int n) throws Exception1{
        if(n < 0) throw new Exception1();
        this.tablica = new int[n+1];
        for(int i = 0; i <= n; i++){
            this.tablica[i] = dwumianNewtona(n,i);
        }
    }

    public int wspolczynnik(int m) throws Exception2{
        if(m < 0 || m > this.tablica.length) throw new Exception2();
        return this.tablica[m];
    }
}

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
                    WierszTrojkataPascala tp = new WierszTrojkataPascala(i);
                    int counter = i+1;
                    int m = 0;
                    for(int j = k; j<2*rows && counter > 0; j +=2){
                        try{
                            myLabels[i][j-1].setText(Integer.toString(tp.wspolczynnik(m)));
                            counter--;
                            m++;
                        }catch(Exception2 | NumberFormatException ex){
                            System.out.println("Nieprawidlowy argument - " + m); 
                        }
                    }
                    k--;
                    counter++;
                }catch(Exception1 | NumberFormatException ex){
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