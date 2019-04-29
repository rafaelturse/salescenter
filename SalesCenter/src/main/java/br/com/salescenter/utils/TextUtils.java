package br.com.salescenter.utils;

import br.com.salescenter.contants.converter.CNPJConverter;
import br.com.salescenter.contants.converter.CPFConverter;
import br.com.salescenter.contants.converter.RGConverter;
import lombok.NonNull;

public class TextUtils {

	public static String firstUpper(@NonNull String text) {
		return text.substring(0, 1).toUpperCase() + text.substring(1, text.length()).toLowerCase();
	}

	public static String formatRG(String rg) {
		RGConverter rgConverter = new RGConverter();
		return rgConverter.getAsString(null, null, rg);
	}
	
	public static String formatCPF(String cpf) {
		CPFConverter cpfConverter = new CPFConverter();
		return cpfConverter.getAsString(null, null, cpf);
	}
	
	public static String formatCNPJ(String cnpj) {
		CNPJConverter cnpjConverter = new CNPJConverter();
		return cnpjConverter.getAsString(null, null, cnpj);
	}
	
	public static String fileName(String text){
		return text.substring(0, text.lastIndexOf("."));
	}
	
	public static String fileExtension(String text){
		return text.substring(text.lastIndexOf(".")+1, text.length());
	}
}
