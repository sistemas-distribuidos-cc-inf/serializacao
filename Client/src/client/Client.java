/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import objeto.Pessoa;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pratos
 */
public class Client {

    
    
    public static void main(String[] args) throws ClassNotFoundException {
       
        try {
            
            Socket socket = new Socket("localhost",5555);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            //ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            Pessoa pessoa = new Pessoa();
            pessoa.setNome("Lucas");
            pessoa.setSobrenome("Prates");
            pessoa.setIdade(23);
            output.writeObject(pessoa);
            output.close();
            FileOutputStream out = new FileOutputStream("pessoa.txt");
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(pessoa);
            objOut.close();
           /* output.writeObject(objOut);
            output.close();*/
            //input.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
