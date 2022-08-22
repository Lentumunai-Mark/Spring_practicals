package com.Losh.InsertQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    //----------------------------retrieving data from database-------------------------------------
    public List<Player> getAllPlayers(){
        String SQL = "SELECT * FROM PLAYER";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<Player>(Player.class));
    }
    public Player getPlayerById(int id){
        String sql = "SELECT * FROM PLAYER WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Player>(Player.class), new Object[] {id});
    }
    //------------------------------------Insert Queries------------------------------------------
    public int insertPlayer(Player player){
        String sql = "INSERT INTO PLAYER (ID, Name, Nationality, Birth_date, Titles)" + "Values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[] {player.getId(), player.getName(), player.getNationality(), new Timestamp(player.getBirthDate().getTime()),  player.getTitles()});
    }
    //-----------------------------------------Update Queries------------------------------------------
    public int updatePlayer(Player player){
        String sql = "UPDATE PLAYER SET Name = ?, Nationality = ?, Birth_date = ?, Titles = ?" + "WHERE ID = ?";
        return jdbcTemplate.update(sql, player.getName(), player.getNationality(), new Timestamp(player.getBirthDate().getTime()), player.getTitles(), player.getId());
    }
    //-----------------------------------------Delete Queries----------------------------------------------
    public int deletePlayerByID(int Id){
        String sql = "DELETE FROM PLAYER WHERE ID = ?";
        return jdbcTemplate.update(sql, new Object[] {Id});
    
    }
    //-----------------------------------------DDL QUERIES (CREATING TABLES)------------------------------------------------------
    public void createTable(){
        String sql = "CREATE TABLE TOURNAMENT (ID Integer, NAME VARCHAR(50), LOCATION VARCHAR(50), PRIMARY KEY(ID))";
        jdbcTemplate.execute(sql);
        System.out.println("Nakulombotov Lempiris");
    }
    //----------------------------------------Row mapper (defining our own)  
    private static final class PlayerMapper implements RowMapper<Player>{
        @Override
        public Player mapRow(ResultSet resultSet, int row) throws SQLException{
            Player player = new Player();
            player.setId(resultSet.getInt("id"));
            player.setName(resultSet.getString("name"));
            player.setNationality(resultSet.getString("nationality"));
            player.setBirthDate(resultSet.getDate("birth_date"));
            player.setTitles(resultSet.getInt("titles"));
            return player;
        }
    }
    
    public List<Player> getPlayerByNationality(String nationality){
        String sql = "SELECT * FROM PLAYER WHERE Nationality = ?";
        return jdbcTemplate.query(sql, new PlayerMapper(), new Object[] {nationality});
    }
    
}
