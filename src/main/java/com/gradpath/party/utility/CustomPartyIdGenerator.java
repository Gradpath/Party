package com.gradpath.party.utility;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomPartyIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String prefix = "PA";
        String generatedId = null;

        try (Connection connection = session.getJdbcConnectionAccess().obtainConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT MAX(CAST(SUBSTRING(party_id, 3) AS UNSIGNED)) AS maxId FROM Party");
            if (resultSet.next()) {
                int maxId = resultSet.getInt("maxId");
                int newId = maxId + 1;
                generatedId = prefix + String.format("%06d", newId);
            }
        } catch (SQLException e) {
            throw new HibernateException("Failed to generate party ID", e);
        }

        return generatedId;
    }

}

