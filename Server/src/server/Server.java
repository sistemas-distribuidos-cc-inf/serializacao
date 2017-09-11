/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.ServerSocket;
import java.net.Socket;

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
    
    private void trataConexao(Socket socket)throws IOException 
    {
      //protocolo da aplicacao
      //criar stream de entrada e saida
        // tratar a conversacao entre client e servidor
        try{
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        
        String msg = input.readUTF();
        System.out.println("Mensagem recebida...");
        output.writeUTF("Hello World");
        input.close();
        output.close();
        
        }catch(IOException e){
            //tratamento de excessoes
        }finally{
            
            fechaSocket(socket);
        }
    }
    public static void main(String[] args) {
        try{
            //inicializando os metodos na porta 5555
        Server server = new Server();
        System.out.println("Aguardando conexeao");
        server.criarServerSocket(5555);  
        Socket socket = server.esperaConexao();//protocolo
        System.out.println("Cliente conectado");
        server.trataConexao(socket);
        System.out.println("Cliente finalizado");
        }catch(IOException e){
            
        }
            
    }
    
}
