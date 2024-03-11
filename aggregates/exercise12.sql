create table cd.bookings(
    bookid int,
    facid int,
    memid int,
    starttime timestamp,
    slots int,
    primary key(bookid),
    foreign key(facid) references cd.facilities(facid),
    foreign key(memid) references cd.members(memid)
)

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

select facid, extract(month from starttime) as month, sum(slots) as slots
from cd.bookings
where starttime between '2012-01-01' and '2013-01-01'
group by rollup(facid, month)
order by facid, month; 