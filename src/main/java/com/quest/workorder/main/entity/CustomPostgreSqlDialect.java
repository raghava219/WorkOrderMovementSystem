package com.quest.workorder.main.entity;

import java.sql.Types;
import org.hibernate.dialect.PostgreSQL94Dialect;

public class CustomPostgreSqlDialect extends PostgreSQL94Dialect {

    public CustomPostgreSqlDialect() {
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
    }
}
