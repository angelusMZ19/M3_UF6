package com.repositori;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ClientRepositori clientRepository;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Establecer la conexión a la base de datos
        Coneccion connection;
        try {
            connection = new Coneccion();
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Clase de conexión no encontrada.");
            e.printStackTrace();
            return;
        }

        clientRepository = new ClientRepositori(connection); // Inicializar clientRepository con la conexión

        boolean exit = false;
        while (!exit) {
            System.out.println("1-. Inserir un nou client");
            System.out.println("2-. Llistar tots els clients");
            System.out.println("3-. Mostrar un client per id");
            System.out.println("4-. Mostrar un client donat el NIF");
            System.out.println("5-. Eliminar un client");
            System.out.println("6-. Actualitzar un client");
            System.out.println("7-. Sortir");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Introdueix el codi del client:");
                    int codi_cli = scanner.nextInt();
                    System.out.println("Introdueix el nom del client:");
                    String nom_cli = scanner.next();
                    System.out.println("Introdueix el NIF del client:");
                    String nif = scanner.next();
                    System.out.println("Introdueix l'adreça del client:");
                    String adreca = scanner.next();
                    System.out.println("Introdueix la ciutat del client:");
                    String ciutat = scanner.next();
                    System.out.println("Introdueix el telèfon del client:");
                    int telefon = scanner.nextInt();

                    Client newClient = new Client(codi_cli, nom_cli, nif, adreca, ciutat, telefon);
                    clientRepository.create(newClient);
                    System.out.println("Client inserit correctament.");
                    break;

                case 2:
                    List<Client> allClients = clientRepository.findAll();
                    System.out.println("Llistat de tots els clients:");
                    for (Client client : allClients) {
                        System.out.println(client);
                    }
                    break;

                case 3:
                    System.out.println("Introdueix l'ID del client a mostrar:");
                    int clientId = scanner.nextInt();
                    Client clientById = clientRepository.findById(clientId);
                    if (clientById != null) {
                        System.out.println("Dades del client:");
                        System.out.println(clientById);
                    } else {
                        System.out.println("No s'ha trobat cap client amb aquest ID.");
                    }
                    break;

                case 4:
                    System.out.println("Introdueix el NIF del client a mostrar:");
                    String clientNif = scanner.next();
                    Client clientByNif = clientRepository.findByNif(clientNif);
                    if (clientByNif != null) {
                        System.out.println("Dades del client:");
                        System.out.println(clientByNif);
                    } else {
                        System.out.println("No s'ha trobat cap client amb aquest NIF.");
                    }
                    break;

                case 5:
                    System.out.println("Introdueix l'ID del client a eliminar:");
                    int deleteClientId = scanner.nextInt();
                    clientRepository.deleteById(deleteClientId);
                    System.out.println("Client eliminat correctament.");
                    break;

                case 6:
                    System.out.println("Introdueix l'ID del client a actualitzar:");
                    int updateClientId = scanner.nextInt();
                    Client clientToUpdate = clientRepository.findById(updateClientId);
                    if (clientToUpdate != null) {
                        System.out.println("Introdueix el nou nom del client:");
                        String updatedNom = scanner.next();
                        System.out.println("Introdueix el nou NIF del client:");
                        String updatedNif = scanner.next();
                        System.out.println("Introdueix la nova adreça del client:");
                        String updatedAdreca = scanner.next();
                        System.out.println("Introdueix la nova ciutat del client:");
                        String updatedCiutat = scanner.next();
                        System.out.println("Introdueix el nou telèfon del client:");
                        int updatedTelefon = scanner.nextInt();

                        clientToUpdate.setNom_cli(updatedNom);
                        clientToUpdate.setNif(updatedNif);
                        clientToUpdate.setAdreca(updatedAdreca);
                        clientToUpdate.setCiutat(updatedCiutat);
                        clientToUpdate.setTelefon(updatedTelefon);

                        clientRepository.update(clientToUpdate);
                    } else {
                        System.out.println("No s'ha trobat cap client amb aquest ID.\n");
                    }
                    break;

                case 7:
                    exit = true;
                    break;

                default:
                    System.out.println("Opció no vàlida. Si us plau, tria una opció del menú.");
            }
        }
        scanner.close();
    }
}
