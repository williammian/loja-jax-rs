package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;

@Path("projetos2")
public class ProjetoResource2 {
	
	@Path("{id}")
	@GET
    @Produces(MediaType.APPLICATION_XML)
    public Projeto busca(@PathParam("id") long id) {
        Projeto projeto = new ProjetoDAO().busca(id);
        return projeto;
    }
	
	@POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response adiciona(Projeto projeto){
        new ProjetoDAO().adiciona(projeto);
        URI uri = URI.create("/projetos2/" + projeto.getId());	
        return Response.created(uri).build();
    }

}
