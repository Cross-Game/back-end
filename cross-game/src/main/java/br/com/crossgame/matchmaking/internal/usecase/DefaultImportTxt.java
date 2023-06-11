package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.usecase.ImportTxt;
import br.com.crossgame.matchmaking.internal.entity.GameRecommendation;
import br.com.crossgame.matchmaking.internal.repository.GameRecommendationRepository;
import br.com.crossgame.matchmaking.internal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class DefaultImportTxt implements ImportTxt {
    private UserRepository userRepository;
    private GameRecommendationRepository gameRecommendationRepository;


    @Override
    public void execute(MultipartFile file, Long idUser) {
/*
    try {
        byte[] content =  file.getBytes();
        GameRecommendation gameRecommendation = new GameRecommendation();
        this.read(content);
        gameRecommendation.setGameName(nomeDoJogo);
        gameRecommendation.setCompany(empresaResponsavel);
        gameRecommendation.setGenre(genero);
        gameRecommendation.setReason(motivo);
        gameRecommendation.setUserName(userRepository.findById(idUser).orElseThrow().getUsername());
        gameRecommendationRepository.save(gameRecommendation);
    }
    catch (IOException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Error with read recommendation file");
    }*/
    }

  /*  public static void leArquivoTxt(byte[] nomeArq) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        int HEADER_SIZE = 43;
        int RECORD_SIZE = 76;

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

         String tipoDeRegistro;
         String tipoDeArquivo;
         Date dataDeGeracao;
         String nomeDoUsuarioQueSolicitouArquivo;
         int tipoDeRegistroCorpo;
         String nomeDoJogo;
         String empresaResponsavel;
         String motivo;
         String genero;

        nomeArq += ".txt";

        List<GameRecommendation> gameRecommendationList = new ArrayList<>();
        // try-catch para abrir o arquivo
        try {
            entrada = new BufferedReader(new FileReader(n));
        }
        catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
            System.exit(1);
        }

        // try-catch para leitura do arquivo
        try {
            registro = entrada.readLine(); // le o primeiro registro do arquivo

            while (registro != null) {
                // 01234567890
                // 00NOTA20231
                tipoRegistro = registro.substring(0,2);     // obtem os 2 primeiros caracteres do registro
                // substring - primeiro argumento é onde começa a substring dentro da string
                // e o segundo argumento é onde termina a substring + 1
                // Verifica se o tipoRegistro é um header, ou um trailer, ou um registro de dados
                if (tipoRegistro.equals("00")) {
                    System.out.println("é um registro de header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2,6));
                    System.out.println("Ano/semestre: " + registro.substring(6,11));
                    System.out.println("Data e hora da gravação do arquivo: " + registro.substring(11,30));
                    System.out.println("Versão do documento de layout: " + registro.substring(30,32));
                }
                else if (tipoRegistro.equals("01")) {
                    System.out.println("é um registro de trailer");
                    qtdRegDadoGravado = Integer.parseInt(registro.substring(2,12));
                    if (contaRegDadoLido == qtdRegDadoGravado) {
                        System.out.println("Quantidade de registros lidos compatível com " +
                                "quantidade de registros gravados");
                    }
                    else {
                        System.out.println("Quantidade de registros lidos incompatível com " +
                                "quantidade de registros gravados");
                    }
                }
                else if (tipoRegistro.equals("02")) {
                    System.out.println("é um registro de dados");
                    curso = registro.substring(2,7).trim();
                    ra = registro.substring(7,15);
                    nome = registro.substring(15,65).trim();
                    disciplina = registro.substring(65,105).trim();
                    media = Double.valueOf(registro.substring(105,110).replace(',','.'));
                    qtdFalta = Integer.parseInt(registro.substring(110,113));
                    Aluno a = new Aluno(ra, nome, curso, disciplina, media, qtdFalta);

                    // para importar esse dado para o banco de dados
                    // repository.save(a);

                    // no nosso caso, não estamos conectados a banco de dados
                    // então vamos add o objeto na listaLida
                    listaLida.add(a);

                    // contabiliza que leu mais um registro de dados
                    contaRegDadoLido++;
                }
                else {
                    System.out.println("tipo de registro inválido");
                }
                // le o proximo registro do arquivo
                registro = entrada.readLine();
            }
            entrada.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
        }

        // Vamos exibir a lista lida
        System.out.println("\nLista contendo os dados lidos do arquivo:");
        for (Aluno a : listaLida) {
            System.out.println(a);
        }

        // Para importar a lista toda para o banco de dados:
        // repository.saveAll(listaLida);

    }



    public static void main(String[] args) {
        List<Aluno> lista = new ArrayList<>();
        lista.add(new Aluno("01221112","Matheus Patricio","ADS",
                "Estrutura de Dados",9.5,5));
        lista.add(new Aluno("01221026","Leonardo Mariano","ADS",
                "Engenharia de Software",6.0,6));
        lista.add(new Aluno("01221176","Leonardo Nakagawa","REDES",
                "Segurança da Informação",4.0,0));
        lista.add(new Aluno("01221056","Yohan Torquato","CCO",
                "Análise de Algoritmos",9.0,0));
        lista.add(new Aluno("01221148","Vitoria Vieira","SIS",
                "Modelagem de Processos",10.0,0));
        lista.add(new Aluno("01221098","Rafael Caxixi","ADS",
                "Pesquisa e Inovação 3",6.0,12));

        for (Aluno a : lista) {
            System.out.println(a);
        }

//        gravaArquivoTxt(lista, "alunos");
        leArquivoTxt("alunos");
    }*/


}
