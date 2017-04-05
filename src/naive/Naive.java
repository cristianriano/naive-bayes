/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package naive;

import conversion.arff2Base;
import entidad.Paciente;
import java.util.List;
import probabilidades.ajusteWeka;
import probabilidades.probabilidades;

/**
 *
 * 
 * @author Cristian
 */
public class Naive {
    
    private static double probDrugY, probDrugX, probDrugA, probDrugB, probDrugC; //Probabilidades a priori
    private static int total=0;
    
    private static probabilidades p;
    private static ajusteWeka a;
    private static arff2Base conversor;
    
    private static double probCondFDrugA, probCondMDrugA; //Probabilidades de drugA dado Femenino y Masculino
    private static double probCondFDrugB, probCondMDrugB;
    private static double probCondFDrugC, probCondMDrugC;
    private static double probCondFDrugX, probCondMDrugX;
    private static double probCondFDrugY, probCondMDrugY;
    
    private static double probCondBPHighDrugA, probCondBPLowDrugA, probCondBPNormalDrugA; //Probabilidades de drugA dado BP = HIGH, NORMAL O LOW
    private static double probCondBPHighDrugB, probCondBPLowDrugB, probCondBPNormalDrugB;
    private static double probCondBPHighDrugC, probCondBPLowDrugC, probCondBPNormalDrugC;
    private static double probCondBPHighDrugX, probCondBPLowDrugX, probCondBPNormalDrugX;
    private static double probCondBPHighDrugY, probCondBPLowDrugY, probCondBPNormalDrugY;
    
    private static double probCondChHighDrugA, probCondChLowDrugA; //Probabilidades de drugA dado Cholesterol = HIGH O LOW
    private static double probCondChHighDrugB, probCondChLowDrugB;
    private static double probCondChHighDrugC, probCondChLowDrugC;
    private static double probCondChHighDrugX, probCondChLowDrugX;
    private static double probCondChHighDrugY, probCondChLowDrugY;
    
    private static List<Double> NaDrugA, NaDrugB, NaDrugC, NaDrugX, NaDrugY;
    private static List<Double> KDrugA, KDrugB, KDrugC, KDrugX, KDrugY;
    private static List<Double> AgeDrugA, AgeDrugB, AgeDrugC, AgeDrugX, AgeDrugY;
    
    private static double precisionNa, precisionK, precisionAge;
    
    private static double mediaNaDrugA, desvNaDrugA;
    private static double mediaNaDrugB, desvNaDrugB;
    private static double mediaNaDrugC, desvNaDrugC;
    private static double mediaNaDrugX, desvNaDrugX;
    private static double mediaNaDrugY, desvNaDrugY;
    
    private static double mediaKDrugA, desvKDrugA;
    private static double mediaKDrugB, desvKDrugB;
    private static double mediaKDrugC, desvKDrugC;
    private static double mediaKDrugX, desvKDrugX;
    private static double mediaKDrugY, desvKDrugY;
    
    private static double mediaAgeDrugA, desvAgeDrugA;
    private static double mediaAgeDrugB, desvAgeDrugB;
    private static double mediaAgeDrugC, desvAgeDrugC;
    private static double mediaAgeDrugX, desvAgeDrugX;
    private static double mediaAgeDrugY, desvAgeDrugY;
    
    public void ajustar(){
        p = new probabilidades();
        a = new ajusteWeka();
        
        total = p.numeroTotal();
        probDrugA = p.probabilidadPriori("drugA", total);
        probDrugB = p.probabilidadPriori("drugB", total);
        probDrugC = p.probabilidadPriori("drugC", total);
        probDrugX = p.probabilidadPriori("drugX", total);
        probDrugY = p.probabilidadPriori("drugY", total);
        
        probCondFDrugA = p.probabilidadCondicionalSex("drugA", "F");
        probCondMDrugA = p.probabilidadCondicionalSex("drugA", "M");
        probCondFDrugB = p.probabilidadCondicionalSex("drugB", "F");
        probCondMDrugB = p.probabilidadCondicionalSex("drugB", "M");
        probCondFDrugC = p.probabilidadCondicionalSex("drugC", "F");
        probCondMDrugC = p.probabilidadCondicionalSex("drugC", "M");
        probCondFDrugX = p.probabilidadCondicionalSex("drugX", "F");
        probCondMDrugX = p.probabilidadCondicionalSex("drugX", "M");
        probCondFDrugY = p.probabilidadCondicionalSex("drugY", "F");
        probCondMDrugY = p.probabilidadCondicionalSex("drugY", "M");
        
        probCondBPHighDrugA = p.probabilidadCondicionalBP("drugA", "HIGH");
        probCondBPNormalDrugA = p.probabilidadCondicionalBP("drugA", "NORMAL");
        probCondBPLowDrugA = p.probabilidadCondicionalBP("drugA", "LOW");
        probCondBPHighDrugB = p.probabilidadCondicionalBP("drugB", "HIGH");
        probCondBPNormalDrugB = p.probabilidadCondicionalBP("drugB", "NORMAL");
        probCondBPLowDrugB = p.probabilidadCondicionalBP("drugB", "LOW");
        probCondBPHighDrugC = p.probabilidadCondicionalBP("drugC", "HIGH");
        probCondBPNormalDrugC = p.probabilidadCondicionalBP("drugC", "NORMAL");
        probCondBPLowDrugC = p.probabilidadCondicionalBP("drugC", "LOW");
        probCondBPHighDrugX = p.probabilidadCondicionalBP("drugX", "HIGH");
        probCondBPNormalDrugX = p.probabilidadCondicionalBP("drugX", "NORMAL");
        probCondBPLowDrugX = p.probabilidadCondicionalBP("drugX", "LOW");
        probCondBPHighDrugY = p.probabilidadCondicionalBP("drugY", "HIGH");
        probCondBPNormalDrugY = p.probabilidadCondicionalBP("drugY", "NORMAL");
        probCondBPLowDrugY = p.probabilidadCondicionalBP("drugY", "LOW");
        
        probCondChHighDrugA = p.probabilidadCondicionalCholesterol("drugA", "HIGH");
        probCondChLowDrugA = p.probabilidadCondicionalCholesterol("drugA", "NORMAL");
        probCondChHighDrugB = p.probabilidadCondicionalCholesterol("drugB", "HIGH");
        probCondChLowDrugB = p.probabilidadCondicionalCholesterol("drugB", "NORMAL");
        probCondChHighDrugC = p.probabilidadCondicionalCholesterol("drugC", "HIGH");
        probCondChLowDrugC = p.probabilidadCondicionalCholesterol("drugC", "NORMAL");
        probCondChHighDrugX = p.probabilidadCondicionalCholesterol("drugX", "HIGH");
        probCondChLowDrugX = p.probabilidadCondicionalCholesterol("drugX", "NORMAL");
        probCondChHighDrugY = p.probabilidadCondicionalCholesterol("drugY", "HIGH");
        probCondChLowDrugY = p.probabilidadCondicionalCholesterol("drugY", "NORMAL");
        
        precisionNa=a.calcularPrecisionNa();
        precisionK=a.calcularPrecisionK();
        precisionAge=a.calcularPrecisionAge();
        
        NaDrugA=a.redondearNa(precisionNa, "drugA");
        KDrugA=a.redondearK(precisionK, "drugA");
        AgeDrugA=a.redondearAge(precisionAge, "drugA");
        
        NaDrugB=a.redondearNa(precisionNa, "drugB");
        KDrugB=a.redondearK(precisionK, "drugB");
        AgeDrugB=a.redondearAge(precisionAge, "drugB");
        
        NaDrugC=a.redondearNa(precisionNa, "drugC");
        KDrugC=a.redondearK(precisionK, "drugC");
        AgeDrugC=a.redondearAge(precisionAge, "drugC");
        
        NaDrugX=a.redondearNa(precisionNa, "drugX");
        KDrugX=a.redondearK(precisionK, "drugX");
        AgeDrugX=a.redondearAge(precisionAge, "drugX");
        
        NaDrugY=a.redondearNa(precisionNa, "drugY");
        KDrugY=a.redondearK(precisionK, "drugY");
        AgeDrugY=a.redondearAge(precisionAge, "drugY");
        
        mediaNaDrugA = p.promedio(NaDrugA);
        mediaKDrugA = p.promedio(KDrugA);
        mediaAgeDrugA = p.promedio(AgeDrugA);
        
        mediaNaDrugB = p.promedio(NaDrugB);
        mediaKDrugB = p.promedio(KDrugB);
        mediaAgeDrugB = p.promedio(AgeDrugB);
        
        mediaNaDrugC = p.promedio(NaDrugC);
        mediaKDrugC = p.promedio(KDrugC);
        mediaAgeDrugC = p.promedio(AgeDrugC);
        
        mediaNaDrugX = p.promedio(NaDrugX);
        mediaKDrugX = p.promedio(KDrugX);
        mediaAgeDrugX = p.promedio(AgeDrugX);
        
        mediaNaDrugY = p.promedio(NaDrugY);
        mediaKDrugY = p.promedio(KDrugY);
        mediaAgeDrugY = p.promedio(AgeDrugY);
        
        desvAgeDrugA = p.desviacion(AgeDrugA, mediaAgeDrugA);
        desvNaDrugA = p.desviacion(NaDrugA, mediaNaDrugA);
        desvKDrugA = p.desviacion(KDrugA, mediaKDrugA);
        
        desvAgeDrugB = p.desviacion(AgeDrugB, mediaAgeDrugB);
        desvNaDrugB = p.desviacion(NaDrugB, mediaNaDrugB);
        desvKDrugB = p.desviacion(KDrugB, mediaKDrugB);
        
        desvAgeDrugC = p.desviacion(AgeDrugC, mediaAgeDrugC);
        desvNaDrugC = p.desviacion(NaDrugC, mediaNaDrugC);
        desvKDrugC = p.desviacion(KDrugC, mediaKDrugC);
        
        desvAgeDrugX = p.desviacion(AgeDrugX, mediaAgeDrugX);
        desvNaDrugX = p.desviacion(NaDrugX, mediaNaDrugX);
        desvKDrugX = p.desviacion(KDrugX, mediaKDrugX);
        
        desvAgeDrugY = p.desviacion(AgeDrugY, mediaAgeDrugY);
        desvNaDrugY = p.desviacion(NaDrugY, mediaNaDrugY);
        desvKDrugY = p.desviacion(KDrugY, mediaKDrugY);
    }
    
    public String recomendar(Paciente p){
        double a=0,b=0,c=0,x=0,y=0,t=0;
        String droga="";
        if(p.getSex().equals("M")){
            if(p.getCholesterol().equals("HIGH")){
                if(p.getBp().equals("HIGH")){
                    a=probCondMDrugA*probDrugA*probCondBPHighDrugA*probCondChHighDrugA*normal(mediaNaDrugA, desvNaDrugA, p.getNa())*
                            normal(mediaKDrugA, desvKDrugA, p.getK())*normal(mediaAgeDrugA, desvAgeDrugA, p.getAge());
                    b=probCondMDrugB*probDrugB*probCondBPHighDrugB*probCondChHighDrugB*normal(mediaNaDrugB, desvNaDrugB, p.getNa())*
                            normal(mediaKDrugB, desvKDrugB, p.getK())*normal(mediaAgeDrugB, desvAgeDrugB, p.getAge());
                    c=probCondMDrugC*probDrugC*probCondBPHighDrugC*probCondChHighDrugC*normal(mediaNaDrugC, desvNaDrugC, p.getNa())*
                            normal(mediaKDrugC, desvKDrugC, p.getK())*normal(mediaAgeDrugC, desvAgeDrugC, p.getAge());
                    x=probCondMDrugX*probDrugX*probCondBPHighDrugX*probCondChHighDrugX*normal(mediaNaDrugX, desvNaDrugX, p.getNa())*
                            normal(mediaKDrugX, desvKDrugX, p.getK())*normal(mediaAgeDrugX, desvAgeDrugX, p.getAge());
                    y=probCondMDrugY*probDrugY*probCondBPHighDrugY*probCondChHighDrugY*normal(mediaNaDrugY, desvNaDrugY, p.getNa())*
                            normal(mediaKDrugY, desvKDrugY, p.getK())*normal(mediaAgeDrugY, desvAgeDrugY, p.getAge());
                    t=a+b+c+x+y;
                    a=a/t; b=b/t; c=c/t; x=x/t; y=y/t;
                    System.out.println("-------------------------------");
                    System.out.println(a);
                    System.out.println(b);
                    System.out.println(c);
                    System.out.println(x);
                    System.out.println(y);
                    System.out.println(t);
                    System.out.println("-------------------------------");
                }
                else if(p.getBp().equals("NORMAL")){
                    a=probCondMDrugA*probDrugA*probCondBPNormalDrugA*probCondChHighDrugA*normal(mediaNaDrugA, desvNaDrugA, p.getNa())*
                            normal(mediaKDrugA, desvKDrugA, p.getK())*normal(mediaAgeDrugA, desvAgeDrugA, p.getAge());
                    b=probCondMDrugB*probDrugB*probCondBPNormalDrugB*probCondChHighDrugB*normal(mediaNaDrugB, desvNaDrugB, p.getNa())*
                            normal(mediaKDrugB, desvKDrugB, p.getK())*normal(mediaAgeDrugB, desvAgeDrugB, p.getAge());
                    c=probCondMDrugC*probDrugC*probCondBPNormalDrugC*probCondChHighDrugC*normal(mediaNaDrugC, desvNaDrugC, p.getNa())*
                            normal(mediaKDrugC, desvKDrugC, p.getK())*normal(mediaAgeDrugC, desvAgeDrugC, p.getAge());
                    x=probCondMDrugX*probDrugX*probCondBPNormalDrugX*probCondChHighDrugX*normal(mediaNaDrugX, desvNaDrugX, p.getNa())*
                            normal(mediaKDrugX, desvKDrugX, p.getK())*normal(mediaAgeDrugX, desvAgeDrugX, p.getAge());
                    y=probCondMDrugY*probDrugY*probCondBPNormalDrugY*probCondChHighDrugY*normal(mediaNaDrugY, desvNaDrugY, p.getNa())*
                            normal(mediaKDrugY, desvKDrugY, p.getK())*normal(mediaAgeDrugY, desvAgeDrugY, p.getAge());
                    t=a+b+c+x+y;
                    a=a/t; b=b/t; c=c/t; x=x/t; y=y/t;
                }
                else{
                    
                    a=probCondMDrugA*probDrugA*probCondBPLowDrugA*probCondChHighDrugA*normal(mediaNaDrugA, desvNaDrugA, p.getNa())*
                            normal(mediaKDrugA, desvKDrugA, p.getK())*normal(mediaAgeDrugA, desvAgeDrugA, p.getAge());
                    b=probCondMDrugB*probDrugB*probCondBPLowDrugB*probCondChHighDrugB*normal(mediaNaDrugB, desvNaDrugB, p.getNa())*
                            normal(mediaKDrugB, desvKDrugB, p.getK())*normal(mediaAgeDrugB, desvAgeDrugB, p.getAge());
                    c=probCondMDrugC*probDrugC*probCondBPLowDrugC*probCondChHighDrugC*normal(mediaNaDrugC, desvNaDrugC, p.getNa())*
                            normal(mediaKDrugC, desvKDrugC, p.getK())*normal(mediaAgeDrugC, desvAgeDrugC, p.getAge());
                    x=probCondMDrugX*probDrugX*probCondBPLowDrugX*probCondChHighDrugX*normal(mediaNaDrugX, desvNaDrugX, p.getNa())*
                            normal(mediaKDrugX, desvKDrugX, p.getK())*normal(mediaAgeDrugX, desvAgeDrugX, p.getAge());
                    y=probCondMDrugY*probDrugY*probCondBPLowDrugY*probCondChHighDrugY*normal(mediaNaDrugY, desvNaDrugY, p.getNa())*
                            normal(mediaKDrugY, desvKDrugY, p.getK())*normal(mediaAgeDrugY, desvAgeDrugY, p.getAge());
                    t=a+b+c+x+y;
                    a=a/t; b=b/t; c=c/t; x=x/t; y=y/t; 
                }
            }
            else{
              if(p.getBp().equals("HIGH")){
                    a=probCondMDrugA*probDrugA*probCondBPHighDrugA*probCondChLowDrugA*normal(mediaNaDrugA, desvNaDrugA, p.getNa())*
                            normal(mediaKDrugA, desvKDrugA, p.getK())*normal(mediaAgeDrugA, desvAgeDrugA, p.getAge());
                    b=probCondMDrugB*probDrugB*probCondBPHighDrugB*probCondChLowDrugB*normal(mediaNaDrugB, desvNaDrugB, p.getNa())*
                            normal(mediaKDrugB, desvKDrugB, p.getK())*normal(mediaAgeDrugB, desvAgeDrugB, p.getAge());
                    c=probCondMDrugC*probDrugC*probCondBPHighDrugC*probCondChLowDrugC*normal(mediaNaDrugC, desvNaDrugC, p.getNa())*
                            normal(mediaKDrugC, desvKDrugC, p.getK())*normal(mediaAgeDrugC, desvAgeDrugC, p.getAge());
                    x=probCondMDrugX*probDrugX*probCondBPHighDrugX*probCondChLowDrugX*normal(mediaNaDrugX, desvNaDrugX, p.getNa())*
                            normal(mediaKDrugX, desvKDrugX, p.getK())*normal(mediaAgeDrugX, desvAgeDrugX, p.getAge());
                    y=probCondMDrugY*probDrugY*probCondBPHighDrugY*probCondChLowDrugY*normal(mediaNaDrugY, desvNaDrugY, p.getNa())*
                            normal(mediaKDrugY, desvKDrugY, p.getK())*normal(mediaAgeDrugY, desvAgeDrugY, p.getAge());
                    t=a+b+c+x+y;
                    a=a/t; b=b/t; c=c/t; x=x/t; y=y/t;
                }
              else if(p.getBp().equals("NORMAL")){
                    a=probCondMDrugA*probCondFDrugA*probDrugA*probCondBPNormalDrugA*probCondChLowDrugA*normal(mediaNaDrugA, desvNaDrugA, p.getNa())*
                            normal(mediaKDrugA, desvKDrugA, p.getK())*normal(mediaAgeDrugA, desvAgeDrugA, p.getAge());
                    b=probCondMDrugB*probDrugB*probCondBPNormalDrugB*probCondChLowDrugB*normal(mediaNaDrugB, desvNaDrugB, p.getNa())*
                            normal(mediaKDrugB, desvKDrugB, p.getK())*normal(mediaAgeDrugB, desvAgeDrugB, p.getAge());
                    c=probCondMDrugC*probDrugC*probCondBPNormalDrugC*probCondChLowDrugC*normal(mediaNaDrugC, desvNaDrugC, p.getNa())*
                            normal(mediaKDrugC, desvKDrugC, p.getK())*normal(mediaAgeDrugC, desvAgeDrugC, p.getAge());
                    x=probCondMDrugX*probDrugX*probCondBPNormalDrugX*probCondChLowDrugX*normal(mediaNaDrugX, desvNaDrugX, p.getNa())*
                            normal(mediaKDrugX, desvKDrugX, p.getK())*normal(mediaAgeDrugX, desvAgeDrugX, p.getAge());
                    y=probCondMDrugY*probDrugY*probCondBPNormalDrugY*probCondChLowDrugY*normal(mediaNaDrugY, desvNaDrugY, p.getNa())*
                            normal(mediaKDrugY, desvKDrugY, p.getK())*normal(mediaAgeDrugY, desvAgeDrugY, p.getAge());
                    t=a+b+c+x+y;
                    a=a/t; b=b/t; c=c/t; x=x/t; y=y/t;
                }
                else{
                    a=probCondMDrugA*probDrugA*probCondBPLowDrugA*probCondChLowDrugA*normal(mediaNaDrugA, desvNaDrugA, p.getNa())*
                            normal(mediaKDrugA, desvKDrugA, p.getK())*normal(mediaAgeDrugA, desvAgeDrugA, p.getAge());
                    b=probCondMDrugB*probDrugB*probCondBPLowDrugB*probCondChLowDrugB*normal(mediaNaDrugB, desvNaDrugB, p.getNa())*
                            normal(mediaKDrugB, desvKDrugB, p.getK())*normal(mediaAgeDrugB, desvAgeDrugB, p.getAge());
                    c=probCondMDrugC*probDrugC*probCondBPLowDrugC*probCondChLowDrugC*normal(mediaNaDrugC, desvNaDrugC, p.getNa())*
                            normal(mediaKDrugC, desvKDrugC, p.getK())*normal(mediaAgeDrugC, desvAgeDrugC, p.getAge());
                    x=probCondMDrugX*probDrugX*probCondBPLowDrugX*probCondChLowDrugX*normal(mediaNaDrugX, desvNaDrugX, p.getNa())*
                            normal(mediaKDrugX, desvKDrugX, p.getK())*normal(mediaAgeDrugX, desvAgeDrugX, p.getAge());
                    y=probCondMDrugY*probDrugY*probCondBPLowDrugY*probCondChLowDrugY*normal(mediaNaDrugY, desvNaDrugY, p.getNa())*
                            normal(mediaKDrugY, desvKDrugY, p.getK())*normal(mediaAgeDrugY, desvAgeDrugY, p.getAge());
                    t=a+b+c+x+y;
                    a=a/t; b=b/t; c=c/t; x=x/t; y=y/t; 
                }
            }
        }
        else{
       if(p.getCholesterol().equals("HIGH")){
                if(p.getBp().equals("HIGH")){
                    a=probCondFDrugA*probDrugA*probCondBPHighDrugA*probCondChHighDrugA*normal(mediaNaDrugA, desvNaDrugA, p.getNa())*
                            normal(mediaKDrugA, desvKDrugA, p.getK())*normal(mediaAgeDrugA, desvAgeDrugA, p.getAge());
                    b=probCondFDrugB*probDrugB*probCondBPHighDrugB*probCondChHighDrugB*normal(mediaNaDrugB, desvNaDrugB, p.getNa())*
                            normal(mediaKDrugB, desvKDrugB, p.getK())*normal(mediaAgeDrugB, desvAgeDrugB, p.getAge());
                    c=probCondFDrugC*probDrugC*probCondBPHighDrugC*probCondChHighDrugC*normal(mediaNaDrugC, desvNaDrugC, p.getNa())*
                            normal(mediaKDrugC, desvKDrugC, p.getK())*normal(mediaAgeDrugC, desvAgeDrugC, p.getAge());
                    x=probCondFDrugX*probDrugX*probCondBPHighDrugX*probCondChHighDrugX*normal(mediaNaDrugX, desvNaDrugX, p.getNa())*
                            normal(mediaKDrugX, desvKDrugX, p.getK())*normal(mediaAgeDrugX, desvAgeDrugX, p.getAge());
                    y=probCondFDrugY*probDrugY*probCondBPHighDrugY*probCondChHighDrugY*normal(mediaNaDrugY, desvNaDrugY, p.getNa())*
                            normal(mediaKDrugY, desvKDrugY, p.getK())*normal(mediaAgeDrugY, desvAgeDrugY, p.getAge());
                    t=a+b+c+x+y;
                    a=a/t; b=b/t; c=c/t; x=x/t; y=y/t;
                }
                else if(p.getBp().equals("NORMAL")){
                    a=probCondFDrugA*probDrugA*probCondBPNormalDrugA*probCondChHighDrugA*normal(mediaNaDrugA, desvNaDrugA, p.getNa())*
                            normal(mediaKDrugA, desvKDrugA, p.getK())*normal(mediaAgeDrugA, desvAgeDrugA, p.getAge());
                    b=probCondFDrugB*probDrugB*probCondBPNormalDrugB*probCondChHighDrugB*normal(mediaNaDrugB, desvNaDrugB, p.getNa())*
                            normal(mediaKDrugB, desvKDrugB, p.getK())*normal(mediaAgeDrugB, desvAgeDrugB, p.getAge());
                    c=probCondFDrugC*probDrugC*probCondBPNormalDrugC*probCondChHighDrugC*normal(mediaNaDrugC, desvNaDrugC, p.getNa())*
                            normal(mediaKDrugC, desvKDrugC, p.getK())*normal(mediaAgeDrugC, desvAgeDrugC, p.getAge());
                    x=probCondFDrugX*probDrugX*probCondBPNormalDrugX*probCondChHighDrugX*normal(mediaNaDrugX, desvNaDrugX, p.getNa())*
                            normal(mediaKDrugX, desvKDrugX, p.getK())*normal(mediaAgeDrugX, desvAgeDrugX, p.getAge());
                    y=probCondFDrugY*probDrugY*probCondBPNormalDrugY*probCondChHighDrugY*normal(mediaNaDrugY, desvNaDrugY, p.getNa())*
                            normal(mediaKDrugY, desvKDrugY, p.getK())*normal(mediaAgeDrugY, desvAgeDrugY, p.getAge());
                    t=a+b+c+x+y;
                    a=a/t; b=b/t; c=c/t; x=x/t; y=y/t;
                }
                else{
                    a=probCondFDrugA*probDrugA*probCondBPLowDrugA*probCondChHighDrugA*normal(mediaNaDrugA, desvNaDrugA, p.getNa())*
                            normal(mediaKDrugA, desvKDrugA, p.getK())*normal(mediaAgeDrugA, desvAgeDrugA, p.getAge());
                    b=probCondFDrugB*probDrugB*probCondBPLowDrugB*probCondChHighDrugB*normal(mediaNaDrugB, desvNaDrugB, p.getNa())*
                            normal(mediaKDrugB, desvKDrugB, p.getK())*normal(mediaAgeDrugB, desvAgeDrugB, p.getAge());
                    c=probCondFDrugC*probDrugC*probCondBPLowDrugC*probCondChHighDrugC*normal(mediaNaDrugC, desvNaDrugC, p.getNa())*
                            normal(mediaKDrugC, desvKDrugC, p.getK())*normal(mediaAgeDrugC, desvAgeDrugC, p.getAge());
                    x=probCondFDrugX*probDrugX*probCondBPLowDrugX*probCondChHighDrugX*normal(mediaNaDrugX, desvNaDrugX, p.getNa())*
                            normal(mediaKDrugX, desvKDrugX, p.getK())*normal(mediaAgeDrugX, desvAgeDrugX, p.getAge());
                    y=probCondFDrugY*probDrugY*probCondBPLowDrugY*probCondChHighDrugY*normal(mediaNaDrugY, desvNaDrugY, p.getNa())*
                            normal(mediaKDrugY, desvKDrugY, p.getK())*normal(mediaAgeDrugY, desvAgeDrugY, p.getAge());
                    t=a+b+c+x+y;
                    a=a/t; b=b/t; c=c/t; x=x/t; y=y/t; 
                }
            }
            else{
              if(p.getBp().equals("HIGH")){
                    a=probCondFDrugA*probDrugA*probCondBPHighDrugA*probCondChLowDrugA*normal(mediaNaDrugA, desvNaDrugA, p.getNa())*
                            normal(mediaKDrugA, desvKDrugA, p.getK())*normal(mediaAgeDrugA, desvAgeDrugA, p.getAge());
                    b=probCondFDrugB*probDrugB*probCondBPHighDrugB*probCondChLowDrugB*normal(mediaNaDrugB, desvNaDrugB, p.getNa())*
                            normal(mediaKDrugB, desvKDrugB, p.getK())*normal(mediaAgeDrugB, desvAgeDrugB, p.getAge());
                    c=probCondFDrugC*probDrugC*probCondBPHighDrugC*probCondChLowDrugC*normal(mediaNaDrugC, desvNaDrugC, p.getNa())*
                            normal(mediaKDrugC, desvKDrugC, p.getK())*normal(mediaAgeDrugC, desvAgeDrugC, p.getAge());
                    x=probCondFDrugX*probDrugX*probCondBPHighDrugX*probCondChLowDrugX*normal(mediaNaDrugX, desvNaDrugX, p.getNa())*
                            normal(mediaKDrugX, desvKDrugX, p.getK())*normal(mediaAgeDrugX, desvAgeDrugX, p.getAge());
                    y=probCondFDrugY*probDrugY*probCondBPHighDrugY*probCondChLowDrugY*normal(mediaNaDrugY, desvNaDrugY, p.getNa())*
                            normal(mediaKDrugY, desvKDrugY, p.getK())*normal(mediaAgeDrugY, desvAgeDrugY, p.getAge());
                    t=a+b+c+x+y;
                    a=a/t; b=b/t; c=c/t; x=x/t; y=y/t;
                }
              else if(p.getBp().equals("NORMAL")){
                    a=probCondFDrugA*probDrugA*probCondBPNormalDrugA*probCondChLowDrugA*normal(mediaNaDrugA, desvNaDrugA, p.getNa())*
                            normal(mediaKDrugA, desvKDrugA, p.getK())*normal(mediaAgeDrugA, desvAgeDrugA, p.getAge());
                    b=probCondFDrugB*probDrugB*probCondBPNormalDrugB*probCondChLowDrugB*normal(mediaNaDrugB, desvNaDrugB, p.getNa())*
                            normal(mediaKDrugB, desvKDrugB, p.getK())*normal(mediaAgeDrugB, desvAgeDrugB, p.getAge());
                    c=probCondFDrugC*probDrugC*probCondBPNormalDrugC*probCondChLowDrugC*normal(mediaNaDrugC, desvNaDrugC, p.getNa())*
                            normal(mediaKDrugC, desvKDrugC, p.getK())*normal(mediaAgeDrugC, desvAgeDrugC, p.getAge());
                    x=probCondFDrugX*probDrugX*probCondBPNormalDrugX*probCondChLowDrugX*normal(mediaNaDrugX, desvNaDrugX, p.getNa())*
                            normal(mediaKDrugX, desvKDrugX, p.getK())*normal(mediaAgeDrugX, desvAgeDrugX, p.getAge());
                    y=probCondFDrugY*probDrugY*probCondBPNormalDrugY*probCondChLowDrugY*normal(mediaNaDrugY, desvNaDrugY, p.getNa())*
                            normal(mediaKDrugY, desvKDrugY, p.getK())*normal(mediaAgeDrugY, desvAgeDrugY, p.getAge());
                    t=a+b+c+x+y;
                    a=a/t; b=b/t; c=c/t; x=x/t; y=y/t;
                }
                else{
                    a=probCondFDrugA*probDrugA*probCondBPLowDrugA*probCondChLowDrugA*normal(mediaNaDrugA, desvNaDrugA, p.getNa())*
                            normal(mediaKDrugA, desvKDrugA, p.getK())*normal(mediaAgeDrugA, desvAgeDrugA, p.getAge());
                    b=probCondFDrugB*probDrugB*probCondBPLowDrugB*probCondChLowDrugB*normal(mediaNaDrugB, desvNaDrugB, p.getNa())*
                            normal(mediaKDrugB, desvKDrugB, p.getK())*normal(mediaAgeDrugB, desvAgeDrugB, p.getAge());
                    c=probCondFDrugC*probDrugC*probCondBPLowDrugC*probCondChLowDrugC*normal(mediaNaDrugC, desvNaDrugC, p.getNa())*
                            normal(mediaKDrugC, desvKDrugC, p.getK())*normal(mediaAgeDrugC, desvAgeDrugC, p.getAge());
                    x=probCondFDrugX*probDrugX*probCondBPLowDrugX*probCondChLowDrugX*normal(mediaNaDrugX, desvNaDrugX, p.getNa())*
                            normal(mediaKDrugX, desvKDrugX, p.getK())*normal(mediaAgeDrugX, desvAgeDrugX, p.getAge()); 
                    y=probCondFDrugY*probDrugY*probCondBPLowDrugY*probCondChLowDrugY*normal(mediaNaDrugY, desvNaDrugY, p.getNa())*
                            normal(mediaKDrugY, desvKDrugY, p.getK())*normal(mediaAgeDrugY, desvAgeDrugY, p.getAge());
                    t=a+b+c+x+y;
                    a=a/t; b=b/t; c=c/t; x=x/t; y=y/t; 
                }
            }
            
        }
        
        if(a>=b && a>=c && a>=x && a>=y) droga="drugA";
        else if(b>=a && b>=c && b>=x && b>=y) droga="drugB";
        else if(c>=b && c>=a && c>=x && c>=y) droga="drugC";
        else if(x>=b && x>=c && x>=a && x>=y) droga="drugX";
        else if(y>=b && y>=c && y>=a && y>=x) droga="drugY";
        return droga;
    }
    
    public Double normal(Double m, Double d, Double x){
        return (Math.pow(Math.E, -((Math.pow(x-m, 2))/(2*Math.pow(d, 2)))))/(Math.sqrt(2*Math.PI)*d);
    }
    

}
