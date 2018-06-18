package pucrs.myflight.modelo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class GerenciadorVoos {

    private ArrayList<Voo> voos;

    public GerenciadorVoos() {
        this.voos = new ArrayList<>();
    }

    public void ordenarDataHora() {
        //voos.sort(Comparator.comparing(v -> v.getDatahora()));
        voos.sort(Comparator.comparing(Voo::getDatahora));
    }

    public void ordenarDataHoraDuracao() {
        voos.sort(Comparator.comparing(Voo::getDatahora).
                thenComparing(Voo::getDuracao));
    }

    public void adicionar(Voo r) {
        voos.add(r);
    }

    public ArrayList<Voo> listarTodos() {
        return new ArrayList<>(voos);
    }


//    public void carregaDados(String nomeArq) throws IOException {
//        Path path = Paths.get(nomeArq);
//        try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("utf8")))) {
//            sc.useDelimiter("[;\n]"); // separadores: ; e nova linha
//            String header = sc.nextLine(); // pula cabeçalho
//            String cod, nome, desc;
//            int cap;
//            double lat = 0;
//            double longit = 0;
//
//            while (sc.hasNext()) {
//                cod = sc.next();
//                nome = sc.next();
//                desc = sc.next();
//                cap = sc.nextInt();
//                lat = sc.nextDouble();
//                longit = sc.nextDouble();
//                Geo geo = new Geo(lat, longit);
//                Aeroporto aeroDestino = new Aeroporto(cod, nome, geo);
//                Aeroporto aeroOrigem = new Aeroporto(cod, nome, geo);
//                CiaAerea ciaAerea = new CiaAerea(cod, nome);
//                Aeronave aero = new Aeronave(cod, desc, cap);
//                Rota rotaFinal = new Rota(ciaAerea, aeroOrigem, aeroDestino, aero);
//
//                adicionar();
//            }
//        }
//    }

    public ArrayList<Voo> buscarData(LocalDate data) {
       ArrayList<Voo> result = new ArrayList<>();
       for(Voo v: voos)
           if(v.getDatahora().toLocalDate().equals(data))
               result.add(v);
       return result;
    }

    // Tarefa 1: listar os dados de vôos cuja origem é informada
    public ArrayList<Voo> buscarOrigem(String cod) {
        ArrayList<Voo> result = new ArrayList<>();
        for(Voo v: voos)
            if(v.getRota().getOrigem().getCodigo().equals(cod))
                result.add(v);
        return result;
    }

    // Tarefa 1: listar os dados de vôos que operam em determinado período do dia
    public ArrayList<Voo> buscarPeriodo(LocalTime inicio, LocalTime fim) {
        ArrayList<Voo> result = new ArrayList<>();
        for(Voo v: voos) {
            if(v.getDatahora().toLocalTime().compareTo(inicio) >= 0 &&
                    v.getDatahora().toLocalTime().compareTo(fim) <= 0)
                result.add(v);
        }
        return result;
    }
}
