create table "limits" (id bigserial primary key, "limit" numeric, user_id  bigint);
insert into "limits" ("limit", user_id)values(10000, 1),(5000, 2),(3000,3),(0,4),(1,5);

