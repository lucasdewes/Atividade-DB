package mesa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Incluir1 {
    
    public void conectarMESA(){
        Connection conexao = Conexao.obterConexao();
        PreparedStatement comando = null;
        int bola = 1;
        int raquete = 2;
        String jogador1, jogador2;
        
        jogador1 = JOptionPane.showInputDialog("Digite o nome do jogador 1");
        jogador2 = JOptionPane.showInputDialog("Digite o nome do jogador 2");
        
        String sql = "INSERT INTO mesa (bola, raquete, jogador1, jogador2) VALUES";
        sql += "("+bola+", "+raquete+", "+jogador1+", "+jogador2+" )";
        
        try {
            comando = conexao.prepareStatement("INSERT INTO mesa (bola, raquete, jogador1, jogador2) VALUES (?, ?, ?, ?)");//? numero de colunas
            comando.setInt(1, raquete);
            comando.setInt(2, raquete);
            comando.setString(3, jogador1);
            comando.setString(4, jogador2);
            comando.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inclusão realizada com sucesso !","Tabela mesa",JOptionPane.INFORMATION_MESSAGE);
            //System.out.println("Inclusão realizada com sucesso !");
        }catch (SQLException ex){
            System.err.println("Erro ao incluir dados na tabela MESA \n"+ex.toString());
        }
        finally{
            try {
                comando.close();
                conexao.close();
            }catch (SQLException ex){
                System.err.println("Erro ao desconectar "+ex.toString());
            }
        }
    }      
}