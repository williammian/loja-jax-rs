package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Projeto;

public class ProjetoTest2 {
	
private HttpServer server;
	
	@Before
    public void before() {
        this.server = Servidor.inicializaServidor();
    }

    @After
    public void mataServidor() {
        server.stop();
    }
	
	@Test
    public void testaQueAConexaoComOServidorFuncionaNoPathDeProjetos() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        Projeto projeto = target.path("/projetos2/1").request().get(Projeto.class);
        Assert.assertEquals("Minha loja", projeto.getNome());
    }
	
	@Test
    public void testaQueSuportaNovosProjetos(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        
        Projeto projeto = new Projeto();
        projeto.setAnoDeInicio(2020);
        projeto.setNome("Curso");

        Entity<Projeto> entity = Entity.entity(projeto, MediaType.APPLICATION_XML);

        Response response = target.path("/projetos2").request().post(entity);
        Assert.assertEquals(201, response.getStatus());
        
        String location = response.getHeaderString("Location");
        Projeto projetoCarregado = client.target(location).request().get(Projeto.class);
        Assert.assertTrue(projeto.getNome().equals("Curso"));
    }

}
