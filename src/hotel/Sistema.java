package hotel;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Sistema implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = -4002891441825575232L;

	File file = new File("arquivos_sistema/hug.dat");
	FileOutputStream outFile;
		

	public void salvaSistema(SistemaController controller) throws Exception {
		
		try {
			outFile = new FileOutputStream(file);
			BufferedOutputStream outStream = new BufferedOutputStream(outFile);
			ObjectOutputStream stream = new ObjectOutputStream(outStream);
			stream.writeObject(controller);
			stream.close();
		} catch (FileNotFoundException exp) {
			throw new Exception("Arquivo inexistente");
		} catch (IOException exp) {
			throw new Exception("Arquivo inexistente");
		}
	}
	
	public SistemaController restauraSistema(SistemaController controller){
		File file = new File("arquivos_sistema/hug.dat");
		controller = null;
		if(!file.exists()){
			return null;
		}
		try{
			
			FileInputStream inFile = new FileInputStream(file);
			BufferedInputStream inStream = new BufferedInputStream(inFile);
			ObjectInputStream stream = new ObjectInputStream(inStream);
			controller = (SistemaController) stream.readObject();
			stream.close();
			
		}catch(FileNotFoundException exp) {
			System.out.println("FILENOT FOUND "+ exp.getMessage());
		}catch(IOException exp){
			System.out.println("IOEX "+ exp.getMessage());
		} catch (ClassNotFoundException exp) {
			System.out.println("CLASS NOT FOUND "+ exp.getMessage());
		}
		return controller;
	}
	
}