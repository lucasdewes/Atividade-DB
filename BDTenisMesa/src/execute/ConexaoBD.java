package execute;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class ConexaoBD{
    public static final int data = 2019;
    private String time1, time2, tecnico1, tecnico2, nomeArbitro;
    int size = 2, pontos;
    int qtd_P1 = 0, set1 = 0, qtd_P2 = 0, set2 = 0;
    int a1 = 0, a2 = 0, pontos1 = 0, pontos2 = 0;
    
    
    private void erro(Object elemento){
        JOptionPane.showMessageDialog(null, "ERRO !",""+elemento,JOptionPane.ERROR_MESSAGE);
        throw new IllegalAccessError("EXPRESSÃO INVÁLIDA !");
    }
    //faixaIdade
    private void adicinarCategoria(int idade, String nome){//verificando as faixas etárias simples
        if(idade >= 10 && idade <=25){//mirim
            this.CATEGORIA("mirim", "", nome);
        }else if(idade >25 && idade <=50){
            this.CATEGORIA("", "junvenil",nome);//juvenil
        }else{
            System.err.println("IDADE NÃO ESTÁ ENTRE AS FAIXAS ETÁRIAS");
            this.erro(idade);
        }
    }
    
    public boolean VefirificaIDADE10_25Simples(int idade1, int idade2){
        boolean flag;
        if(idade1 >= 10 && idade2 <= 25){
            flag = true;
        }else{
            flag = false;
        }
        return flag;
    }
    
    public boolean VefificaIdade25_50Simples(int idade1, int idade2){
        boolean flag;
        if(idade1 >= 25 && idade2 <= 50){
            flag = true;
        }else{
            flag = false;
        }
        return flag;
    }
    
    public boolean verificaIDADE10_25Comp(int idade1, int idade2, int idade3, int idade4){//entre 10 e 25
        boolean flag;
        if((idade1>= 10 && idade1 <= 25) && (idade2>= 10 && idade2 <= 25) && (idade3 >= 10 && idade3 <= 25) && (idade4 >= 10 && idade4 <= 25)){
            flag = true;
        }else{
            flag = false;
        }
        return flag;
    }
    //Método para vefificar se as idades de todos os jogadores estão entre 25 e 50
    public boolean verificaIDADE25_50Comp(int idade1, int idade2, int idade3, int idade4){
        boolean flag;
        if((idade1>= 25 && idade1 <= 50) && (idade2>= 25 && idade2 <= 50) && (idade3 >= 25 && idade3 <= 50) && (idade4 >= 25 && idade4 <= 50)){
            flag = true;
        }else{
            flag = false;
        }
        return flag;
    }
    
    public void executaConectar(){
        Scanner leia = new Scanner(System.in);
        
        Connection conexao = Conexao.obterConexao();
        PreparedStatement comando = null;
        
        String[] opcao = {"EQUIPE_SIMPLES","EQUIPE_COMPOSTA"};
        int dataNasc1, dataNasc2;
        
        String resposta = (String) JOptionPane.showInputDialog(null,"Escolha uma opção","Opção",JOptionPane.PLAIN_MESSAGE, null, opcao,"EQUIPE_SIMPLES");
        
        switch(resposta){
            case "EQUIPE_SIMPLES":
                String jogador1, jogador2;
                
                time1 = JOptionPane.showInputDialog("Digite o nome do TIME 1");
                jogador1 = JOptionPane.showInputDialog("Digite o nome do jogador 1");
                dataNasc1 = Integer.parseInt(JOptionPane.showInputDialog("Jogador: 1\nDigite o ano de nascimento", null));
                this.tecnico1 = JOptionPane.showInputDialog("Digite o nome do TÉCNICO 1");
                
                time2 = JOptionPane.showInputDialog("Digite o nome do TIME 2");
                jogador2 = JOptionPane.showInputDialog("Digite o nome do jogador 2");
                dataNasc2 = Integer.parseInt(JOptionPane.showInputDialog("\nJogador: 2\nDigite o ano de nascimento", null));
                this.tecnico2 = JOptionPane.showInputDialog("Digite o nome do TECNICO 2");
                
                boolean idadeSimples = this.VefirificaIDADE10_25Simples(data-dataNasc1, data-dataNasc2);
                boolean idadeComposta = this.VefificaIdade25_50Simples(data-dataNasc1, data-dataNasc2);
                
                if((idadeSimples == true) && (idadeComposta == false)){
                    this.EQUIPE_SIMPLES(jogador1, dataNasc1, jogador2, dataNasc2, tecnico1, tecnico2);
                    this.adicinarCategoria(data-dataNasc1, jogador1);
                    this.adicinarCategoria(data-dataNasc2, jogador2);
                    this.JOGO(time1, time2, "EQUIPE_SIMPLES", this.getNomeArbitro());
                    
                }else if((idadeSimples == false) && (idadeComposta == true)){
                    this.EQUIPE_SIMPLES(jogador1, data-dataNasc1, jogador2, dataNasc2, tecnico1, tecnico2);
                    this.adicinarCategoria(data-dataNasc1, jogador1);
                    this.adicinarCategoria(data-dataNasc2, jogador2);
                    this.JOGO(time1, time2, "EQUIPE_SIMPLES", this.getNomeArbitro());
                }else {
                    System.out.println("IDADE DE DUPLA NÃO ESTÁ NA FAIXA ETÁRIA: "+(data-dataNasc1)+" - "+(dataNasc2));
                    this.erro("Faixa etária incompatíveis");
                }
                
                //inicio do jogo
                JOptionPane.showMessageDialog(null, "BEM VINDO AO JOGO - TENIS DE MESA");
                
                System.out.println("\t\n________ jogando...\n");
                
                System.out.println("Digite a opcao de pontos para o time");
                System.out.println("1 - ponto time 1:");
                System.out.println("2 - ponto time 2");
                do{
                    pontos = leia.nextInt();
                    switch (pontos){
                    case 1:
                    qtd_P1 += 1;
                    pontos1 += 1;
                    System.out.println("   TIME1    TIME2");
                    System.out.println("PLACAR:  = "+qtd_P1+" X "+qtd_P2);
                    if(qtd_P1 == size){
                        set1 += 1;
                        System.out.println("\t\tSET VENCIDO POR TIME 1 = "+qtd_P1 +" X "+qtd_P2);
                        a1 += 1;
                        //reiniciando a contagem dos pontos
                        set1 = 0;
                        set2 = 0;
                        qtd_P1 = 0;
                        qtd_P2 = 0;
                        System.out.println("\t\tSET1 = "+a1);
                    }
                    
                break;
                case 2:
                    qtd_P2 += 1;
                    pontos2 += 1;
                    System.out.println("PLACAR:  = "+qtd_P1+" X "+qtd_P2);
                    if(qtd_P2 == size){
                        set2 += 1;
                        System.out.println("\t\tSET VENCIDO POR TIME 2:  "+qtd_P2 +" X "+qtd_P1);
                        a2 += 1;
                        //reiciando a contagem de pontos
                        set1 = 0;
                        set2 = 0;
                        qtd_P1 = 0;
                        qtd_P2 = 0;
                        System.out.println("\t\tSET2 = "+a2);
                    }
                break;
                
                default :
                    System.err.println("OPÇÃO INVÁLIDA !");
                    throw new IllegalArgumentException("PONTO NÃO ENCONTRADO !");   
                }
                   
            }while((a1+a2) != 5);//simulando até 5 sets
            System.out.println("_______ FIM DE JOGO _________");
            JOptionPane.showMessageDialog(null, "FIM DE JOGO\n");
            
            this.PONTOS(this.time1, pontos1, a1, this.time2, pontos2, a2);//alimentando a tabela pontos do jogo simples
            //ALIMENTANDO A TABELA O RESULTADO com o vencedor do jogo
            if(a1 > a2){
                //this.RESULTADO(vencedor, set_vencedor, perdedor, set_perdedor, ano)
                this.RESULTADO(this.time1, a1, this.time2, a2, data);
            }else{
                this.RESULTADO(this.time2, a2, this.time1, a1, data);
            }
            
            break;
            // FIM DO JOGO SIMPLES
            
            case "EQUIPE_COMPOSTA":
                String jog1, nome1;
                int dataN1, dtN1;
                String jog2, nome2;
                int dataN2, dtN2;
                
                time1 = JOptionPane.showInputDialog("Digite o nome do TIME 1");
                jog1 = JOptionPane.showInputDialog("Digite o nome do jogador1 time1");
                dataN1 = Integer.parseInt(JOptionPane.showInputDialog("Jogador: 1\nDigite o ano de nascimento", null));
                
                nome1 = JOptionPane.showInputDialog("Digite o nome do jogador2 time1");
                dtN1 = Integer.parseInt(JOptionPane.showInputDialog("Jogador: 1\nDigite o ano de nascimento", null));
                tecnico1 = JOptionPane.showInputDialog("Digite o nome do TÉCNICO 1");
                
                time2 = JOptionPane.showInputDialog("Digite o nome do TIME 2");
                jog2 = JOptionPane.showInputDialog("Digite o nome do jogador1 time2");
                dataN2 = Integer.parseInt(JOptionPane.showInputDialog("Jogador: 1\nDigite o ano de nascimento", null));
                
                nome2 = JOptionPane.showInputDialog("Digite o nome do jogador2 time2");
                dtN2 = Integer.parseInt(JOptionPane.showInputDialog("Jogador: 1\nDigite o ano de nascimento", null));
                tecnico2 = JOptionPane.showInputDialog("Digite o nome do TÉCNICO 2");
                
                boolean idadeDuplaMIRIM = this.verificaIDADE10_25Comp(data-dataN1, data-dtN1, data-dataN2, data-dtN2);
                boolean idadeDuplaJUVENIL = this.verificaIDADE25_50Comp(data-dataN1, data-dtN1, data-dataN2, data-dtN2);
                
                if((idadeDuplaMIRIM == true) && (idadeDuplaJUVENIL == false)){//adicionando as categorias pela idade
                    this.EQUIPE_COMPOSTA(time1, jog1, nome1, tecnico1, time2, jog2, nome2, tecnico2);
                    this.adicinarCategoria(data-dataN1, jog1);
                    this.adicinarCategoria(data-dtN1, nome1);
                    this.adicinarCategoria(data-dataN2, jog2);
                    this.adicinarCategoria(data-dtN2, nome2);
                    
                    
                }else if((idadeDuplaMIRIM == false) && (idadeDuplaJUVENIL == true)){
                    this.EQUIPE_COMPOSTA(time1, jog1, nome1, tecnico1, time2, jog2, nome2, tecnico2);
                    this.adicinarCategoria(data-dataN1, jog1);
                    this.adicinarCategoria(data-dtN1, nome1);
                    this.adicinarCategoria(data-dataN2, jog2);
                    this.adicinarCategoria(data-dtN2, nome2);
                    
                }else{
                    System.err.println("INTERVALO DE IDADES NÃO ENCONTRADO - "+(data-dataN1)+" - "+(data-dtN1)
                    +" - "+(data-dataN2)+" - "+(data-dtN2));
                    this.erro("Faixa etárias");
                }
                //adicionando a tabela JOGO
                this.JOGO(time1, time2, "EQUIPE_COMPOSTA", this.nomeArbitro);
                
                JOptionPane.showMessageDialog(null, "BEM VINDO AO JOGO - TENIS DE MESA");
                System.out.println("\n______ jogando...\n");
                System.out.println("Digite a opcao de pontos para o time");
                System.out.println("1 - ponto time 1:");
                System.out.println("2 - ponto time 2");
                
                do{
                    pontos = leia.nextInt();
                    switch (pontos){
                    case 1:
                    qtd_P1 += 1;
                    pontos1 += 1;
                    System.out.println("   TIME1    TIME2");
                    System.out.println("PLACAR:  = "+qtd_P1+" X "+qtd_P2);
                    if(qtd_P1 == size){
                        set1 += 1;
                        System.out.println("\t\tSET VENCIDO POR TIME 1 = "+qtd_P1 +" X "+qtd_P2);
                        a1 += 1;
                        //reiniciando a contagem dos pontos
                        set1 = 0;
                        set2 = 0;
                        qtd_P1 = 0;
                        qtd_P2 = 0;
                        System.out.println("\t\tSET1 = "+a1);
                    }
                        
                break;
                case 2:
                    qtd_P2 += 1;
                    pontos2 += 1;
                    System.out.println("PLACAR:  = "+qtd_P1+" X "+qtd_P2);
                    if(qtd_P2 == size){
                        set2 += 1;
                        System.out.println("\t\tSET VENCIDO POR TIME 2:  "+qtd_P2 +" X "+qtd_P1);
                        a2 += 1;
                        //reiciando a contagem de pontos
                        set1 = 0;
                        set2 = 0;
                        qtd_P1 = 0;
                        qtd_P2 = 0;
                        System.out.println("\t\tSET2 = "+a2);
                    }
                break;
                
                default :
                    System.err.println("OPÇÃO INVÁLIDA !");
                    throw new IllegalArgumentException("PONTO NÃO ENCONTRADO !");   
                }
                    
            }while((a1+a2) != 5);//simulando até 5 sets
                
            System.out.println("_____ FIM DE JOGO __________");
            JOptionPane.showMessageDialog(null, "FIM DE JOGO");
            
            this.PONTOS(this.time1, pontos1, a1, this.time2, pontos2, a2);
            if(a1 > a2){
                this.RESULTADO(this.time1, a1, this.time2, a2, data);
            }else{
                this.RESULTADO(this.time2, a2, this.time1, a1, data);
            }
        }
        
    }
    
    private boolean EQUIPE_SIMPLES(String jogador1, int dataNasc1,   String jogador2, int dataNasc2,
            String tecnico1, String tecnico2){//tabela EQUIPE_SIMPLES
        Connection conexao = Conexao.obterConexao();//realizando conexao com o pgAdmin
        PreparedStatement comando = null;
        
        String sql = "INSERT INTO equipe_simples (jogador1, dataNasc1, jogador2, dataNasc2, tecnico1, tecnico2) VALUES";
        sql += "("+jogador1+", "+dataNasc1+", "+jogador2+", "+dataNasc2+", "+tecnico1+", "+tecnico2+" )";
        try{
            comando = conexao.prepareStatement("INSERT INTO equipe_simples (jogador1, dataNasc1, jogador2, dataNasc2, tecnico1, tecnico2) VALUES (?, ?, ?, ?, ?, ?)");//? numero de colunas
            comando.setString(1, jogador1);
            comando.setInt(2, dataNasc1);
            comando.setString(3, jogador2);
            comando.setInt(4,dataNasc2);
            comando.setString(5, tecnico1);
            comando.setString(6, tecnico2);
            comando.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Inclusão realizada com sucesso !","Tabela EQUIPE_SIMPLES",JOptionPane.INFORMATION_MESSAGE);
            
            System.out.println("\tEQUIPE_SIMPLES");
            System.out.println("Jogador 1: "+jogador1);
            System.out.println("Ano de nascimento: "+dataNasc1+"    Idade: "+(data-dataNasc1));
            System.out.println("Jogador 2: "+jogador2);
            System.out.println("Ano de Nascimento: "+dataNasc2+"   Idde: "+(data-dataNasc2));
            System.out.println("Técnico 1: "+tecnico1);
            System.out.println("Técnico 2: "+tecnico2+"\n");
            
        }catch (SQLException ex){
            System.err.println("Erro ao incluir dados na tabela EQUIPE_SIMPLES \n"+ex.toString());
        }
        finally{
            try{
                comando.close();
                conexao.close();
            }catch (SQLException ex){
                System.err.println("Erro ao desconectar do Banco de Dados - Tabela EQUIPE_SIMPLES\n"+ex.toString());
            }
        }
        this.ARBITRO();//chamando o medoto para adicionar na tabela arbitro
        return true;
    }
    //tabela EQUIPE_COMPOSTA
    private boolean EQUIPE_COMPOSTA(String time1, String jogador1, String nome1, String tecnico1, 
        String time2, String jogador2, String nome2, String tecnico2){//tabela EQUIPE_COMPOSTA
        Connection conexao = Conexao.obterConexao();//realizando conexao com o pgAdmin
        PreparedStatement comando = null;
        
        String sql = "INSERT INTO equipe_composta (time1, jogador1, nome1, tecnico1, time2, jogador2, nome2, tecnico2) VALUES";
        sql += "("+time1+", "+jogador1+", "+nome1+", "+tecnico1+", "+tecnico1+", "+time2+", "+jogador2+", "+nome2+", "+tecnico2+" )";
        try{
            comando = conexao.prepareStatement("INSERT INTO equipe_composta (time1, jogador1, nome1, tecnico1, time2, jogador2, nome2, tecnico2) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");//? numero de colunas
            comando.setString(1, time1);
            comando.setString(2, jogador1);
            comando.setString(3, nome1);
            comando.setString(4, tecnico1);
            
            comando.setString(5, time2);
            comando.setString(6, jogador2);
            comando.setString(7, nome2);
            comando.setString(8, tecnico2);
            comando.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Inclusão realizada com sucesso !","Tabela EQUIPE_COMPOSTA",JOptionPane.INFORMATION_MESSAGE);
            System.out.println("\n\tEQUIPE_COMPOSTA");
            System.out.println("Nome do time 1: "+time1);
            System.out.println("Jogador 1 - Dupla A: "+jogador1);
            System.out.println("Jogador 2 - Dupla A: "+nome1);
            System.out.println("Técnico - Dupla A: "+tecnico1);
            
            System.out.println("\nNome do time 2: "+time2);
            System.out.println("Jogador 1 - Dupla B: "+jogador2);
            System.out.println("Jogador 2 - Dupla B: "+nome2);
            System.out.println("Técnico - Dupla B: "+tecnico2);
            
        }catch (SQLException ex){
            System.err.println("Erro ao incluir dados na tabela EQUIPE_COMPOSTA \n"+ex.toString());
        }
        finally{
            try{
                comando.close();
                conexao.close();
            }catch (SQLException ex){
                System.err.println("Erro ao desconectar com Banco de dados - tabela EQUIPE_COMPOSTA\n"+ex.toString());
            }
        }
        this.ARBITRO();//chamando para adicionar o arbitro do jogo
        return true;
    }
    
    private boolean CATEGORIA(String elemento1, String elemento2, String nome){
        Connection conexao = Conexao.obterConexao();//realizando conexao com o pgAdmin
        PreparedStatement comando = null;
        
        String sql = "INSERT INTO categoria (mirim, juvenil, nome) VALUES";
        sql += "("+elemento1+", "+elemento2+", "+nome+") ";
        
        try {
            comando = conexao.prepareStatement("INSERT INTO categoria (mirim, juvenil, nome) VALUES (?, ?, ?)");//? numero de colunas
            comando.setString(1, elemento1);
            comando.setString(2, elemento2);
            comando.setString(3, nome);
            comando.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inclusão realizada com sucesso !","Tabela CATEGORIA",JOptionPane.INFORMATION_MESSAGE);
            
            System.out.println("\n\tCATEGORIA");
            if(elemento1.length() > 2){//concertar calculos
                System.out.println("Nome: "+nome+"\tCategoria: mirim");
            }else if(elemento1.length() < 2){
                System.out.println("Nome: "+nome+"\tCategoria: juvenil");
            }
            
        }catch (SQLException ex){
            System.err.println("Erro ao incluir dados na tabela CATEGORIA \n"+ex.toString());
        }
        finally{
            try{
                comando.close();
                conexao.close();
            }catch (SQLException ex){
                System.err.println("Erro ao desconectar com o Banco de Dados - tabela CATEGORIA\n"+ex.toString());
            }
        }
        return true;
    }
    
    private void ARBITRO(){//TABELA ARBITRO
        Lista lista1 = new Lista(100);//adicionar o nome do arbitro
        Lista lista2 = new Lista(100);//adicionar a quantidade partidas ministradas
        
        Object identidade = null;
        int qtd_partidas = 1;//fazer a procura e a contagem consultando o banco de dados 
        
        this.nomeArbitro = JOptionPane.showInputDialog("Digite o nome do Árbitro");
        identidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a identidade do ÁRBITRO", null));
        
        
        Connection conexao = Conexao.obterConexao();
        PreparedStatement comando = null;
        
        String sql = "INSERT INTO arbitro (nomeArbitro, identidade, qtd_partidas) VALLUES";
        sql += "("+this.nomeArbitro+", "+identidade+", "+qtd_partidas+" )";
        
        try{
            comando = conexao.prepareStatement("INSERT INTO arbitro (nomeArbitro, identidade, qtd_partidas) VALUES (?, ?, ?)");//? numero de colunas
            comando.setString(1, this.getNomeArbitro());//verificar
            comando.setObject(2, identidade);
            comando.setInt(3, qtd_partidas);
            comando.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inclusão realizada com sucesso !","Tabela ARBITRO",JOptionPane.INFORMATION_MESSAGE);
            
            System.out.println("\n\tARBITRO");
            System.out.println("Arbitro: "+this.nomeArbitro);
            System.out.println("Identidade: "+identidade);
            System.out.println("Partidas ministradas: "+qtd_partidas);
            
        }catch (SQLException ex){
            System.err.println("Erro ao incluir dados na tabela ARBITRO \n"+ex.toString());
        }
        finally{
            try{
                comando.close();
                conexao.close();
            }catch (SQLException ex){
                System.err.println("Erro ao desconectar do Banco de Dados - Tabela ARBITRO\n"+ex.toString());
            }
        }
    }
    //tabela PONTOS
    private boolean PONTOS(String time1, int qtd_P1, int set1, String time2, int qtd_P2, int set2){
        Connection conexao = Conexao.obterConexao();
        PreparedStatement comando = null;
        
        String sql = "INSERT INTO pontos (time1, qtd_P1, set1, time2, qtd_P2, set2) VALLUES";
        sql += "("+time1+", "+qtd_P1+", "+set1+", "+time2+", "+qtd_P2+", "+set2+") ";
        
        try{
            comando = conexao.prepareStatement("INSERT INTO pontos (time1, qtd_P1, set1, time2, qtd_P2, set2) VALUES (?, ?, ?, ?, ?, ?)");//? numero de colunas
            comando.setString(1, time1);
            comando.setInt(2, qtd_P1);
            comando.setInt(3, set1);
            comando.setString(4, time2);
            comando.setInt(5, qtd_P2);
            comando.setInt(6, set2);
            comando.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inclusão realizada com sucesso !","Tabela PONTOS",JOptionPane.INFORMATION_MESSAGE);
            
            System.out.println("\n\tPONTOS");
            System.out.println("Time 1: "+time1);
            System.out.println("Pontos = "+qtd_P1);
            System.out.println("Sets = "+set1);
            
            System.out.println("\nTime 2: "+time2);
            System.out.println("Pontos = "+qtd_P2);
            System.out.println("Sets = "+set2);
            
        }catch (SQLException ex){
            System.err.println("Erro ao incluir dados na tabela - PONTOS \n "+ex.toString());
        }
        finally{
            try{
                comando.close();
                conexao.close();
            }catch (SQLException ex){
                System.err.println("Erro ao desconectar com o banco de dados - Tabela PONTOS"+ex.toString());
            }
        }
        return true;
    }
    
    private boolean RESULTADO(String vencedor, int set_vencedor, String perdedor, int set_perdedor, int ano){
        Connection conexao = Conexao.obterConexao();
        PreparedStatement comando = null;
        
        String sql = "INSERT INTO resultado (vencedor, set_vencedor, perdedor, set_perdedor, ano) VALLUES";
        sql += "("+vencedor+", "+set_vencedor+", "+perdedor+", "+set_perdedor+", "+ano+") ";
        
        try{
            comando = conexao.prepareStatement("INSERT INTO resultado (vencedor, set_vencedor, perdedor, set_perdedor, ano) VALUES (?, ?, ?, ?, ? )");//? numero de colunas
            comando.setString(1, vencedor);
            comando.setInt(2, set_vencedor);
            
            comando.setString(3, perdedor);
            comando.setInt(4, set_perdedor);
            comando.setInt(5, ano);
            comando.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inclusão realizada com sucesso !","Tabela RESULTADO",JOptionPane.INFORMATION_MESSAGE);
            
            System.out.println("\n\tRESULTADO");
            System.out.println("Vencedor do jogo: "+vencedor);
            System.out.println("Set do vencedor = "+set_vencedor);
            
            System.out.println("Adversário: "+perdedor);
            System.out.println("Set do adversário: "+set_perdedor);
            System.out.println("Ano: "+ano);
            
        }catch (SQLException ex){
            System.out.println("Erro ao incluir dados na tabela RESULTADO \n"+ex.toString());
        }
        finally{
            try{
                comando.close();
                conexao.close();
            }catch (SQLException ex){
                System.out.println("Erro ao desconectar com o banco de dados - Tabela RESULTADO \n"+ex.toString());
                return false;
            }
        }
        return true;
    }
    
    public void JOGO(String time1, String time2, String modalidade, String nomeArbitro){
        Connection conexao = Conexao.obterConexao();
        PreparedStatement comando = null;
        
        String sql = "INSERT INTO jogo (time1, time2, modalidade, nomeArbitro) VALLUES";
        sql += "("+time1+", "+time2+", "+modalidade+", "+nomeArbitro+") ";
        
        try{
            comando = conexao.prepareStatement("INSERT INTO jogo (time1, time2, modalidade, nomeArbitro) VALUES (?, ?, ?, ?)");//? numero de colunas
            comando.setString(1, time1);
            comando.setString(2, time2);
            comando.setString(3, modalidade);
            comando.setString(4, nomeArbitro);
            comando.executeUpdate();
            JOptionPane.showMessageDialog(null, "Inclusão realizada com sucesso !","Tabela JOGO",JOptionPane.INFORMATION_MESSAGE);
            
            System.out.println("\n\tJOGO");
            System.out.println("Time 1: "+time1);
            System.out.println("Time 2 = "+time2);
            System.out.println("Modalidade: "+modalidade);
            System.out.println("Árbitro: "+nomeArbitro);
            
        }catch (SQLException ex){
            System.out.println("Erro ao incluir dados na tabela JOGO \n"+ex.toString());
        }
        finally{
            try{
                comando.close();
                conexao.close();
            }catch (SQLException ex){
                System.out.println("Erro ao desconectar com o banco de dados - Tabela JOGO \n"+ex.toString());
            }
        }
    }
    
    
    
    public String getNomeArbitro() {
        return nomeArbitro;
    }
    public void setNomeArbitro(String nomeArbitro) {
        this.nomeArbitro = nomeArbitro;
    }

    public ConexaoBD() {
    }

    public ConexaoBD(String time1, String time2, String tecnico1, String tecnico2, String nomeArbitro, int pontos) {
        this.time1 = time1;
        this.time2 = time2;
        this.tecnico1 = tecnico1;
        this.tecnico2 = tecnico2;
        this.nomeArbitro = nomeArbitro;
        this.pontos = pontos;
    }
    
    
}
