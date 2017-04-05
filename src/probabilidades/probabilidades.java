/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package probabilidades;

import entidad.Paciente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Cristian
 */
public class probabilidades {
    
    public int numeroTotal(){ //Total de registros
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NaivePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createNamedQuery("Paciente.findAll");
        return query.getResultList().size();
    }
    
    public double probabilidadPriori(String drug, int total){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NaivePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createNamedQuery("Paciente.findByDrug");
        query.setParameter("drug", drug);
        return ((double)query.getResultList().size())/total;  //Pacientes con droga drug sobre el total de registros
    }
    
    public double probabilidadCondicionalSex(String drug, String sex){
        int total=0, num=0;
        List<Paciente> pacientes = new ArrayList<>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NaivePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createNamedQuery("Paciente.findByDrug");
        query.setParameter("drug", drug);
        pacientes = query.getResultList();
        total = pacientes.size();  //Total de pacientes con droga drug
        for(Paciente p : pacientes){
            if(p.getSex().equals(sex)) num ++; //Cuenta los registros con sexo = sex
        }
        return ((double) (num + 1)/(total + 2));  //Ajuste de laplace
    }
    
    public double probabilidadCondicionalBP(String drug, String bp){
        int total=0, num=0;
        List<Paciente> pacientes = new ArrayList<>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NaivePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createNamedQuery("Paciente.findByDrug");
        query.setParameter("drug", drug);
        pacientes = query.getResultList();
        total = pacientes.size();  //Total de pacientes con droga drug
        for(Paciente p : pacientes){
            if(p.getBp().equals(bp)) num ++; //Cuenta los registros con BP = bp
        }
        return ((double) (num + 1)/(total + 3));  //Ajuste de laplace
    }
    
    public double probabilidadCondicionalCholesterol(String drug, String cholesterol){
        int total=0, num=0;
        List<Paciente> pacientes = new ArrayList<>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NaivePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createNamedQuery("Paciente.findByDrug");
        query.setParameter("drug", drug);
        pacientes = query.getResultList();
        total = pacientes.size();  //Total de pacientes con droga drug
        for(Paciente p : pacientes){
            if(p.getCholesterol().equals(cholesterol)) num ++; //Cuenta los registros con Colesterol = cholesterol
        }
        return ((double) (num + 1)/(total + 2));  //Ajuste de laplace
    }
    
    public Double promedio(List<Double> lista){
        double promedio=0;
        int tamano=lista.size();
        for(int x=0; x<tamano;x++){
            promedio=promedio+lista.get(x);
        }
        return promedio/tamano;
    }
    
    public Double desviacion(List<Double> lista, Double promedio){
        double desviacion=0;
        double varianza=0;
        int tamano=lista.size();
        for(int x=0; x<tamano;x++){
            varianza=varianza+(Math.pow(lista.get(x)-promedio,2));
        }
        varianza=varianza/(tamano-1);
        return Math.sqrt(varianza);
    }
}
