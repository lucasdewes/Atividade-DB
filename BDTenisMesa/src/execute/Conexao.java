package execute;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/BDatvTenisMesa";//nome do banco de dados
    private static final String DRIVE = "org.postgresql.Driver";
    private static final String USER = "postgres";
    private static final String PASS = "123";
    
    public static Connection obterConexao(){
        Connection conexao = null;
        
        try{
            Class.forName(DRIVE);
            conexao = DriverManager.getConnection(URL, USER, PASS);
        }catch (ClassNotFoundException cnf){
            System.out.println("Driver não encontrado - "+cnf.getMessage());
        }catch (SQLException sqle){
            System.out.println("Erro ao conectar no banco - "+sqle.getMessage());;
        }
        return conexao;
    }
}
