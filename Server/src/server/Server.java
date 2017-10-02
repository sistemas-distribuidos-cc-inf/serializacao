/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import objeto.Pessoa;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pratos
 */
public class Server {
    
    private ServerSocket serverSocket;
    
    // Porta pela qual o cliente ira acessar
    // IOExcepetion será tratado pelo client e não pelo servidor
    private void criarServerSocket(int porta) throws IOException
    {
        serverSocket = new ServerSocket(porta);
    }
    // espera por uma conexão até ser aceita
    private Socket esperaConexao() throws IOException
    {
        Socket socket = serverSocket.accept();
        return socket;
    }
    
    private void fechaSocket(Socket s) throws IOException
    {
        s.close();
    }
    
    private void trataConexao(Socket socket)throws IOException, ClassNotFoundException 
    {
      //protocolo da aplicacao
      //criar stream de entrada e saida
        // tratar a conversacao entre client e servidor
      
        try{
          ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
         
                Pessoa pessoa;
                pessoa = (Pessoa) input.readObject();
             FileOutputStream objeto = new FileOutputStream("pessoa.ser"); 
             ObjectOutputStream saida = new ObjectOutputStream(objeto); 
                 saida.writeObject(pessoa);
                 System.out.println("Object received = " + pessoa.getNome());
                 System.out.println("Object received = " + pessoa.getSobrenome());
                 System.out.println("Object received = " + pessoa.getIdade());
             saida.close();
             objeto.close();
             input.close();
               
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Problema no tratamento de conexão com o cliente " +socket.getInetAddress());
            System.out.println("Error: " +e.getMessage());
        }finally{
          
            fechaSocket(socket);
        }
    
    }
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        
        
         //try{
            //inicializando os metodos na porta 5555
        Server server = new Server();
        System.out.println("Aguardando conexao");
        server.criarServerSocket(12345);
        while(true){
        Socket socket = server.esperaConexao();//protocolo
        System.out.println("Cliente conectado" +socket.getInetAddress());
        server.trataConexao(socket);
        System.out.println("Cliente finalizado");
        }
        
        //}catch(IOException e){
            
        //}
            
    }
    
}
