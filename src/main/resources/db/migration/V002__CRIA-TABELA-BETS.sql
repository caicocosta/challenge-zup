create table bets (  
	id serial not null,  
	player_id int not null,  
	bet VARCHAR ( 20 ) not null,
	    	
	primary key (id) 
);	


alter table bets add constraint fk_bets_player foreign key (player_id) references player (id);