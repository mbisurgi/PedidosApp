package com.designfreed.apppedidos.repositories.generic;

import com.activeandroid.Model;
import com.activeandroid.query.Select;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class GenericRepository<E extends Model, PK extends Long> {
    public Class<E> getType() {
        Class clazz = this.getClass();
        Type superClass = clazz.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) superClass;
        Type[] generics = parameterizedType.getActualTypeArguments();
        return (Class<E>) generics[0];
    }

    public E getById(PK id) {
        return E.load(getType(), id);
    }

    public abstract E getByIdCrm(PK id);

    public List<E> getAll() {
        return new Select().all().from(getType()).execute();
    }

    public Long saveOrUpdate(E entity) {
        return entity.save();
    }

    public void delete(E entity) {
        entity.delete();
    }
}
