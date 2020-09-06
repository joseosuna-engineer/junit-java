package com.prottonne.testing.jpa;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class JpaClient {

    @PersistenceContext
    private EntityManager entityManager;

    public JpaClient() {
        super();
    }

    private Integer getGuid() {
        Query query = entityManager.createNativeQuery("query");
        BigInteger sequence = (BigInteger) query.getSingleResult();
        return sequence.intValue();
    }

    public RootEntity create() {

        try {

            Integer guid = getGuid();
            LocalDateTime now = LocalDateTime.now();

            RootEntity rootEntity = new RootEntity(guid, now, now);

            entityManager.persist(rootEntity);
            entityManager.flush();

            return rootEntity;

        } finally {

            entityManager.close();
        }

    }

    public RootEntity createWithChild() {

        try {

            Integer guid = getGuid();
            LocalDateTime now = LocalDateTime.now();

            RootEntity rootEntity = new RootEntity(guid, now, now);

            OneEntity oneEntity = new OneEntity(guid);
            oneEntity.setBooleanData(Boolean.TRUE);

            rootEntity.setOneEntity(oneEntity);

            entityManager.persist(rootEntity);
            entityManager.flush();

            return rootEntity;

        } finally {

            entityManager.close();
        }

    }

    public Boolean deleteById(Integer guid) {
        Boolean response = Boolean.FALSE;
        try {

            RootEntity rootEntity = entityManager.find(RootEntity.class, guid);

            if (null != rootEntity) {
                entityManager.remove(rootEntity);
                response = Boolean.TRUE;
            }

        } finally {
            entityManager.flush();
            entityManager.close();
        }

        return response;

    }

    public Boolean deleteAll(String someData) {
        Boolean response = Boolean.FALSE;
        try {

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<ManyEntity> criteriaQuery = criteriaBuilder.createQuery(ManyEntity.class);
            Root<ManyEntity> root = criteriaQuery.from(ManyEntity.class);

            criteriaQuery.
                    select(root).
                    where(
                            criteriaBuilder.
                                    equal(
                                            root.get("someData"), someData
                                    )
                    );

            TypedQuery<ManyEntity> typedQuery = entityManager.createQuery(criteriaQuery);

            List<ManyEntity> manyEntityList = typedQuery.getResultList();

            if (null != manyEntityList) {
                response = Boolean.TRUE;
                manyEntityList.forEach(manyEntity -> {
                    entityManager.remove(manyEntity);
                });
            }

        } finally {
            entityManager.flush();
            entityManager.close();
        }
        return response;
    }

}
