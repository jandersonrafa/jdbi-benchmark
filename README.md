
CREATE TABLE jdbi.orders (
	id bigserial NOT NULL,
	amount numeric NOT NULL,
	active boolean NOT NULL,
	createddate date NOT NULL,
	createdtime timestamp NOT NULL,
	numberorder integer NOT NULL,
	one varchar(36)  NULL,
	two varchar(36)  NULL,
	tree varchar(36)  NULL,
	four varchar(36)  NULL,
	five varchar(36)  NULL,
	six varchar(36)  NULL,
	seven varchar(36)  NULL,
	eight varchar(36)  NULL,
	nine varchar(36)  NULL,
	ten varchar(36)  NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (id)
);

GRANT ALL ON jdbi.orders TO jdbi;

