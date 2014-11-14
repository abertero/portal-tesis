package portal.config;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class JPA {
    private static final Logger logger = LoggerFactory.getLogger(JPA.class);

    private static EntityManager em;
    private static EntityManagerFactory emf;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
        logger.info("JPA inicializado");
    }

    @Autowired
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public static EntityManager em() {
        return em;
    }

    public static EntityManagerFactory emf() {
        return emf;
    }

    //<editor-fold desc="Transaccion">

    public static interface Tx<T> {
        public T doTx();
    }

    public static abstract class TxVoid implements Tx<Void> {
        @Override
        public Void doTx() {
            doTx_();
            return null;
        }

        public abstract void doTx_();
    }

    public static <T> T tx(Tx<T> tx) {
        JPA proxy = (JPA) Spring.bean("_jpa_scg");
        return proxy.doTx(tx);
    }

    /**
     * Transaction con Propagation.REQUIRES_NEW
     *
     * @param tx
     * @param <T>
     * @return
     */
    public static <T> T txNew(Tx<T> tx) {
        JPA proxy = (JPA) Spring.bean("_jpa_scg");
        return proxy.doNewTx(tx);
    }

    @Transactional
    public <T> T doTx(Tx<T> tx) {
        return tx.doTx();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public <T> T doNewTx(Tx<T> tx) {
        return tx.doTx();
    }

    //</editor-fold>

    public static <T> List<T> query(String oql, Object... params) {
        Query query = em.createQuery(oql);
        setParamsArray(query, params);
        return query.getResultList();
    }

    public static <T> List<T> queryNamed(String oql, Object... pairParams) {
        Query query = em.createQuery(oql);
        setPairParams(query, pairParams);
        return query.getResultList();
    }

    public static <T> T querySingle(String oql, Object... params) {
        Query query = em.createQuery(oql);
        setParamsArray(query, params);
        return (T) query.getSingleResult();
    }

    public static <T> T querySingleNamed(String oql, Object... pairParams) {
        Query query = em.createQuery(oql);
        setPairParams(query, pairParams);
        return (T) query.getSingleResult();
    }

    public static <T> T queryFirst(String oql, Object... params) {
        List<T> result = JPA.query(oql, params);
        return result.isEmpty() ? null : result.get(0);
    }

    public static <T> T queryFirstNamed(String oql, Object... pairParams) {
        List<T> result = JPA.queryNamed(oql, pairParams);
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * Busca entidad por campo altkey
     *
     * @param clazz     class
     * @param altKey    altKey
     * @param requerido indica si debe retornar entidad
     * @param <T>       T
     * @return T o null si requerido = false
     */
    public static <T> T findByAltKey(Class<T> clazz, String altKeyProp, String altKey, boolean requerido) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        cq.select(root)
                .where(cb.equal(root.get(altKeyProp), altKey));

        List<T> result = em.createQuery(cq).getResultList();
        return requerido ? DataAccessUtils.requiredUniqueResult(result) : DataAccessUtils.uniqueResult(result);
    }

    /**
     * Busca entidad por campo altkey
     *
     * @param clazz  class
     * @param altKey altKey
     * @param <T>    T
     * @return T o Exception si no existe
     */
    public static <T> T findByAltKey(Class<T> clazz, String altKeyProp, String altKey) {
        return findByAltKey(clazz, altKeyProp, altKey, true);
    }

    public static <T> T findByAltKey(Class<T> clazz, String altKey, boolean requerido) {
        return findByAltKey(clazz, "altKey", altKey, requerido);
    }

    public static <T> T findByAltKey(Class<T> clazz, String altKey) {
        return findByAltKey(clazz, "altKey", altKey);
    }

    public static <T> List<T> findAll(Class<T> clazz, String... orderProps) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        cq.select(root);
        if (orderProps != null) {
            List<Order> orders = new ArrayList<Order>(orderProps.length);
            for (String prop : orderProps) {
                if (StringUtils.isNotEmpty(prop)) {
                    orders.add(cb.asc(root.get(prop)));
                }
            }
            cq.orderBy(orders);
        }
        return em.createQuery(cq).getResultList();
    }

    public static int updateNamed(String oql, Object... pairParams) {
        Query query = em.createQuery(oql);
        setPairParams(query, pairParams);
        return query.executeUpdate();
    }

    private static void setPairParams(Query query, Object[] pairParams) {
        if (pairParams == null) {
            return;
        }
        for (int i = 0; i < pairParams.length; i += 2) {
            String name = (String) pairParams[i];
            Object value = pairParams[i + 1];

            setParameter(query, name, value);
        }
    }

    private static void setParameter(Query query, String name, Object value) {
        if (value instanceof Timestamp) {
            query.setParameter(name, (Date) value, TemporalType.TIMESTAMP);
        } else if (value instanceof Date) {
            query.setParameter(name, (Date) value, TemporalType.DATE);
        } else {
            query.setParameter(name, value);
        }
    }

    private static void setParameter(Query query, int index, Object value) {
        if (value instanceof Timestamp) {
            query.setParameter(index, (Date) value, TemporalType.TIMESTAMP);
        } else if (value instanceof Date) {
            query.setParameter(index, (Date) value, TemporalType.DATE);
        } else {
            query.setParameter(index, value);
        }
    }

    private static void setParamsArray(Query query, Object[] params) {
        if (params == null) {
            return;
        }

        int i = 1;
        for (Object value : params) {
            setParameter(query, i++, value);
        }
    }

    public static void setParameters(Query query, Map<String, Object> params) {
        if (params == null) {
            return;
        }

        for (Map.Entry<String, Object> e : params.entrySet()) {
            String name = e.getKey();
            Object value = e.getValue();
            setParameter(query, name, value);
        }
    }

}
