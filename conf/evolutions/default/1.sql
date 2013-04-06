# --- First database schema
 
# --- !Ups
 
create table req (
    id    bigint(20) NOT NULL AUTO_INCREMENT,
    data  varchar(1280),
    PRIMARY KEY (id)
);
 
# --- !Downs
 
drop table req;