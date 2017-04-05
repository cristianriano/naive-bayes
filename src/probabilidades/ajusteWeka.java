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
public class ajusteWeka {
    
    public double calcularPrecisionNa(){
       List<Paciente> pacientes;
       double delta=0;
       double distincion=0;
       double tmp=0;
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("NaivePU");
       EntityManager em = emf.createEntityManager();
       EntityTransaction tx = em.getTransaction();
       tx.begin();
       Query query = em.createNamedQuery("Paciente.findAllNa");
       pacientes = query.getResultList();
       for(int x=0; x<pacientes.size()-1; x++){
           tmp =(pacientes.get(x+1).getNa()-pacientes.get(x).getNa());
           delta=delta+tmp;
           if(tmp!=0) distincion++;
       }
       return delta/distincion;
    }
    
    public double calcularPrecisionK(){
       List<Paciente> pacientes;
       double delta=0;
       double distincion=0;
       double tmp=0;
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("NaivePU");
       EntityManager em = emf.createEntityManager();
       EntityTransaction tx = em.getTransaction();
       tx.begin();
       Query query = em.createNamedQuery("Paciente.findAllK");
       pacientes = query.getResultList();
       for(int x=0; x<pacientes.size()-1; x++){
           tmp =(pacientes.get(x+1).getK()-pacientes.get(x).getK());
           delta=delta+tmp;
           if(tmp!=0) distincion++;
       }
       return delta/distincion;
    }
    
    public double calcularPrecisionAge(){
       List<Paciente> pacientes;
       double delta=0;
       double distincion=0;
       double tmp=0;
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("NaivePU");
       EntityManager em = emf.createEntityManager();
       EntityTransaction tx = em.getTransaction();
       tx.begin();
       Query query = em.createNamedQuery("Paciente.findAllAge");
       pacientes = query.getResultList();
       for(int x=0; x<pacientes.size()-1; x++){
           tmp =(pacientes.get(x+1).getAge()-pacientes.get(x).getAge());
           delta=delta+tmp;
           if(tmp!=0) distincion++;
       }
       return delta/distincion;
    }
    
    public ArrayList<Double> redondearNa(Double precision, String drug){
       List<Paciente> pacientes;
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("NaivePU");
       EntityManager em = emf.createEntityManager();
       EntityTransaction tx = em.getTransaction();
       tx.begin();
       Query query = em.createNamedQuery("Paciente.findByDrug");
       query.setParameter("drug", drug);
       pacientes = query.getResultList();
       ArrayList<Double> redondeado = new ArrayList();
       for(Paciente p: pacientes){
           redondeado.add((Math.round(p.getNa()/precision))*precision);
       }
       return redondeado;
    }
    
    public ArrayList<Double> redondearK(Double precision, String drug){
       List<Paciente> pacientes;
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("NaivePU");
       EntityManager em = emf.createEntityManager();
       EntityTransaction tx = em.getTransaction();
       tx.begin();
       Query query = em.createNamedQuery("Paciente.findByDrug");
       query.setParameter("drug", drug);
       pacientes = query.getResultList();
       ArrayList<Double> redondeado = new ArrayList();
       for(Paciente p: pacientes){
           redondeado.add((Math.round(p.getK()/precision))*precision);
       }
       return redondeado;
    }
    
    public ArrayList<Double> redondearAge(Double precision, String drug){
       List<Paciente> pacientes;
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("NaivePU");
       EntityManager em = emf.createEntityManager();
       EntityTransaction tx = em.getTransaction();
       tx.begin();
       Query query = em.createNamedQuery("Paciente.findByDrug");
       query.setParameter("drug", drug);
       pacientes = query.getResultList();
       ArrayList<Double> redondeado = new ArrayList();
       for(Paciente p: pacientes){
           redondeado.add((Math.round(p.getAge()/precision))*precision);
       }
       return redondeado;
    }
    
}
