package p4;

import p2.Reiziger;
import p3.Adres;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDAO {

    public boolean save(OVChipkaart ovchipkaart) throws SQLException;

    public boolean delete(OVChipkaart ovchipkaart) throws SQLException;

    public boolean update(OVChipkaart ovchipkaart) throws SQLException;

    OVChipkaart findByReiziger(Reiziger reiziger) throws SQLException;

    List<OVChipkaart> findAll() throws SQLException;

    OVChipkaart findByKaartnummer(int kaartnummer) throws SQLException;
}
