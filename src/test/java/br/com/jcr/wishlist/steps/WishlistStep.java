package br.com.jcr.wishlist.steps;

import br.com.jcr.wishlist.models.Product;
import br.com.jcr.wishlist.models.Wishlist;
import br.com.jcr.wishlist.utilities.RestAssuredExtension;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WishlistStep {
    private static ResponseOptions<Response> response;

    @Before
    public void TestSetup(){
        RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
    }

    @Dado("Que eu irei enviar um Objeto Wishlist contendo CustomerId e Um lista de produtos com Id e Description para o metodo POST em {string}")
    public void queEuIreiEnviarUmObjetoWishlistContendoCustomerIdEUmListaDeProdutosComIdEDescriptionParaOMetodoPOSTEm(String uri) {
        List<Product> productList = new ArrayList<>();
        for(int i = 0; i < 19; i++){
            productList.add(Product.builder()
                    .id("30")
                    .description("Teste de Produto 1")
                    .build());
        }
        Wishlist wishlist = Wishlist.builder().customerId("8000").products(
                productList).build();
        response = RestAssuredExtension.InsertWishList(uri,wishlist);
    }

    @Entao("Eu Recebo status {int}")
    public void euReceboStatus(int status) {
        assertThat(response.getStatusCode(), equalTo(status));
    }

    @Dado("Que eu irei enviar um Objeto Wishlist contendo CustomerId e Um lista com mais de vinte produtos com Id e Description para o metodo POST em {string}")
    public void queEuIreiEnviarUmObjetoWishlistContendoCustomerIdEUmListaComMaisDeVinteProdutosComIdEDescriptionParaOMetodoPOSTEm(String uri) {
        List<Product> productList = new ArrayList<>();
        for(int i = 0; i < 21; i++){
            productList.add(Product.builder()
                    .id("31")
                    .description("Teste de Produto 1")
                    .build());
        }
        Wishlist wishlist = Wishlist.builder().customerId("9000").products(productList).build();
        response = RestAssuredExtension.InsertWishList(uri,wishlist);
    }

    @Entao("Eu Recebo a mensagem {string}")
    public void euReceboAMensagem(String mensagem) {
        assertThat(response.getBody().jsonPath().get("mensagem"), equalTo("Limite máximo de 20 produtos na lista de desejos atingida!"));
    }

    @Dado("Que eu irei enviar um Objeto Product  para o metodo POST em {string}")
    public void queEuIreiEnviarUmObjetoProducParaOMetodoPOSTEm(String uri) {

        response = RestAssuredExtension.InsertProductInWishList(uri,Product.builder()
                .id("31")
                .description("Teste de Produto 1")
                .build());
    }


    @Dado("Fazer a requisição GET em passando CustomerId {string}")
    public void fazerARequisiçãoGETEmPassandoCustomerId(String uri) {
        response = RestAssuredExtension.getWishlist(uri);
    }

    @Entao("Eu Recebo um objeto com uma lista de produtos com tamanho maior que {int}")
    public void euReceboUmObjetoComUmaListaDeProdutosComTamanhoMaiorQue(int tamanho) {
        assertThat(response.body().jsonPath().setRootPath("retorno").getList("products").size(), allOf(greaterThan(tamanho)));
    }

    @Entao("Eu Recebo um objeto com uma lista de produtos com tamanho igual a {int}")
    public void euReceboUmObjetoComUmaListaDeProdutosComTamanhoIgualHa(int tamanho) {
        assertThat(response.body().jsonPath().setRootPath("retorno").getList("products").size(), equalTo(tamanho));
    }

    @Dado("Fazer a requisição delete em passando CustomerId {string}")
    public void fazerARequisiçãoDeleteEmPassandoCustomerId(String uri) {
        response = RestAssuredExtension.deleteWishlist(uri);
    }

    @Entao("Entao Eu Recebo status {int}")
    public void entaoEuReceboStatus(int status) {
        assertThat(response.getStatusCode(), equalTo(status));
    }


}
