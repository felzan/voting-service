create table if not exists topic
(
	id int auto_increment
		primary key,
	description text null,
	created_at datetime not null,
	session_end datetime null
);

create table if not exists  vote
(
	vote varchar(3) not null,
	topic_id int not null,
	user_id int not null,
	primary key (topic_id, user_id),
	constraint vote_topic_id_fk
		foreign key (topic_id) references topic (id)
);
