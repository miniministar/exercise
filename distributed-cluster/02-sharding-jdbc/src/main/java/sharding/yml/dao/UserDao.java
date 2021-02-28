package sharding.yml.dao;

import sharding.yml.entity.User;

public interface UserDao  {

    void addOne(User user);

    User getOneById(long id);
}
