package dao;

import entity.SuperEntity;

import java.util.List;

public interface SuperDAO<Entity extends SuperEntity, ID> {
    boolean add(Entity entity);

    boolean update(Entity entity);

    boolean delete(ID id);

    List<Entity> find();

}
