package quarto;

public enum TipoQuarto implements PrecoQuarto{
	
	LUXO{
		@Override
		public double getPreco(){
			return 250.0;
		}
	},
	SIMPLES{
		@Override
		public double getPreco(){
			return 100.0;
		}
	},
	PRESIDENCIAL{
		@Override
		public double getPreco(){
			return 450.0;
		}
	}
}
