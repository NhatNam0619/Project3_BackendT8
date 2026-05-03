package com.devon.building.repository.impl;


import com.devon.building.builder.CustomerResquestBuilder;
import com.devon.building.entity.Customer;
import com.devon.building.repository.custom.CustomerRepositoryCustom;
import com.devon.building.utils.SecurityUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    private void JoinSQL(CustomerResquestBuilder customerResquestBuilder, StringBuilder join) {

        if (customerResquestBuilder.getStaffid() != null) {
            join.append(" LEFT join c.users user ");
        }
        join.append(" where 1=1 ");
    }

    private void buildSpecial(CustomerResquestBuilder customerResquestBuilder, StringBuilder special) {

        if (customerResquestBuilder.getStaffid() != null) {
            special.append(" and user.id = " + customerResquestBuilder.getStaffid());
        }
        if (customerResquestBuilder.getFullName() != null && !customerResquestBuilder.getFullName().isEmpty()) {
            special.append(" AND c.fullName LIKE '%" + customerResquestBuilder.getFullName() + "%'");
        }
        if (customerResquestBuilder.getPhone() != null) {
            special.append(" And c.phone = " + customerResquestBuilder.getPhone());
        }
        if (customerResquestBuilder.getEmail() != null && !customerResquestBuilder.getEmail().isEmpty()) {
            special.append(" AND c.email LIKE '%" + customerResquestBuilder.getEmail() + "%'");
        }
        if (customerResquestBuilder.getStatus() != null && !customerResquestBuilder.getStatus().isEmpty()) {
            special.append(" AND c.status LIKE '%" + customerResquestBuilder.getStatus() + "%'");
        }
        if (!SecurityUtil.hasRole("MANAGER")) {
        special.append(" And c.isActive = true ");}
        special.append(" ORDER BY c.fullName ASC ");
    }
    @Override
    public List<Customer> findAll(CustomerResquestBuilder customerResquestBuilder) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT DISTINCT c from Customer c ");
        JoinSQL(customerResquestBuilder, jpql);
        buildSpecial(customerResquestBuilder, jpql);
        Query query = entityManager.createQuery(jpql.toString(), Customer.class);
        return query.getResultList();
    }
}
