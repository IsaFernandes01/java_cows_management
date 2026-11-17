package repositories;// Repository não conhece quem o chama

import connection.Conn;
import entities.Pasto;
import entities.Vaca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PastoRepository {
    private int nextId = 1;

    public void insert(String local, int capacidade) {
        String sql = "INSERT INTO pastos(local, capacidade) VALUES (?, ?)";

        PreparedStatement stmt = null;

        try {
            Connection conn = Conn.getConn();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, local);
            stmt.setInt(2, capacidade);

            stmt.executeUpdate();
            System.out.println("Pasto cadastrado com sucesso no banco!");

        } catch(SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        }
    }

    public Pasto findById(Integer id) {
        Pasto pasto = null;
        String sql = "SELECT * FROM pastos WHERE id = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conn.getConn();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                int idPasto = rs.getInt("id");
                String local = rs.getString("local");
                int capacidade = rs.getInt("capacidade");
                int rebanhoAtual = rs.getInt("rebanhoAtual");

                pasto = new Pasto(id, local, capacidade, rebanhoAtual);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            return null;
        }

        return pasto;
    }

    public ArrayList<Pasto> findAll() {
        ArrayList<Pasto> pastos = new ArrayList<>();

        String sql = "SELECT * FROM pastos";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conn.getConn();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String local = rs.getString("local");
                int capacidade = rs.getInt("capacidade");
                int rebanhoAtual = rs.getInt("rebanhoAtual");

                Pasto pasto = new Pasto(id, local, capacidade, rebanhoAtual);

                pastos.add(pasto);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            return null;
        }

        return pastos;
    }

    public void update(Pasto pasto) { // Nova instancia atualizada
        String sql = "UPDATE pastos SET local = ?, capacidade = ?, rebanhoAtual = ? WHERE id = ?";

        // Verificacao básica
        if (pasto.getCapacidade() < pasto.getRebanhoAtual()) {
            System.out.println("Valor da capacidade violado");
            return;
        }

        PreparedStatement stmt = null;

        try {
            Connection conn = Conn.getConn();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pasto.getLocal());
            stmt.setInt(2, pasto.getCapacidade());
            stmt.setInt(3, pasto.getRebanhoAtual());
            stmt.setInt(4, pasto.getId());

            int rowCountAffected = stmt.executeUpdate();

            if (rowCountAffected > 0) {
                System.out.println("Pasto atualizado com sucesso!");
                return;
            } else {
                System.out.println("Nenhuma vaca encontrada com o ID " + pasto.getId());
                return;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        }
    }

    public void remove(Integer id) {
        String sql = "DELETE FROM pastos WHERE id = ?";

        PreparedStatement stmt = null;

        try {
            Connection conn = Conn.getConn();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rowCountAffected = stmt.executeUpdate();

            if (rowCountAffected > 0) {
                System.out.println("Pasto removido com sucesso!");
                return;
            } else {
                System.out.println("Nenhum pasto encontrado com o ID" + id);
                return;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            return;
        }
    }
}
