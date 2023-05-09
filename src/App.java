import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class App {
  public static void main(String[] args) throws Exception {
    System.out.println("Hello World!!!");

    //fazer um conexão http e buscar os top 250 filmes
    String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
    URI endereco = URI.create(url);
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    String body = response.body();

    //Pegar os mais populares
    String urlMostPopular = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
    URI enderecoMostPopular = URI.create(urlMostPopular);
    HttpClient client1 = HttpClient.newHttpClient();
    HttpRequest requisicao = HttpRequest.newBuilder(enderecoMostPopular).GET().build();
    HttpResponse<String> resposta = client1.send(requisicao, HttpResponse.BodyHandlers.ofString());
    String corpo = resposta.body();


    //extrair só os dados que interessam (titulo,  poster, classificação)
    var parser = new JsonParser();
    List<Map<String, String>> listaDeFilmes = parser.parse(body);

    List<Map<String, String>> maisPopulares = parser.parse(corpo);


    //exibir e manipular os dados
      var gerador = new GeradorDeFigurinhas();
    for ( int i = 0; i < 10; i++) {
      Map<String, String> filme = listaDeFilmes.get(i);

      String urlImagem = filme.get("image").replace("((@+)(.*).jpg$", "$1.jpg");
      String titulo = filme.get("title");

      InputStream inputStream = new URL(urlImagem).openStream();
      String nomeArquivo = "saida/" + titulo + ".png";

      gerador.cria(inputStream, nomeArquivo);

      System.out.println(titulo);
      System.out.println();
    }
  }
}
