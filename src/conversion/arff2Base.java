/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package conversion;

import weka.core.*;
import weka.core.converters.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class arff2Base {
    
    public void convertir(String path){
        
      try{
        Instances data = new Instances(new BufferedReader(new FileReader(path)));
        data.setClassIndex(data.numAttributes() - 1);

        DatabaseSaver save = new DatabaseSaver();
        save.setUrl("jdbc:mysql://localhost:3306/weka_test");
        save.setUser("root");
        save.setPassword("masterkey");
        save.setInstances(data);
        save.setRelationForTableName(false);
        save.setTableName("paciente");
        save.connectToDatabase();
        save.writeBatch();
      }
      catch(Exception e){
          System.out.println("Error al convertir");  
      }
    }
    
}
