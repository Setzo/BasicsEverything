package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import model.Offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component("offerDAO")
public class OfferDAO {

	private JdbcTemplate jdbc;

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

	@Autowired
	public void setDataSource(DataSource dataSource) {
		
		this.jdbc = new JdbcTemplate(dataSource);
	}
}
