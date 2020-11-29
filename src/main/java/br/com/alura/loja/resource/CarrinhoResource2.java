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

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;

@Path("carrinhos2")
public class CarrinhoResource2 {
	
	@Path("{id}")
	@GET
    @Produces(MediaType.APPLICATION_XML)
    public Carrinho buscaXML(@PathParam("id") long id) {
        Carrinho carrinho = new CarrinhoDAO().busca(id);
        return carrinho;
    }
	
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response adiciona(Carrinho carrinho) {
    	new CarrinhoDAO().adiciona(carrinho);
    	URI uri = URI.create("/carrinhos2/" + carrinho.getId());	
        return Response.created(uri).build();
    }

}
