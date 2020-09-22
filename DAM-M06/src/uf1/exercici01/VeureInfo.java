package uf1.exercici01;

import java.io.File;

public class VeureInfo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File f = new File("src" + File.separator + "uf1" + File.separator + "exercici01" + File.separator + args[0]);
		
		if (f.exists()) {
			if(f.isDirectory()) {
				
				 String[] arxius = f.list();
				 for (int i = 0; i<arxius.length; i++){
					 System.out.println(arxius[i]);
				 }
				
			}else if(f.isFile()) {
				
				 System.out.println("Nom del fitxer : "+f.getName());
				 System.out.println("Ruta           : "+f.getPath());
				 System.out.println("Ruta absoluta  : "+f.getAbsolutePath());
				 System.out.println("Es pot escriure: "+f.canRead());
				 System.out.println("Es pot llegir  : "+f.canWrite());
				 System.out.println("Grandaria      : "+f.length());
				 System.out.println("Es un directori: "+f.isDirectory());
				 System.out.println("Es un fitxer   : "+f.isFile());
				
			}
		}
		if(f.isHidden()) {
			System.out.println("El archiu está ocult");
		}
	}
}
