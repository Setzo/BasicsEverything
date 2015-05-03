package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import model.Offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

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

	@Autowired
	public void setDataSource(DataSource dataSource) {
		
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
}
