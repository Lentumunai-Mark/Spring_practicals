package com.Losh.InsertQuery;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InsertQueryApplication implements CommandLineRunner{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	PlayerDao dao;
	public static void main(String[] args) {
		SpringApplication.run(InsertQueryApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		//-----------------------------Section 1(Retrieving data from the DB)-------------------------
		//logger.info("All players data: {}", dao.getAllPlayers());
		//logger.info("Players with Id 3 {}", dao.getPlayerById(3));
		
		
		//--------------------------Insert Queries(Adding data to the DB)--------------------------------
		logger.info("Inserting player 4 {}", dao.insertPlayer(new Player(4, "Thiem", "Austria", new Date(System.currentTimeMillis()), 17)));
		logger.info("All players data: {}", dao.getAllPlayers());
		//---------------------------Update Queries ------------------------------------------------------------
		logger.info("updating player 4 {}", dao.updatePlayer(new Player(4, "Thiem", "Austria", Date.valueOf("1993-09-03"), 17)));
		logger.info("logging player 4 {}", dao.getPlayerById(4));
		//---------------------------Delete Queries---------------------------------------------------------------
		logger.info("Deleting player 4 {}", dao.deletePlayerByID(4));
		logger.info("Retrieving all palyers {}", dao.getAllPlayers());
		//--------------------------------------Creating Tables----------------------------------------------------
		dao.createTable();
		//--------------------------------------Custom RowMapper -------------------------------------------------------------
		logger.info("French players : {}", dao.getPlayerByNationality("France"));
	}

}
