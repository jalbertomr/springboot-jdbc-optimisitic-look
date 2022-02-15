package com.bext.optimistic.dao;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	private static final String UPDATE_SQL = "update ACCOUNT set NAME = :name, VERSION = (:version + 1) where VERSION = :version and ID = :id";
	private static final String SELECT_SQL = "select ID,NAME, VERSION from ACCOUNT where ID = :id";
	
	public int update(Account account) {
		return jdbc.update(UPDATE_SQL, new BeanPropertySqlParameterSource(account));
	}
	
	public Account selectById(final int id) {
		return jdbc.queryForObject(SELECT_SQL, Collections.singletonMap("id", id), 
				(rs, rowNum) -> {
					Account account = new Account();
					account.setId(rs.getInt("ID"));
					account.setName(rs.getString("NAME"));
					account.setVersion(rs.getInt("VERSION"));
					return account;
				});
	}
}
