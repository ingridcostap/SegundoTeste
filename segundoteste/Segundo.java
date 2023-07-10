package segundoteste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Segundo {
    private int idCandidato;
    private Map<Integer, Candidato> Candidatos;
    private List<Integer> CandidatosAprovados;

    public Segundo() {
    	super();
        this.idCandidato = 1;
        this.Candidatos = new HashMap<>();
        this.CandidatosAprovados = new ArrayList<>();
    }

    public int iniciarProcesso(String nome) throws Exception {
        if (nome == null || nome.isEmpty()) {
            throw new Exception("Nome Inválido.");
        }

        for (Candidato candidato : Candidatos.values()) {
            if (candidato.getNome().equalsIgnoreCase(nome)) {
                throw new Exception("Candidato já participa do processo.");
            }
        }

        int id = idCandidato++;
        Candidato Candidato = new Candidato(id, nome, "Recebido!");
        Candidatos.put(id, Candidato);
        return id;
    }

    public void marcarEntrevista(int codCandidato) throws Exception {
        Candidato Candidato = getCandidato(codCandidato);
        Candidato.setStatus("Qualificado!");
    }

    public void desqualificarCandidato(int codCandidato) throws Exception {
        Candidato Candidato = getCandidato(codCandidato);
        Candidatos.remove(codCandidato);
    }

    public String verificarStatusCandidato(int codCandidato) throws Exception {
        Candidato Candidato = getCandidato(codCandidato);
        return Candidato.getStatus();
    }

    public void aprovarCandidato(int codCandidato) throws Exception {
        Candidato Candidato = getCandidato(codCandidato);
        Candidato.setStatus("Aprovado");
        CandidatosAprovados.add(codCandidato);
    }

    public List<String> obterAprovados() throws Exception {
        List<String> nomesAprovados = new ArrayList<>();
        for (int id : CandidatosAprovados) {
            Candidato Candidato = getCandidato(id);
            nomesAprovados.add(Candidato.getNome());
        }
        return nomesAprovados;
    }

    private Candidato getCandidato(int codCandidato) throws Exception {
        Candidato Candidato = Candidatos.get(codCandidato);
        if (Candidato == null) {
            throw new Exception("Candidato não encontrado.");
        }
        return Candidato;
    }

    private static class Candidato {
        private int id;
        private String nome;
        private String status;

        public Candidato(int id, String nome, String status) {
            this.id = id;
            this.nome = nome;
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static void main(String[] args) throws Exception {
        Segundo sistemaRH = new Segundo();

        Scanner scanner = new Scanner(System.in);

        boolean executando = true;

        while (executando) {
            exibirMenu();
            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    System.out.println("Digite o nome do candidato:");
                    String nomeCandidato = scanner.nextLine();
                    try {
                        int idCandidato = sistemaRH.iniciarProcesso(nomeCandidato);
                        System.out.println("Candidato registrado com sucesso. ID: " + idCandidato);
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case "2":
                    System.out.println("Digite o ID do candidato:");
                    int idMarcarEntrevista = Integer.parseInt(scanner.nextLine());
                    try {
                        sistemaRH.marcarEntrevista(idMarcarEntrevista);
                        System.out.println("Entrevista marcada para o candidato.");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case "3":
                    System.out.println("Digite o ID do candidato:");
                    int idDesqualificar = Integer.parseInt(scanner.nextLine());
                    try {
                        sistemaRH.desqualificarCandidato(idDesqualificar);
                        System.out.println("Candidato desqualificado com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case "4":
                    System.out.println("Digite o ID do candidato:");
                    int idVerificarStatus = Integer.parseInt(scanner.nextLine());
                    try {
                        String statusCandidato = sistemaRH.verificarStatusCandidato(idVerificarStatus);
                        System.out.println("Status do candidato: " + statusCandidato);
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case "5":
                    System.out.println("Digite o ID do candidato:");
                    int idAprovarCandidato = Integer.parseInt(scanner.nextLine());
                    try {
                        sistemaRH.aprovarCandidato(idAprovarCandidato);
                        System.out.println("Candidato aprovado com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case "6":
                    System.out.println("Lista de candidatos aprovados:");
                    sistemaRH.obterAprovados().forEach(System.out::println);
                    break;
                case "0":
                    executando = false;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("==== Sistema de RH - Controle de Candidatos ====");
        System.out.println("Selecione uma opção:");
        System.out.println("1. Registrar candidato");
        System.out.println("2. Marcar entrevista para candidato");
        System.out.println("3. Desqualificar candidato");
        System.out.println("4. Verificar status do candidato");
        System.out.println("5. Aprovar candidato");
        System.out.println("6. Listar candidatos aprovados");
        System.out.println("0. Sair do sistema");
        System.out.print("Opção: ");
    }
}