package model;

import java.awt.Color;
import java.util.Random;

public class Game {

	private Player player;
	private Player oponent;
	
	public Game(String nombre, int size) {
		this.player = new Player(nombre, size);
		this.oponent = new Player("Maquina", size);
	}
	

	// dos jugadores cada uno con sus barcos
	//ubicar los barcos aleatoriamente para el oponente
	//validar las posiciones 
	//si se encuentra una parte del barco a partir de la toma de celdas aleatorias, se evaluan 

	public void randomShips(int sizeShip, Player p) {
		System.out.println("su"+sizeShip);
		boolean shipLocated = false;
		int x,y, dir;
		int sizeBoard = this.oponent.getBoard().getPlayerFields().length;
		while(!shipLocated){
			x = this.getRandomNumber(0, sizeBoard-1 );
			y = this.getRandomNumber(0, sizeBoard-1);
			dir = this.getRandomNumber(0, 3);
			String direction = "";
			if(dir==0)
				direction = "top";
			else if(dir==1)
				direction = "bottom";
			else if(dir==2)
				direction = "left";
			else 
				direction = "right";
			if(this.validatePosition(x, y, sizeShip, direction, sizeBoard,p )) {
				System.out.println(x+ y+ sizeShip+ direction+ sizeBoard);
				if(oponent.getBoard().getPlayerFields()[x][y].isFree()){
					this.putShip(x, y, sizeShip, direction, this.oponent);
					shipLocated = true;
				}
			}
			
		 }
	}
	//validar que no salga de los limites, y no pase sobre otro barco
	public boolean validatePosition(int row, int colum, int sizeShip, String direction, int sizeBoard, Player p) {
		boolean valid = true;
		if(direction.equals("top")) {
			if(row-sizeShip < 0)
				valid = false;
			for (int i = row-sizeShip; i < row && valid; i++) {
				if(!p.getBoard().getPlayerFields()[i][colum].isFree())
					valid = false;
			}
		}else if(direction.equals("bottom")) {
			if(row+sizeShip > sizeBoard)
				valid = false;
			for (int i = row; i < row+sizeShip && valid; i++) {
				if(!p.getBoard().getPlayerFields()[i][colum].isFree())
					valid = false;
			}
		}else if(direction.equals("left")) {
			if(colum-sizeShip < 0)
				valid = false;
			for (int i = colum-sizeShip; i < colum && valid; i++) {
				if(!p.getBoard().getPlayerFields()[row][i].isFree())
					valid = false;
			}
		}else {//right
			if(colum+sizeShip > sizeBoard)
				valid = false;
			for (int i = colum; i <= colum+sizeShip && valid; i++) {
				if(!p.getBoard().getPlayerFields()[row][i].isFree())
					valid = false;
			}
		}
		return valid;
	}
	public void putShip(int row, int colum, int sizeShip, String direction, Player p) {
		
		sizeShip = sizeShip - 1;
		p.getBoard().getPlayerFields()[row][colum].setFree(false);;
		if(direction.equals("top")) {
			for (int i = row-sizeShip; i < row; i++) {
				p.getBoard().getPlayerFields()[i][colum].setFree(false);
			}
		}else if(direction.equals("bottom")) {
			for (int i = row; i <= row+sizeShip; i++) {
				p.getBoard().getPlayerFields()[i][colum].setFree(false);
			}
		}else if(direction.equals("left")) {
			for (int i = colum-sizeShip; i < colum; i++) {
				p.getBoard().getPlayerFields()[row][i].setFree(false);
			}
		}else {//right
			for (int i = colum; i <= colum+sizeShip; i++) {
				p.getBoard().getPlayerFields()[row][i].setFree(false);
			}
		}
	}

	private int getRandomNumber(int min, int max) {
		 Random numberRandom = new Random();
		 int random  = numberRandom.nextInt((max-min)+1)+(min);
		 return random; 
}
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getOponent() {
		return oponent;
	}

	public void setOponent(Player oponent) {
		this.oponent = oponent;
	}


	

	
}
