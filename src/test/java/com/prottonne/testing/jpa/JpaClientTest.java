package com.prottonne.testing.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import com.prottonne.testing.stub.Stubs;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import org.mockito.ArgumentMatchers;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JpaClientTest {

    @InjectMocks
    private JpaClient jpaClient;

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<ManyEntity> criteriaQueryManyEntity;

    @Mock
    private Root<ManyEntity> rootManyEntity;

    @Mock
    private TypedQuery<ManyEntity> typedQueryManyEntity;

    @Mock
    private Path<Object> pathObject;

    @Test
    public void testCreate() {

        when(query.getSingleResult()).thenReturn(Stubs.GENERATE_GUID());

        when(entityManager.createNativeQuery(anyString())).thenReturn(query);

        RootEntity rootEntity = jpaClient.create();

        assertThat(rootEntity.getGuid(),
                is(
                        Stubs.GUID.intValue()
                ));
    }

    @Test
    public void testCreateWithChild() {

        when(query.getSingleResult()).thenReturn(Stubs.GENERATE_GUID());

        when(entityManager.createNativeQuery(anyString())).thenReturn(query);

        RootEntity rootEntity = jpaClient.createWithChild();

        assertThat(rootEntity.getGuid(),
                is(
                        Stubs.GUID.intValue()
                ));

        OneEntity oneEntity = rootEntity.getOneEntity();

        assertThat(oneEntity.getGuid(),
                is(
                        Stubs.GUID.intValue()
                ));

        assertThat(oneEntity.getBooleanData(),
                is(
                        Boolean.TRUE
                ));

    }

    @Test
    public void testDeleteById() {

        when(entityManager.find(eq(RootEntity.class), any(Integer.class))).
                thenReturn(Stubs.ROOT_ENTITY());

        Boolean response = jpaClient.deleteById(Stubs.GUID.intValue());

        assertThat(response,
                is(
                        Boolean.TRUE
                ));
    }

    @Test
    public void testDeleteAll() {

        when(entityManager.getCriteriaBuilder()).
                thenReturn(criteriaBuilder);

        when(criteriaBuilder.createQuery(ArgumentMatchers.<Class<ManyEntity>>any())).
                thenReturn(criteriaQueryManyEntity);

        when(criteriaQueryManyEntity.from(ArgumentMatchers.<Class<ManyEntity>>any())).
                thenReturn(rootManyEntity);

        when(criteriaQueryManyEntity.select(rootManyEntity)).
                thenReturn(criteriaQueryManyEntity);

        when(rootManyEntity.get(anyString())).thenReturn(pathObject);

        when(entityManager.createQuery(criteriaQueryManyEntity)).
                thenReturn(typedQueryManyEntity);

        when(typedQueryManyEntity.getResultList()).
                thenReturn(Stubs.MANY_ENTITY_LIST());

        Boolean response = jpaClient.deleteAll("some data");

        assertThat(response,
                is(
                        Boolean.TRUE
                ));
    }

}
