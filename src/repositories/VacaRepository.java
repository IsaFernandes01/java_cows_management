package repositories;// Repository não conhece quem o chama
import connection.Conn;
import entities.Vaca;
import interfaces.CrudRepository;

import java.sql.*;
import java.util.ArrayList;

public class VacaRepository implements CrudRepository<Vaca, Integer> {

    @Override
    public void insert(Vaca vaca) {
        String sql = "INSERT INTO vacas(nome, brinco) VALUES (?, ?)";

        PreparedStatement stmt = null;

        try {
            Connection conn = Conn.getConn();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, vaca.getNome());
            stmt.setString(2, vaca.getBrinco());

            stmt.executeUpdate();
            System.out.println("Vaca cadastrada com sucesso no banco!");

            // Duplicate entry '123' for key 'vacas.brinco' Errcode: 1062

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062 && e.getMessage().indexOf("vacas.brinco") > 0) {
                System.out.println("O brinco deve ser único para cada animal");
            } else {
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
            }
        }
    }

    @Override
    public Vaca findById(Integer id)  {
        Vaca vaca = null;
        String sql = "SELECT * FROM vacas WHERE id = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conn.getConn();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                int idVaca = rs.getInt("id");
                String nome = rs.getString("nome");
                String brinco = rs.getString("brinco");
                boolean isManejada = rs.getBoolean("isManejada");

                vaca = new Vaca(id, nome, brinco, isManejada);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            return null;
        }

        return vaca;
    }

    @Override
    public ArrayList<Vaca> findAll() {
        ArrayList<Vaca> vacas = new ArrayList<>();

        String sql = "SELECT * FROM vacas";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection conn = Conn.getConn();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String brinco = rs.getString("brinco");
                boolean isManejada = rs.getBoolean("isManejada");

                Vaca vaca = new Vaca(id, nome, brinco, isManejada);

                vacas.add(vaca);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            return null;
        }

        return vacas;
    }

    @Override
    public void update(Vaca vaca) { // Nova instancia atualizada
        String sql = "UPDATE vacas SET nome = ?, brinco = ?, isManejada = ? WHERE id = ?";

        PreparedStatement stmt = null;

        try {
            Connection conn = Conn.getConn();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, vaca.getNome());
            stmt.setString(2, vaca.getBrinco());
            stmt.setBoolean(3, vaca.getIsManejada());
            stmt.setInt(4, vaca.getId());

            int rowCountAffected = stmt.executeUpdate();

            if (rowCountAffected > 0) {
                System.out.println("Vaca atualizada com sucesso!");
                return;
            } else {
                System.out.println("Nenhuma vaca encontrada com o ID " + vaca.getId());
                return;
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062 && e.getMessage().indexOf("vacas.brinco") > 0) {
                System.out.println("O brinco deve ser único para cada animal");
            } else {
                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());
            }
        }
    }

    @Override
    public void remove(Integer id) {
        String sql = "DELETE FROM vacas WHERE id = ?";

        PreparedStatement stmt = null;

        try {
            Connection conn = Conn.getConn();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rowCountAffected = stmt.executeUpdate();

            if (rowCountAffected > 0) {
                System.out.println("Vaca removida com sucesso!");
                return;
            } else {
                System.out.println("Nenhuma vaca encontrada com o ID " + id);
                return;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
            return;
        }
    }
}