package restaurante;

public class ExcecaoRestaurante {
	private RefeicaoCompleta refeicao;
	
	public ExcecaoRestaurante(){
		
	}
	public static void ConsultaRestauranteException(String nome, String atributo)throws Exception{
		if(nome == null || nome.trim().isEmpty()){
			throw new Exception("Erro na consulta do restaurante. Nome do prato esto vazio.");
		}if(atributo == null || atributo.trim().isEmpty()){
			throw new Exception("Erro na consulta do restaurante. Atributo esto vazio.");
		}
		
	}
	public static void CadastroInvalidoPrato(String nome,String descricao, double preco) throws Exception{
		if(nome == null || nome.trim().isEmpty()){
			throw new Exception("Erro no cadastro do prato. Nome do prato esta vazio.");
		}if (preco <= 0){
			throw new Exception("Erro no cadastro do prato. Preco do prato eh invalido.");
		}if(descricao == null || descricao.trim().isEmpty()){
			throw new Exception("Erro no cadastro do prato. Descricao do prato esta vazia.");
		}
	}
	public static void CadastroInvalidoRefeicao(String nome,String descricao, String componentes) throws Exception{
		if(nome == null || nome.trim().isEmpty()){
			throw new Exception("Erro no cadastro de refeicao. Nome da refeicao esta vazio.");
		}if(descricao == null || descricao.trim().isEmpty()){
			throw new Exception("Erro no cadastro de refeicao. Descricao da refeicao esta vazia.");
		}if(componentes == null || nome.trim().isEmpty()){ 
			throw new Exception("Erro no cadastro de refeicao. Componente(s) esta(o) vazio(s).");	
		}

	}
}
