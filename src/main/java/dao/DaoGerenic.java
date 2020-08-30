package dao;


import connection.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Classe generica responsável pelas operações no banco, fazendo operações diretamente com as entidades
 * - Robson Murilo: 29/08/2020 -
 */
public class DaoGerenic<E> {

    private EntityManager entityManager = HibernateUtil.getEntityManger();

    public void saveGeneric(E entidade) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entidade);
        transaction.commit();
    }


    public E searchGenericId(long id, Class<E> entidade) {

        entityManager.clear();
        // E e = (E) entityManager.createQuery("from"+entidade.getSimpleName()+"where id="+id).getSingleResult();
        E e = (E) entityManager.find(entidade, id);
        return e;
    }


    public void deleteID(E entidade) throws Exception {

        Object id = HibernateUtil.getPrimaryKey(entidade);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        String sql = "delete from" + entidade.getClass().getSimpleName().toLowerCase() +
                " where id=" + id;
        System.out.println(sql);
        entityManager.createNativeQuery("delete from " + entidade.getClass().getSimpleName().toLowerCase() +
                " where id=" + id).executeUpdate();
        transaction.commit();

    }

    public List<E> listGeneric(Class<E> entidade) {

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<E> lista = entityManager.createQuery("from " + entidade.getName()).getResultList();
        transaction.commit();

        return lista;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }


}
