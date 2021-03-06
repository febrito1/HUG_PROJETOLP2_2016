package banco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import hotel.SistemaController;
import restaurante.RestauranteController;

public class BancoDeDados implements Serializable{


	private static final long serialVersionUID = 6955874279628925670L;

	private static BancoDeDados instance = null;
	
	private final String directory = "arquivos_sistema";
	
	private boolean sistemaLiberado;
	
	private ObjectOutputStream sistemaControllerOutput;
	private ObjectInputStream sistemaControllerInput;
	private final String sistemaControllerFile = "hug.dat";
	private SistemaController sistemaController;
	private RestauranteController restauranteController;
	private static final String FIM_DE_LINHA = System.lineSeparator();
	
	private BancoDeDados(){}
	
	public static BancoDeDados getInstance(){
		if(instance == null){
			instance = new BancoDeDados();
		}
		return instance;
	}
	
	public void iniciar() throws IOException{
		iniciarSistemaController();
	}
	
	public void fechar() throws IOException{
		fecharSistemaController();
	}
	
	private void iniciarSistemaController() throws IOException{
		try{
			
			sistemaControllerInput = new ObjectInputStream(new FileInputStream(directory + "/" + sistemaControllerFile));
			
			try{
				
				sistemaController = (SistemaController) sistemaControllerInput.readObject();
				
			}catch(ClassNotFoundException | EOFException e) {
				sistemaController = new SistemaController();
			}
		}catch(FileNotFoundException exp){	
			
			sistemaController = new SistemaController();
			File f = new File(directory + "/" + sistemaControllerFile);	
			f.getParentFile().mkdirs();
			
			try{
				f.createNewFile();
			}catch(IOException e2){
				System.out.println("deu erro");
			}
		}catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
	}
	
	
	public String historicoRestaurante() {
		String historico = "";
		for (int i = 0; i < restauranteController.getCardapio().size(); i++) {
			historico = "Menu do Restaurante: " + restauranteController.getCardapio().size() + " itens no cardapio" + FIM_DE_LINHA + "==> Item "
					+ (i + 1) + ":" + FIM_DE_LINHA + "Nome: " + restauranteController.getCardapio().get(i).getNome() + " Preco: R$" 
					+ restauranteController.getCardapio().get(i).getPreco()+ 0 + FIM_DE_LINHA + "Descricao: " + restauranteController.getCardapio().get(i).getDescricao()
					+ FIM_DE_LINHA + FIM_DE_LINHA;
		}

		return historico;
	}
	
	public String refeicaoHistorico() throws Exception {
		String historico = "";
		for (int i = 0; i < restauranteController.getCardapio().size(); i++) {
			historico = "==> Item" + (i + 1) + ":" + FIM_DE_LINHA + "Nome: " + restauranteController.getCardapio().get(i).getNome() + " Preco: R$"
					+ restauranteController.getCardapio().get(i).getPreco() + FIM_DE_LINHA + "Descricao: " +  restauranteController.getCardapio().get(i).getDescricao()
					+ FIM_DE_LINHA + "Pratos: ";
		}

		return historico;
	}
	
	public String historicoHospede(String id) throws Exception{
		String historico = "Cadastro de Hospedes: "+ sistemaController.getClientesCadastrados().size() + " hospedes registrados"+ FIM_DE_LINHA;
		for (int i = 0; i < sistemaController.getClientesCadastrados().size(); i++) {
			historico += 
			"==> Hospede " + (i+1)+ ":"+ FIM_DE_LINHA +
			"Email: " + sistemaController.getClientesCadastrados().get(id).getEmailHospede() +FIM_DE_LINHA+
			"Nome: "+ sistemaController.getClientesCadastrados().get(id).getNomeHospede()+ FIM_DE_LINHA+
			"Data de nascimento: " + sistemaController.getClientesCadastrados().get(id).getAnoNascimento()+FIM_DE_LINHA
			+ FIM_DE_LINHA;
		}
		return historico;
	}

	public String historicoTransacoes(){
		
		String historico = "";
		double precoTotal = 0.0;
		
		for (int i = 0; i < sistemaController.getTransacaoes().size(); i++) {
			precoTotal += sistemaController.getTransacaoes().get(i).getTotalGasto(); 
			historico ="==> Nome: " + sistemaController.getTransacaoes().get(i).getNomeCliente() + FIM_DE_LINHA +
					"Gasto: R$" + sistemaController.getTransacaoes().get(i).getTotalGasto() + FIM_DE_LINHA 
					+ " Detalhes: " + sistemaController.getTransacaoes().get(i).getTransacao()+ FIM_DE_LINHA +
					"..." + FIM_DE_LINHA;
		}
		String historico_2 = " ";
		for(int i = 0; i < sistemaController.getTransacaoes().size(); i++){
			historico_2 = "===== Resumo de transacoes ====="+ FIM_DE_LINHA + 
		"Lucro total: R$" + precoTotal + FIM_DE_LINHA + 
		"Total de transacoes:" + sistemaController.getTransacaoes().size() + FIM_DE_LINHA +
		"Lucro medio por transacao: R$" + (precoTotal/sistemaController.getTransacaoes().size()) + FIM_DE_LINHA;
		}
		
		return historico + historico_2;
		
	}
	
	public void escreveHistorico() throws Exception{
		File arquivo = new File("cad_transacoes.txt");
		try{
			arquivo.createNewFile();
			FileReader fr = new FileReader(arquivo);
			FileWriter fw = new FileWriter(arquivo);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(historicoTransacoes());
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			bw.close();
			while(linha != null){
				linha = br.readLine();
			}
		}catch(IOException e){
			System.out.println("aa");
		}
	}
	
	public void escreveRelatorioRestaurante() throws Exception{
		
		File arquivoRestaurante = new File("cad_restaurante.txt");
		try{
			arquivoRestaurante.createNewFile();
			FileReader fr = new FileReader(arquivoRestaurante);
			
			FileWriter fw = new FileWriter(arquivoRestaurante);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(historicoRestaurante());
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			
			bw.close();
		while(linha != null){
			linha = br.readLine();
		}
		}catch (IOException e){
		}
	
	}
	
	public void escreveRelatorioHospede(String id) throws Exception{
		File arquivoHospede = new File("cad_hospedes.txt");
		File arquivoRestaurante = new File("cad_restaurante.txt");
		try{
			arquivoHospede.createNewFile();
			FileReader fr = new FileReader(arquivoHospede);
			
			FileWriter fw = new FileWriter(arquivoHospede);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(historicoHospede(id));
			BufferedReader br = new BufferedReader(fr);
			String linha = br.readLine();
			
			bw.close();
		while(linha != null){
			linha = br.readLine();
		}
		}catch (IOException e){
		}
	
	}
	
	public SistemaController getSistemaController() {
		return this.sistemaController;
	}
	
	public boolean isSistemaLiberado() {
		return sistemaLiberado;
	}
	
	
	public void setSistemaLiberado(boolean status) {
		this.sistemaLiberado = status;
	}
	
	
	private void fecharSistemaController() throws IOException{
		try{
			
			sistemaControllerOutput = new ObjectOutputStream( new FileOutputStream(directory + "/" + sistemaControllerFile));
			sistemaControllerOutput.writeObject(sistemaController);
			
			sistemaControllerOutput.close();
		}catch(FileNotFoundException e){
			File f = new File(directory + "/" + sistemaControllerFile);

			f.getParentFile().mkdirs();
		} catch (IOException e1) {
			System.err.println("IOException: " + e1.getMessage());
		}
	}
}
