package avaj;

import avaj.MyException;
import java.util.regex.*;
import java.io.*;

public class Lexer {
    private boolean         valid;
    private int             runTime;

    protected int           numOfAirplanes;
    protected Matcher[]     airplanes;

    Lexer(String filename){
        try {

            Pattern         p1 = Pattern.compile("^\\s*(\\d+)\\s*$");
            Pattern         p2 = Pattern.compile("^\\s*(Baloon|JetPlane|Helicopter)\\s+(\\w+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s*$");
            Matcher         m;

            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = br.readLine()) != null){
                numOfAirplanes++;
            }
            numOfAirplanes--;

            br = new BufferedReader(new FileReader(filename));

            if ((line = br.readLine()) != null){
                m = p1.matcher(line);
                if (!m.matches())
                    throw new MyException("\033[0;31mIncorrect Runtime Format : " + line);
                runTime = Integer.parseInt(line);
            }

            airplanes = new Matcher[numOfAirplanes];
            int i = 0;

            while ((line = br.readLine()) != null){
                m = p2.matcher(line);

                if (m.find()){
                    airplanes[i++] = m;
                }

                if (!m.matches())
                    throw new MyException("\033[0;31mIncorrect Airplane Format : " + line);
            }

            br.close();
            valid = true;
        }catch (IOException e){
            valid = false;
            System.out.println(e.getMessage());
        }catch (MyException e){
            valid = false;
            System.out.println(e.getMessage());
        }
    }

    public boolean      isValid(){
        return valid;
    }

    public int          getRunTime(){
        return runTime;
    }

}