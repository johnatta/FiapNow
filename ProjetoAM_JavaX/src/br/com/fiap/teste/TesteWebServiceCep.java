package br.com.fiap.teste;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.com.fiap.ws.Webservicecep;

public class TesteWebServiceCep {

	public static void main(String[] args){

		try {
			JAXBContext jc = JAXBContext.newInstance(Webservicecep.class);

			Unmarshaller u = jc.createUnmarshaller();
			URL url = new URL( "http://cep.republicavirtual.com.br/web_cep.php?cep=09371160&formato=xml" );
			Webservicecep cep = (Webservicecep) u.unmarshal( url );

			System.out.println(cep.getResultado_txt());
			System.out.println(cep.getResultado());
			System.out.println(cep.getUf());
			System.out.println(cep.getCidade());
			System.out.println(cep.getBairro());
			System.out.println(cep.getLogradouro());
			System.out.println(cep.getTipo_logradouro());

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
