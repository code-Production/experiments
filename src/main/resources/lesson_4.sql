drop schema if exists cinema cascade;
create schema if not exists cinema;

set search_path = "cinema";

drop table if exists movies cascade;
create table if not exists movies (
	id bigserial primary key,
	title varchar(255) not null,
	duration smallint not null
);

drop table if exists halls cascade;
create table if not exists halls (
	id serial primary key,
	max_seats smallint not null
);

drop table if exists sessions cascade;
create table if not exists sessions (
	id bigserial primary key,
	movie_id bigint not null,
	start_time timestamp without time zone not null,
	ticket_price real not null,
	hall_id int not null,
	
	constraint fk_movies foreign key (movie_id) references movies(id),
	constraint fk_halls foreign key (hall_id) references halls(id)
);

drop table if exists tickets cascade;
create table if not exists tickets (
	id bigserial primary key,
	session_id bigint not null,
	seat smallint not null,
	
	constraint fk_sessions foreign key (session_id) references sessions(id)
);

insert into halls 
	(id, max_seats) 
values 
	(1, 30),
	(2, 50),
	(3, 100);


insert into movies 
	(id, title, duration) 
values 
	(1, 'terminator', 120),
	(2, 'harry potter', 120),
	(3, 'iron man', 120),
	(4, '1917', 120),
	(5, 'kung fury', 120);

	
insert into sessions 
	(movie_id, start_time, ticket_price, hall_id)
values
	(1, '01.01.2020 9:00:00', 150, 1),
	(4, '01.01.2020 12:30:00', 150, 2),
	(2, '01.01.2020 20:00:00', 150, 3),
	(3, '02.01.2020 5:00:00', 150, 1),
	(5, '01.01.2020 11:00:00', 150, 2),
	(2, '01.01.2020 16:30:00', 150, 3),
	(3, '01.01.2020 15:00:00', 150, 1),
	(1, '01.01.2020 19:00:00', 150, 2);


insert into tickets
	(session_id , seat)
values
	(1,1),
	(1,2),
	(1,3),
	(2,1),
	(2,2),
	(4,1),
	(6,1),
	(6,2),
	(6,3),
	(6,4),
	(6,5),
	(6,6),
	(8,1);



--statistics
with t as (
	select t1.mid, t1.mtitle, total_customer, total_sessions, customers_per_session, total_income, income_per_session from 
		(select m.id as mid, m.title as mtitle, sum(case when t.session_id is not null then 1 else 0 end) as total_customer
		from movies m
		left join sessions s 
		on m.id = s.movie_id
		left join tickets t 
		on s.id = t.session_id
		group by m.id) as t1
	left join
		(select t2.mid as mid, avg(session_customers) as customers_per_session from 
			(select s.id as sid, s.movie_id as mid, sum(case when t.id is null then 0 else 1 end) as session_customers
			from sessions s 
			left join tickets t 
			on t.session_id = s.id 
			group by s.id) as t2
		group by t2.mid) as t3
	on t1.mid = t3.mid
	left join 
		(select s.movie_id as mid, count(s.movie_id) as total_sessions
		from sessions s 
		group by s.movie_id) as t4
	on t1.mid = t4.mid
	left join 
		(select s.movie_id as mid, sum(case when t.id is null then 0 else s.ticket_price end) as total_income 
		from sessions s 
		left join tickets t 
		on s.id = t.session_id
		group by s.movie_id) as t5
	on t1.mid = t5.mid
	left join 
		(select t6.mid, avg(session_total) as income_per_session  from
			(select s.id, s.movie_id as mid, sum(case when t.id is null then 0 else s.ticket_price end) as session_total
			from sessions s 
			left join tickets t 
			on s.id = t.session_id 
			group by s.id) as t6
		group by t6.mid) as t7
	on t1.mid = t7.mid
	order by t5.total_income desc
)
select *
from t
union all
select 0, '*TOTAL*', 
sum(total_customer), 
sum(total_sessions), 
sum(total_customer) / sum(total_sessions), 
sum(total_income), 
sum(total_income) / sum(total_sessions)  
from t;


select 'Total income:' as _,
sum(case when interval'1 hour' * 9 <= cast(s.start_time as time) and cast(s.start_time as time) < interval '1 hour' * 12 then s.ticket_price else 0 end) as p9_12,
sum(case when interval'1 hour' * 12 <= cast(s.start_time as time) and cast(s.start_time as time) < interval '1 hour' * 15 then s.ticket_price else 0 end) as p12_15,
sum(case when interval'1 hour' * 15 <= cast(s.start_time as time) and cast(s.start_time as time) < interval '1 hour' * 18 then s.ticket_price else 0 end) as p15_18,
sum(case when interval'1 hour' * 18 <= cast(s.start_time as time) and cast(s.start_time as time) < interval '1 hour' * 21 then s.ticket_price else 0 end) as p18_21,
sum(case when interval'1 hour' * 21 <= cast(s.start_time as time) and cast(s.start_time as time) <= interval '1 hour' * 24 then s.ticket_price else 0 end) as p21_24
from sessions s  
left join tickets t 
on s.id = t.session_id
where t.id is not null
union all 
select 'Total customers:',
sum(case when interval'1 hour' * 9 <= cast(s.start_time as time) and cast(s.start_time as time) < interval '1 hour' * 12 then 1 else 0 end),
sum(case when interval'1 hour' * 12 <= cast(s.start_time as time) and cast(s.start_time as time) < interval '1 hour' * 15 then 1 else 0 end),
sum(case when interval'1 hour' * 15 <= cast(s.start_time as time) and cast(s.start_time as time) < interval '1 hour' * 18 then 1 else 0 end),
sum(case when interval'1 hour' * 18 <= cast(s.start_time as time) and cast(s.start_time as time) < interval '1 hour' * 21 then 1 else 0 end),
sum(case when interval'1 hour' * 21 <= cast(s.start_time as time) and cast(s.start_time as time) <= interval '1 hour' * 24 then 1 else 0 end)
from sessions s 
left join tickets t 
on s.id = t.session_id
where t.id is not null;

--mistakes in timetable
select 
m1.title as Movie_1, 
s1.start_time as start_time, 
s1.start_time + interval '1 minute' * m1.duration as end_time,
m2.title as Movie_2,
s2.start_time as start_time,
s2.start_time + interval '1 minute' * m2.duration as end_time,
s1.hall_id as Hall
from sessions as s1
left join sessions as s2
on s1.id <> s2.id
left join movies as m1
on m1.id = s1.movie_id
left join movies as m2
on m2.id = s2.movie_id
where s2.start_time >= s1.start_time 
and s2.start_time < s1.start_time + interval '1 minute' * m1.duration
and s1.hall_id = s2.hall_id
order by s1.start_time;


--idles between movies
with next_movies as (
	select s1.id as m1_sid, s1.movie_id as m1_id , min(s2.start_time) as m2_start_time, s1.hall_id as m1_hid
	from sessions as s1
	left join sessions as s2
	on s1.id <> s2.id
	left join movies as m1
	on m1.id = s1.movie_id
	where s2.start_time >= s1.start_time + interval '1 minute' * m1.duration
	and s1.hall_id = s2.hall_id 
	group by s1.id
)
select
s1.id as session_1,
m1.title as Movie_1,
s1.start_time as start_time,
m1.duration as duration,
m2.title as movie_2,
s2.start_time as start_time,
s1.hall_id as hall_id,
s2.start_time - s1.start_time - interval '1 minute' * m1.duration as idle_time
from sessions as s1
left join sessions as s2
on s1.id <> s2.id
left join movies as m1
on m1.id = s1.movie_id
left join movies as m2
on m2.id = s2.movie_id
left join next_movies
on s1.id = next_movies.m1_sid
where next_movies.m2_start_time = s2.start_time
and s2.start_time - s1.start_time - interval '1 minute' * m1.duration >= interval '1 minute' * 30
and s2.hall_id = next_movies.m1_hid
order by idle_time desc;
