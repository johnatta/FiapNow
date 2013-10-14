package br.com.fiap.I18N;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class UtilsNLS {

	protected static ClassLoader getCurrentClassLoader(Object defaultObject){
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if(loader == null){
			loader = defaultObject.getClass().getClassLoader();
		}
		return loader;
	}
	
	/**
	 * Retorna a String da internacionalização conforme parâmetros
	 *
	 * @param bundleName Bundle das mensagens
	 * @param key Chave da mensagem
	 * @param params[] parâmetros para retorno da mensaagem
	 * @param locale Local/Linguagem que será retornada a mensagem
	 * @author Ariel Molina 
	 */
	public static String getMessageResourceString(String bundleName, String key, Object params[], Locale locale){
		String text = null;
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, getCurrentClassLoader(params));
		try{
			text = bundle.getString(key);
		} catch(MissingResourceException e){
			text = "?? key " + key + " not found ??";
		}
		if(params != null){
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}
		return text;
	}

}
