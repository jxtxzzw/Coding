var st:string;
    g:array['A'..'Z'] of boolean;
    a:array[0..26] of byte;
    i,len,n:byte;
    c:char;
    t:longint;

procedure sub(s:byte);
var i,j:byte;
begin
  if s>n
    then begin
           t:=t+1;
           for j:=1 to n do write(st[a[j]]);
           write('  ');
           if t mod 8=0 then writeln;
         end
    else for i:=a[s-1]+1 to len do begin
                                  a[s] := i;
                                  sub(s+1);
                                 end;
end;

begin
  write('Input a set of letters:'); readln(st);
  write('n='); readln(n);
  fillchar(g,sizeof(g),false);
  for i := 1 to length(st) do g[upcase(st[i])]:=true;
  st:='';
  for c:='A' to 'Z' do if g[c] then st:=st+c;
  len:=length(st);
  t:= 0;
  sub(1);
  writeln('Total=',t);
  readln;
end.
