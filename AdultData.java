
package javaapplication2;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
;
/**
 *
 * @author - Sushanth
 */

interface constants
{
    static int num = 32486;
//    static int num = 1000;
    static int noa = 5;
    static double corf = 0.14;
    static double levelcon[] = {0.25,0.5,1.0};
    static double pi = Math.PI;
}
class Data {
    
    public int age;
    public int edu_num;
    public int capital_gain;
    public int capital_loss;
    public int hoursperweek;
     public int agech;
     public int educh;
     public int capitalgainch;
     public int capitallossch;
     public int hpwch;
     
     int choosedata(int i)
     {
         String head2;
         switch(i)
                    {
                        case 0:
                            head2 = "Age";
                            return this.age;
                        case 1:
                            head2 = "Education";
                            return this.edu_num;
                        case 2:
                            head2 = "Capital-Gain";
                            return this.capital_gain;
                        case 3:
                            head2 = "Capital-Loss";
                            return this.capital_loss;
                        case 4:
                            head2 = "Hours-Per-Week";
                            return this.hoursperweek;
                        default:
                            return -1;     
                    }
     }
     
     int privacyhoice(int i)
     {
         switch(i)
         {
             case 0: return this.agech;
             case 1: return this.educh;
             case 2: return this.capitalgainch;
             case 3: return this.capitallossch;
             case 4: return this.hpwch;
             default: return -1;
         }
     }
}

class Data2
{
    public double age;
    public double edu_num;
    public double capital_gain;
    public double capital_loss;
    public double hoursperweek;
    
    void putdata(int i, double k)
    {
        switch(i)
        {
            case 0: this.age = k; 
                    break;
            case 1: this.edu_num = k; 
                    break;
            case 2: this.capital_gain = k; 
                    break;
            case 3: this.capital_loss = k; 
                    break;
            case 4: this.hoursperweek = k; 
                    break;
            default: break;
        }
    }
}
class ReadCSV implements constants {
    
        
  public static void readCVS(Data [] obj){
    
    String csvFile = "adults_final.csv";
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";
    int i=0;
    for(int k=0;k<num;k++)
        obj[k]=new Data();

	try {

		br = new BufferedReader(new FileReader(csvFile));
                line = br.readLine();
		while (i<num) {
                    line = br.readLine();
		String[] DataSet = line.split(csvSplitBy);
                obj[i].age = Integer.parseInt(DataSet[0]);
                obj[i].agech = Integer.parseInt(DataSet[1]);
                obj[i].edu_num=Integer.parseInt(DataSet[2]);
                obj[i].educh=Integer.parseInt(DataSet[3]);
                obj[i].capital_gain=Integer.parseInt(DataSet[4]);
                obj[i].capitalgainch=Integer.parseInt(DataSet[5]);
                obj[i].capital_loss=Integer.parseInt(DataSet[6]);
                obj[i].capitallossch=Integer.parseInt(DataSet[7]);
                obj[i].hoursperweek=Integer.parseInt(DataSet[8]);
                obj[i].hpwch=Integer.parseInt(DataSet[9]);
                i++;

                }

	} 
        catch (FileNotFoundException e) {
	} catch (IOException e) {
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
			}
		}
//        for(int j=0;j<10;j++)
//            System.out.println(obj[j].age+" "+obj[j].agech);
//	System.out.println("File Reading Done \nRead "+num+" records");
  }
  
  }
}

class Correlation implements constants
{
    private static double r[][] = new double[5][5];
    private static int count=0;
    
    Correlation()
    {
        for(int i=0;i<5;i++)
        {
            
            for(int j=0;j<5;j++)
            {
                r[i][j]=0.0;
            }
        }
    }
    
    public double[][] QuantCorrelation(Data[] obj)
    {
        String head1="",head2="";
        int x=0,y=0;
      
        for(int i=0;i<5;i++)
        {
            for(int j=i+1;j<5;j++)
            {
                double ex=0,ey=0,exy=0,ex2=0,ey2=0;
                for(int k=0;k<num;k++)
                {
                    switch(i)
                    {
                        case 0:
                            head1 = "Age";
                            x=obj[k].age;
                            break;
                        case 1:
                            head1 = "Education";
                            x=obj[k].edu_num;
                            break;
                        case 2:
                            head1 = "Capital-Gain";
                            x=obj[k].capital_gain;
                            break;
                        case 3:
                            head1 = "Capital-Loss";
                            x=obj[k].capital_loss;
                            break;
                        case 4:
                            head1 = "Hours-Per-Week";
                            x=obj[k].hoursperweek;
                            break;
                        default:
                            break;
                            
                    }
                    
                    switch(j)
                    {
                        case 0:
                            head2 = "Age";
                            y=obj[k].age;
                            break;
                        case 1:
                            head2 = "Education";
                            y=obj[k].edu_num;
                            break;
                        case 2:
                            head2 = "Capital-Gain";
                            y=obj[k].capital_gain;
                            break;
                        case 3:
                            head2 = "Capital-Loss";
                            y=obj[k].capital_loss;
                            break;
                        case 4:
                            head2 = "Hours-Per-Week";
                            y=obj[k].hoursperweek;
                            break;
                        default:
                            break;       
                    }
                    
                    ex = ex+x;
                    ey = ey+y;
                    exy = exy+(x*y);
                    ex2 = ex2+(x*x);
                    ey2 = ey2+(y*y);
                    
                }
                r[i][j] = (((double)num*exy)-(ex*ey))/Math.sqrt((((double)num*ex2)-(ex*ex))*(((double)num*ey2)-(ey*ey)));
                r[j][i] = r[i][j];
//                System.out.println(head1+" and "+head2+" is "+r[i][j]);
                count++;
            }
        }
    return r;
    }
}

class FormMatrix implements constants
{
    private static int matrix[][][] = new int[5][num][3];
    public static int threshold[] = new int[5];
    private static Data[] obj;
    private static double[][] cor;
    FormMatrix(Data[] p, double[][] r)
    {
        threshold[0] = 40;
        threshold[1] = 10;
        threshold[2] = 0;
        threshold[3] = 0;
        threshold[4] = 40;
        obj = p;
        cor = r;
    }
    
    int[][][] makeMatrix()
    {
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<num;j++)
            {
                matrix[i][j][0] = que1(i,j);
                matrix[i][j][1] = que2(i);
                matrix[i][j][2] = que3(i,j, matrix[i][j][1]);
                
            }
        }
        return matrix;
    }
    
    int que1(int i, int j)
    {
        int value = obj[j].choosedata(i);
        if(value>threshold[i])
            return 1;
        else
            return 0;
    }
    
    int que2(int i)
    {
        int value = 0;
        for(int j=1;j<5;j++)
        {
            if(cor[i][j]>=corf) {
                value = 1;
            }
        }
        return value;
    }
    
    int que3(int i, int j, int ch)
    {
        int value = 0, temp =0;
        if(ch == 0)
            return obj[j].privacyhoice(i);
        else
        {
            for(int k=1;k<5;k++)
            {
                if(cor[i][k]>=corf)
                {
                    if(obj[j].privacyhoice(i)==obj[j].privacyhoice(k))
                    value = 1;
                }
            }
            return value;
        }
        
    }
    
}

class FormPrivacyLevel implements constants
{
    public int[][] formtable(int[][][] pm)
    {
        int plevel[][] = new int[num][noa];
        for(int i=0;i<num;i++)
        {
            for(int j=0;j<5;j++)
            {
                if(pm[j][i][0]==1)
                    plevel[i][j]=2;
                else if(pm[j][i][1]==1)
                    plevel[i][j]=1;
                else
                    plevel[i][j]=0;
            }
        }
        return plevel;
    }
}

class MeanSD implements constants
{
    public void mean(Data [] obj)
    {
        String head = new String();
        int x[] = new int[5];
        int x2[] = new int[5];
        double mean[] = new double[5];
        double sd[] = new double[5];
        double sd2[] = new double[5];
        double var[] = new double[5];
        System.out.println("\n\nNow for Mean and SD");
        for(int k=0;k<5;k++)
        {
            x[k]=0;
            x2[k]=0;
            mean[k]=0.0;
            sd[k]=0.0;
            sd2[k]=0.0;
            var[k]=0.0;
        }
        for(int i=0;i<5;i++)
        {
            switch(i)
                    {
                        case 0:
                            head = "Age";
                            break;
                        case 1:
                            head = "Education";
                            break;
                        case 2:
                            head = "Capital-Gain";
                            break;
                        case 3:
                            head = "Capital-Loss";
                            break;
                        case 4:
                            head = "Hours-Per-Week";
                            break;
                        default:
                            break;
                            
                    }
            for(int j=0;j<num;j++)
            {
                int temp = obj[j].choosedata(i);
               x[i] = x[i] + temp;
//               x2[i] = x2[i]+(temp*temp);
               
            }
            mean[i] = (double)x[i]/num;
            for(int s=0;s<num;s++)
            {
                double diff = obj[s].choosedata(i) - mean[i];
                var[i] = var[i]+ (diff*diff);
                        
            }
            sd[i] = Math.sqrt(var[i]/(double)num); 
//            sd2[i] = Math.sqrt((x2[i]-(mean[i]*mean[i]))/(double)num);
           
//            System.out.println(head+" Mean= "+mean[i]+" SD= "+sd[i]);
            
        }
        
    }
}

class NoiseAddition implements constants{
    
    private static double mean[][] = new double[noa][3];
    private static double sd[][] = new double[noa][3];
    private static Data[] obj;
    private static int[][] plevel;
    NoiseAddition(Data[] p, int[][] ptable)
    {
        obj=p;
        plevel=ptable;
    }
    private void findmean()
    {
        int x[][] = new int[noa][3];
        double var[][] = new double[noa][3];
        for(int b=0;b<noa;b++)
            for(int c=0;c<3;c++)
            {
                x[b][c] = 0;
                var[b][c] = 0.0;
            }
        
        for(int i=0;i<noa;i++)
        {
            for(int j=0;j<num;j++)
            {
                int temp = obj[j].choosedata(i);
                int level = plevel[j][i];
                x[i][level] = x[i][level]+temp;
            }
            for(int k=0;k<3;k++)
            {
                mean[i][k] = (double)x[i][k]/num;
            }
            for(int s=0;s<num;s++)
            {
                int level = plevel[s][i];
                double diff = obj[s].choosedata(i) - mean[i][level];
                var[i][level] = var[i][level] + (diff*diff);
                
            }
            for(int a=0;a<3;a++)
            {
                sd[i][a] = Math.sqrt(var[i][a]/(double)num);
            }
            
        }
        for(int d=0;d<3;d++)
        {
            System.out.println("For level "+d);
            for(int l=0;l<noa;l++)
            System.out.println(mean[l][d]+"\t"+sd[l][d]);
        }
    }
    
    public void addnoise(Data2[] result)
    {
        findmean();
        for(int s=0;s<num;s++)
        {
            result[s] = new Data2();
        }
//        System.out.println("\nNoise Components:");
        for(int i=0;i<num;i++)
        {
          for(int j=0;j<noa;j++)
          {   
              
              double x = obj[i].choosedata(j);
              int level = plevel[i][j];
              if(j==2 || j==3)
              {
                  result[i].putdata(j, x);
                  continue;
              }
//              double k = 1/(Math.sqrt((double)2*pi)*sd[j][level]);
              double temp = ((double)x-mean[j][level])/sd[j][level];
//              double z = (-1/2)*temp*temp;
//              double ncom = k*(Math.exp(z));
//              System.out.print(temp+"\t \t");
              double noise = temp + levelcon[level];
              double data = x + noise;
              result[i].putdata(j,data);
          }
//          System.out.println();
        }
        for(int a=0;a<50;a++)
        {
            System.out.println(result[a].age);
        }
    }
}

class WriteCSV implements constants
{
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    
    public static String FileHeader = "age,age_l,education-num,edu_l,capital-gain,gain_l,capital-loss,loss_l,hours-per-week,hpw_l";
    
    public void writeCSVfile(Data2[] obj, String FileName, int[][] plevel)
    {
        FileWriter fw;
        try {
            fw = new FileWriter(FileName);
            
            fw.append(FileHeader.toString());
            fw.append(NEW_LINE_SEPARATOR);
            
            for(int i=0;i<num;i++)
            {
                fw.append(String.valueOf(obj[i].age));
                fw.append(COMMA_DELIMITER);
                fw.append(String.valueOf(plevel[i][0]));
                fw.append(COMMA_DELIMITER);
                fw.append(String.valueOf(obj[i].edu_num));
                fw.append(COMMA_DELIMITER);
                fw.append(String.valueOf(plevel[i][1]));
                fw.append(COMMA_DELIMITER);
                fw.append(String.valueOf(obj[i].capital_gain));
                fw.append(COMMA_DELIMITER);
                fw.append(String.valueOf(plevel[i][2]));
                fw.append(COMMA_DELIMITER);
                fw.append(String.valueOf(obj[i].capital_loss));
                fw.append(COMMA_DELIMITER);
                fw.append(String.valueOf(plevel[i][3]));
                fw.append(COMMA_DELIMITER);
                fw.append(String.valueOf(obj[i].hoursperweek));
                fw.append(COMMA_DELIMITER);
                fw.append(String.valueOf(plevel[i][4]));
                fw.append(NEW_LINE_SEPARATOR);
            }
        fw.close();
        } catch (IOException ex) {
            Logger.getLogger(WriteCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("CSV File Created Succesfully");
    }
}

public class AdultData implements constants{

    public static void main(String[] args) {
        
        Data [] person = new Data[num];
        
        ReadCSV.readCVS(person);
        
        Correlation cor = new Correlation();
        double r[][];
        r = cor.QuantCorrelation(person);
        
        FormMatrix fm = new FormMatrix(person, r);
        int privacymatrix[][][] = fm.makeMatrix();
        
        MeanSD mean = new MeanSD();
        mean.mean(person);
        
        FormPrivacyLevel fpl = new FormPrivacyLevel();
        int plevel[][] = fpl.formtable(privacymatrix);
        
        for(int i=0;i<50;i++)
        {
            for(int j=0;j<5;j++)
            {
                System.out.print(plevel[i][j]+"\t");
            }
            System.out.println();
        }
        Data2 result[] = new Data2[num];
        NoiseAddition naa = new NoiseAddition(person,plevel);
        naa.addnoise(result);
        
        WriteCSV csv = new WriteCSV();
        csv.writeCSVfile(result, "pertubated_adult_levels.csv",plevel);
    }
    
}
