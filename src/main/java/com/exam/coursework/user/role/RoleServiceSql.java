package com.exam.coursework.user.role;


import com.exam.coursework.shared.dao.FactoryDAO;
import com.exam.coursework.shared.dao.FactoryDAOSql;
import com.exam.coursework.shared.service.AbstractServiceSql;
import com.exam.coursework.shared.service.Service;

public class RoleServiceSql extends AbstractServiceSql<Integer, Role> implements Service<Integer, Role> {
    FactoryDAO factoryDAO;
    public RoleServiceSql() {
        super(RoleDAOSql.class);
        factoryDAO = new FactoryDAOSql();
    }
}
