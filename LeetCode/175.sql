select P.FirstName, P.LastName, A.City, A.State
from Person P left join Address A # left outer join 保留左表所有记录
on P.personId = A.personId; # on condition
