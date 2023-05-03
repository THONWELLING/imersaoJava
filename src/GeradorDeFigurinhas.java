import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;


public class GeradorDeFigurinhas {
  public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

    // Ler a Imagem
    // InputStream inputStream = new FileInputStream(new File("src/entrada/filme.jpg"));
    //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg").openStream();
    BufferedImage imagemOriginal = ImageIO.read(inputStream);

    // Criar Uma Nova Imagem em Memória com Transparência e Tamanho Novo
    int largura = imagemOriginal.getWidth();
    int altura = imagemOriginal.getHeight();
    int novaAltura = altura + 200;
    BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

    // Copiar a Imagem original Para Nova Imagem (em Memória)
    Graphics2D graphics =  novaImagem.createGraphics();
    graphics.drawImage(imagemOriginal, 0, 0, null );

    //Configurar a fonte
    var font = new Font(Font.SANS_SERIF, Font.BOLD,  64);
    graphics.setColor(Color.CYAN);
    graphics.setFont(font);

    //Escrever Uma Frase Na Nova Imagem
    graphics.drawString("TOPZERA", 100, novaAltura - 100);


    //Escrever a Nova Imagem um Arquivo
    ImageIO.write(novaImagem, "png", new File(nomeArquivo));

  };
}
