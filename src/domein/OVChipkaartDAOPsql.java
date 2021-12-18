package domein;

import dao.AdresDAO;
import dao.ReizigerDAO;
import dao.OVChipkaartDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPsql implements OVChipkaartDAO {

    private Connection conn;
    private ReizigerDAO rdao;
    private OVChipkaartDAO ovrdao;


    public OVChipkaartDAOPsql(Connection connection) {
        this.conn = connection;
    }


    public void setOvRdao(OVChipkaartDAO ovrdao) {
        this.ovrdao = ovrdao;
    }

    @Override
    public boolean save(OVChipkaart ovchipkaart) {
        try {
            String query = "INSERT INTO ovchipkaart (kaartnummer , geldig_tot , klasse , saldo, reiziger_id) VALUES (?,?,?,?,?)";

            PreparedStatement prepstmt = conn.prepareStatement(query);

            prepstmt.setInt(1, ovchipkaart.getKaartnummer());
            prepstmt.setDate(2, ovchipkaart.getGeldig_tot());
            prepstmt.setInt(3, ovchipkaart.getKlasse());
            prepstmt.setDouble(4, ovchipkaart.getSaldo());
            prepstmt.setInt(5, ovchipkaart.getReiziger_id().getId());

            prepstmt.executeUpdate();
            prepstmt.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean delete(OVChipkaart ovchipkaart) {
        try {
            String query = "DELETE FROM ovchipkaart WHERE kaartnummer = ?";

            PreparedStatement prpstmt = conn.prepareStatement(query);

            prpstmt.setInt(1, ovchipkaart.getKaartnummer());

            prpstmt.executeUpdate();
            prpstmt.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean update(OVChipkaart ovchipkaart) {
        try {
            String query = "UPDATE ovchipkaart SET kaartnummer = ?, geldig_tot =?, klasse=?, saldo=?, reiziger_id = ? WHERE kaartnummer=?";

            PreparedStatement prepstmt = conn.prepareStatement(query);

            prepstmt.setDate(1, ovchipkaart.getGeldig_tot());
            prepstmt.setInt(2, ovchipkaart.getKlasse());
            prepstmt.setDouble(3, ovchipkaart.getSaldo());
            prepstmt.setInt(4, ovchipkaart.getKaartnummer());
            prepstmt.setInt(5, ovchipkaart.getReiziger_id().getId());


            prepstmt.executeUpdate();
            prepstmt.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        }
        return false;
    }


    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException {

        try {
            PreparedStatement prepstmt = conn.prepareStatement("SELECT * FROM ovchipkaart WHERE kaartnummer=?");
            prepstmt.setInt(1, reiziger.getId());

            ArrayList<OVChipkaart> ovChipkaart = new ArrayList<OVChipkaart>();

            ResultSet rsltst = prepstmt.executeQuery();
            while (rsltst.next()) {
                int kaartnummer = rsltst.getInt("kaart_nummer");
                Date geldig_tot = rsltst.getDate("geldig_tot");
                int klasse = rsltst.getInt("klasse");
                Double saldo = rsltst.getDouble("saldo");
                int Reiziger = rsltst.getInt("reiziger_id");

                return (List<OVChipkaart>) new OVChipkaart(kaartnummer, geldig_tot, klasse, saldo, rdao.findById(Reiziger));
            }
            rsltst.close();
            prepstmt.close();

        } finally {

        }

        return null;
    }

    @Override
    public OVChipkaart findByKaartnummer(int kaartnummer) throws SQLException {

        try {
            PreparedStatement prepstmt = conn.prepareStatement("SELECT * FROM ovchipkaart WHERE kaartnummer=?");
            prepstmt.setInt(1, kaartnummer);

            ResultSet rsltst = prepstmt.executeQuery();
            while (rsltst.next()) {
                Date geldig_tot = rsltst.getDate("geldig_tot");
                int klasse = rsltst.getInt("klasse");
                Double saldo = rsltst.getDouble("saldo");
                int Reiziger = rsltst.getInt("reiziger_id");

                System.out.println(kaartnummer);
                System.out.println(geldig_tot);
                System.out.println(klasse);
                System.out.println(saldo);
                System.out.println(Reiziger);

                return new OVChipkaart(kaartnummer, geldig_tot, klasse, saldo, rdao.findById(Reiziger));
            }
            rsltst.close();
            prepstmt.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OVChipkaart> findAll() throws SQLException {

        Statement stmt = conn.createStatement();
        ResultSet rsltst = stmt.executeQuery("SELECT* FROM ovchipkaart");

        ArrayList<OVChipkaart> aantalovchipkaarten = new ArrayList<OVChipkaart>();

        while (rsltst.next()) {

            int kaartnummer = rsltst.getInt("kaart_nummer");
            Date geldig_tot = rsltst.getDate("geldig_tot");
            int klasse = rsltst.getInt("klasse");
            Double saldo = rsltst.getDouble("saldo");
            int Reiziger = rsltst.getInt("reiziger_id");


            OVChipkaart ovchipkaart = new OVChipkaart(kaartnummer, geldig_tot, klasse, saldo, rdao.findById(Reiziger));
            aantalovchipkaarten.add(ovchipkaart);
            rsltst.close();
            stmt.close();
        }

        return aantalovchipkaarten;
    }


}



