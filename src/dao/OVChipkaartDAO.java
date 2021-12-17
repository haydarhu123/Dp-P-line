package dao;

import domein.OVChipkaart;
import domein.Reiziger;

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
