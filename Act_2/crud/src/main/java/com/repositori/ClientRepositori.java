package com.repositori;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositori {
    private Coneccion conn;

    public ClientRepositori(Coneccion conn) {
        this.conn = conn;
    }

    public void create(Client client) {
        try {
            String query = "INSERT INTO Clients (codi_cli, nom_cli, nif, adreca, ciutat, telefon) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, client.getCodi_cli());
            stmt.setString(2, client.getNom_cli());
            stmt.setString(3, client.getNif());
            stmt.setString(4, client.getAdreca());
            stmt.setString(5, client.getCiutat());
            stmt.setInt(6, client.getTelefon());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Client findById(int codiClient) {
        try {
            String query = "SELECT * FROM Clients WHERE codi_cli = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, codiClient);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Client client = new Client();
                client.setCodi_cli(rs.getInt("codi_cli"));
                client.setNom_cli(rs.getString("nom_cli"));
                client.setNif(rs.getString("nif"));
                client.setAdreca(rs.getString("adreca"));
                client.setCiutat(rs.getString("ciutat"));
                client.setTelefon(rs.getInt("telefon"));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public Client findByNif(String clientNif) {
        Client client = null;
        try {
            String query = "SELECT * FROM Clients WHERE nif = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, clientNif);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                client = new Client();
                client.setCodi_cli(rs.getInt("codi_cli"));
                client.setNom_cli(rs.getString("nom_cli"));
                client.setNif(rs.getString("nif"));
                client.setAdreca(rs.getString("adreca"));
                client.setCiutat(rs.getString("ciutat"));
                client.setTelefon(rs.getInt("telefon"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    public void update(Client client) {
        try {
            String query = "UPDATE Clients SET nom_cli = ?, nif = ?, adreca = ?, ciutat = ?, telefon = ? WHERE codi_cli = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, client.getNom_cli());
            stmt.setString(2, client.getNif());
            stmt.setString(3, client.getAdreca());
            stmt.setString(4, client.getCiutat());
            stmt.setInt(5, client.getTelefon());
            stmt.setInt(6, client.getCodi_cli()); 

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cliente actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún cliente con el ID proporcionado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el cliente: " + e.getMessage());
        }
    }

    public void deleteById(int codiClient) {
        try {
            //actualiza la asociación de proyectos con el cliente
            String updateProjectsQuery = "UPDATE Projectes SET codi_client = NULL WHERE codi_client = ?";
            PreparedStatement updateProjectsStmt = conn.prepareStatement(updateProjectsQuery);
            updateProjectsStmt.setInt(1, codiClient);
            updateProjectsStmt.executeUpdate();
    
            // Finalmente elimina el cliente
            String deleteClientQuery = "DELETE FROM Clients WHERE codi_cli = ?";
            PreparedStatement deleteClientStmt = conn.prepareStatement(deleteClientQuery);
            deleteClientStmt.setInt(1, codiClient); 
            deleteClientStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try {
            String query = "SELECT * FROM Clients";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setCodi_cli(rs.getInt("codi_cli"));
                client.setNom_cli(rs.getString("nom_cli"));
                client.setNif(rs.getString("nif"));
                client.setAdreca(rs.getString("adreca"));
                client.setCiutat(rs.getString("ciutat"));
                client.setTelefon(rs.getInt("telefon"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

}
