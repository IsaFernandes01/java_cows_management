package repositories;// Repository não conhece quem o chama

import connection.Conn;
import entities.Manejo;
import entities.Pasto;
import entities.Vaca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ManejoRepository {
    private int nextId = 1;

    public void insert(int idVaca, int idPasto) {
        String sql = "INSERT INTO manejos(idVaca, idPasto) VALUES (?, ?)";

        PreparedStatement stmt = null;

        try {
            Connection conn = Conn.getConn();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idVaca);
            stmt.setInt(2, idPasto);

            stmt.executeUpdate();

            System.out.println("Manejo cadastrado com sucesso no banco!");
        } catch (SQLException e) {
            if (e.getErrorCode() == 1452 && e.getMessage().indexOf("manejos_ibfk_1") > 0) {
                System.out.println("Vaca inexistente");
                return;
            }
            else if (e.getErrorCode() == 1452 && e.getMessage().indexOf("manejos_ibfk_2") > 0) {
                System.out.println("Pasto inexistente");
                return;
            } else {
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
            }
        }
    }

    public Manejo findById(Integer id) {
        Manejo manejo = null;
        String sql = """
        SELECT
            m.id,
            m.idVaca,
            m.idPasto,
            m.dataManejo,
            v.nome AS vaca,
            p.local AS pasto
        FROM manejos AS m 
        JOIN vacas AS v ON v.id = m.idVaca
        JOIN pastos AS p ON p.id = m.idPasto
        WHERE m.id = ?
        """;

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conn.getConn();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                int idManejo = rs.getInt("id");
                int idVaca = rs.getInt("idVaca");
                int idPasto = rs.getInt("idPasto");
                Date dataManejo = rs.getDate("dataManejo");
                String vaca = rs.getString("vaca");
                String pasto = rs.getString("pasto");

                manejo = new Manejo(idManejo, idVaca, idPasto, dataManejo, vaca, pasto);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            return null;
        }

        return manejo;
    }

    public ArrayList<Manejo> findAll() {
        ArrayList<Manejo> manejos = new ArrayList<>();

        String sql = """
        SELECT
            m.id,
            m.idVaca,
            m.idPasto,
            m.dataManejo,
            v.nome AS vaca,
            p.local AS pasto
        FROM manejos AS m 
        JOIN vacas AS v ON v.id = m.idVaca
        JOIN pastos AS p ON p.id = m.idPasto;
        """;

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conn.getConn();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int idVaca = rs.getInt("idVaca");
                int idPasto = rs.getInt("idPasto");
                Date dataManejo = rs.getDate("dataManejo");
                String vaca = rs.getString("vaca");
                String pasto = rs.getString("pasto");

                Manejo manejo = new Manejo(id, idVaca, idPasto, dataManejo, vaca, pasto);

                manejos.add(manejo);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            return null;
        }

        return manejos;
    }

    public void remove(Integer id) {
        String sql = "DELETE FROM manejos WHERE id = ?";

        PreparedStatement stmt = null;

        try {
            Connection conn = Conn.getConn();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rowCountAffected = stmt.executeUpdate();

            if (rowCountAffected > 0) {
                System.out.println("Manejo removido com sucesso!");
                return;
            } else {
                System.out.println("Nenhum manejo encontrado com o ID " + id);
                return;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            return;
        }
    }
}
