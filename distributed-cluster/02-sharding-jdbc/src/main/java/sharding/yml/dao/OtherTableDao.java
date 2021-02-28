package sharding.yml.dao;

import sharding.yml.entity.OtherTable;

import java.util.List;

public interface OtherTableDao {

    long addOne(OtherTable table);

    List<OtherTable> getAll();

}
