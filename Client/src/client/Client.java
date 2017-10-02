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
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pratos
 */
public class Client {

    
    
    public static void main(String[] args) throws ClassNotFoundException, IOException,UnknownHostException {
       
            Socket socket = new Socket("192.168.1.108",12345); 
               
                ObjectOutputStream enviar = new ObjectOutputStream(socket.getOutputStream()); 
                Pessoa pessoa = new Pessoa();
                pessoa.setNome("Lucas");
                pessoa.setSobrenome("Prates");
                pessoa.setIdade(23);
                enviar.writeObject(pessoa);
                enviar.close();
                FileOutputStream objetoParaEnvio = new FileOutputStream("pessoa.ser");
		ObjectOutputStream saida = new ObjectOutputStream(objetoParaEnvio);
		saida.writeObject(pessoa);
                saida.flush();
		saida.close();
                socket.close();
                 
    } 
    
}
