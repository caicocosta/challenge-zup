create table bets (  
	id bigint not null auto_increment,  
	player_id bigint not null,  
	bet varchar(20) not null,
	    	
	primary key (id) 
);	


alter table bets add constraint fk_bets_player foreign key (player_id) references player (id);