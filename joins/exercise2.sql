create table cd.facilities(
    facid int,
    name varchar(100),
    membercost numeric,
    guestcost numeric,
    initialoutlay numeric,
    monthlymaintance numeric,
    primary key(facid)
);

create table cd.bookings(
    bookid int,
    facid int,
    memid int,
    starttime timestamp,
    slots int,
    primary key(bookid),
    foreign key(facid) references cd.facilities(facid),
    foreign key(memid) references cd.members(memid)
);

insert into cd.facilities(facid, name, membercost, guestcost, initialoutlay, monthlymaintance) values 
(0, 'Tennis Court 1', 5, 25, 10000, 200),
(1, 'Tennis Court 2', 5, 25, 8000, 200),
(2, 'Badminton Court', 0, 15.5, 4000, 50),
(3, 'Table Tennis', 0, 5, 320, 10),
(4, 'Massage Room 1', 35, 80, 4000, 3000),
(5, 'Massage Room 2', 35, 80, 4000, 3000),
(6, 'Squash Court', 3.5, 17.5, 5000, 80),
(7, 'Snooker Table', 0, 5, 450, 15),
(8, 'Pool Table', 0, 5, 400, 15);

insert into cd.bookings(bookid, facid, memid, starttime, slots) values
(0, 3, 1, '2012-07-03 11:00:00', 2),
(1, 4, 1, '2012-07-03 08:00:00', 2),
(2, 6, 0, '2012-07-03 18:00:00', 2),
(3, 7, 1, '2012-07-03 19:00:00', 2),
(4, 8, 1, '2012-07-03 10:00:00', 1),
(5, 8, 1, '2012-07-03 15:00:00', 1),
(6, 0, 2, '2012-07-04 09:00:00', 3),
(7, 0, 2, '2012-07-04 15:00:00', 3),
(8, 4, 3, '2012-07-04 13:30:00', 2),
(9, 4, 0, '2012-07-04 15:00:00', 2),
(10, 4, 0, '2012-07-04 17:30:00', 2),
(11, 6, 0, '2012-07-04 12:30:00', 2),
(12, 6, 0, '2012-07-04 14:00:00', 2),
(13, 6, 1, '2012-07-04 15:30:00', 2),
(14, 7, 2, '2012-07-04 14:00:00', 2),
(15, 8, 2, '2012-07-04 12:00:00', 1),
(16, 8, 3, '2012-07-04 18:00:00', 1),
(17, 1, 0, '2012-07-05 17:30:00', 3),
(18, 2, 1, '2012-07-05 09:30:00', 3),
(19, 3, 3, '2012-07-05 09:00:00', 2),
(20, 3, 1, '2012-07-05 19:00:00', 2);

select b.starttime, f.name from cd.bookings b
join cd.facilities f on b.facid = f.facid
where f.name like 'Tennis Court _'
    and b.starttime between '2012-09-21 00:00:00' and '2012-09-21 23:59:59'
order by b.starttime;