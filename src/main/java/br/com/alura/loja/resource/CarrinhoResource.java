package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;

@Path("carrinhos")
public class CarrinhoResource {
	
	@Path("{id}")
	@GET
    @Produces(MediaType.APPLICATION_XML)
    public String buscaXML(@PathParam("id") long id) {
        Carrinho carrinho = new CarrinhoDAO().busca(id);
        return carrinho.toXML();
    }
	
//	@Path("{id}")
//	@GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String buscaJSON(@PathParam("id") long id) {
//        Carrinho carrinho = new CarrinhoDAO().busca(id);
//        return carrinho.toJson();
//    }

}
