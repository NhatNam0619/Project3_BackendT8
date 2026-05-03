package com.devon.building.repository.impl;

import com.devon.building.builder.BuildingResquestBuilder;
import com.devon.building.entity.Building;
import com.devon.building.pagination.PaginationResult;
import com.devon.building.repository.custom.BuildingRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.persistence.Query;

@Repository
@Transactional
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    private void JoinSQL(BuildingResquestBuilder buildingRequestBuilder, StringBuilder join) {

        if (buildingRequestBuilder.getStaffid() != null) {
            join.append(" LEFT join b.users user ");
        }
        if (buildingRequestBuilder.getArearentmin() != null || buildingRequestBuilder.getArearentmax() != null) {
            join.append(" LEFT join b.rentAreas rentarea ");
        }
        join.append(" where 1=1 ");
    }

    private void buildWhere(BuildingResquestBuilder buildingRequestBuilder, StringBuilder where) {

        Field[] fields = BuildingResquestBuilder.class.getDeclaredFields();
        for (Field it : fields) {
            it.setAccessible(true);
            String fieldname = it.getName();
            if (!fieldname.equals("staffid") && !fieldname.equals("type") && !fieldname.startsWith("area")
                    && !fieldname.startsWith("rent")){

                try {
                    Object value = it.get(buildingRequestBuilder);
                    if (value != null) {
                        if (it.getType().equals(Long.class)) {
                            where.append(" AND b." + fieldname + " = " + value);
                        } else {
                            where.append(" AND b." + fieldname + " LIKE '%" + value + "%'");
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void buildSpecial(BuildingResquestBuilder buildingRequestBuilder, StringBuilder special) {

        if (buildingRequestBuilder.getStaffid() != null) {
            special.append(" and user.id = " + buildingRequestBuilder.getStaffid());
        }
        if (buildingRequestBuilder.getType() != null && !buildingRequestBuilder.getType().isEmpty()) {
            special.append(" AND ("
                    + buildingRequestBuilder.getType().stream()
                    .map(i -> "b.type LIKE '%" + i + "%'")
                    .collect(Collectors.joining(" OR "))
                    + ")");
        }

        if (buildingRequestBuilder.getRentpricemin() != null) {
            special.append(" AND b.rentprice >= " + buildingRequestBuilder.getRentpricemin());
        }
        if (buildingRequestBuilder.getRentpricemax() != null) {
            special.append(" AND b.rentprice <= " + buildingRequestBuilder.getRentpricemax());
        }
        if (buildingRequestBuilder.getArearentmin() != null) {
            special.append(" And rentarea.value >= " + buildingRequestBuilder.getArearentmin());
        }
        if (buildingRequestBuilder.getArearentmax() != null) {
            special.append(" AND rentarea.value <= " + buildingRequestBuilder.getArearentmax());
        }
        special.append(" ORDER BY b.name ASC ");
    }

    // @Override
    public PaginationResult<Building> findAll(BuildingResquestBuilder buildingResquestBuillder,int page, int maxResult, int maxNavigationPage) {

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT DISTINCT b from Building b ");
        JoinSQL(buildingResquestBuillder, jpql);
        buildWhere(buildingResquestBuillder, jpql);
        buildSpecial(buildingResquestBuillder, jpql);
        Query query = entityManager.createQuery(jpql.toString(), Building.class);
        PaginationResult<Building> result = new PaginationResult<>(query,query.getResultList().size(),page, maxResult, maxNavigationPage);
        return result;
    }

    public List findAll(BuildingResquestBuilder buildingResquestBuillder) {

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT DISTINCT b from Building b ");
        JoinSQL(buildingResquestBuillder, jpql);
        buildWhere(buildingResquestBuillder, jpql);
        buildSpecial(buildingResquestBuillder, jpql);
        Query query = entityManager.createQuery(jpql.toString(), Building.class);
        return query.getResultList();
    }
}
