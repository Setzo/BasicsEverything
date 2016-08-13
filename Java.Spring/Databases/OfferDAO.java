package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import model.Offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("offerDAO")
public class OfferDAO {

	private NamedParameterJdbcTemplate jdbc;

	public List<Offer> getOffers() {
		
		return jdbc.query("select * from offers", new RowMapper<Offer>() {

			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Offer offer = new Offer();
				
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setEmail(rs.getString("email"));
				offer.setText(rs.getString("text"));
				
				return offer;
			}
		});
	}

	public Offer getOffer(int id) {
	
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		// RowMapper<Offer> wywali typ Offer dla queryForObject(), nie jest potrzebny cast
		return jdbc.queryForObject("select * from offers where id = :id", params, new RowMapper<Offer>() {
	
			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Offer offer = new Offer();
				
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setEmail(rs.getString("email"));
				offer.setText(rs.getString("text"));
				
				return offer;
			}
		});
	}
	
	public List<Offer> getOffersWithParams(MapSqlParameterSource params, String sql) {
		
		return jdbc.query(sql, params, new RowMapper<Offer>() {
	
			@Override
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Offer offer = new Offer();
				
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setEmail(rs.getString("email"));
				offer.setText(rs.getString("text"));
				
				return offer;
			}
		});
	}
	
	public boolean delete(int id) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("id", id);
		
		try {
			
			return jdbc.update("delete from offers where id = :id", params) == 1;
			
		} catch (DataAccessException e) {
			
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
			
			return false;
		}
	}
	
	@Transactional
	public int[] delete(int[] ids) {
		
		MapSqlParameterSource[] param = new MapSqlParameterSource[ids.length];

		for (int i = 0; i < ids.length; i++) {

			param[i] = new MapSqlParameterSource("id", ids[i]);
		}
		
		return jdbc.batchUpdate("delete from offers where id = :id", param);
	}
	
	public boolean create(Offer offer) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		
		return jdbc.update("insert into offers (name, email, text) values (:name, :email, :text)", params) == 1;
	}
	
	@Transactional
	public int[] create (List<Offer> offerList) {
		
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offerList.toArray());
		
		return jdbc.batchUpdate("insert into offers (name, email, text) values (:name, :email, :text)", params);
	}
	
	public boolean update(Offer offer) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		
		return jdbc.update("update offers set name = :name, email = :email, text = :text where id = :id", params) == 1;
	}
	
	@Transactional
	public int[] update (List<Offer> offerList) {
		
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offerList.toArray());
		
		return jdbc.batchUpdate("update offers set name = :name, email = :email, text = :text where id = :id", params);
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
}
