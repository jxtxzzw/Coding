PROGRAM hanno;
VAR
n:integer;
PROCEDURE move(n,a,b,c:integer);
BEGIN
if n=1 then writeln(a,'--->',c)
       else BEGIN
               move(n-1,a,c,b);
               writeln(a,'--->',c);
               move(n-1,b,a,c);
            END;
END;
BEGIN
write('Enter n=');
read(n);
move(n,1,2,3);
END.