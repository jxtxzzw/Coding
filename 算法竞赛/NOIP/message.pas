VAR
n,i,ans,zz,tmp:longint;
t,min,rudu:array[0..200000] of longint;
visited,ava:array[0..200000] of boolean;
changed:boolean;
 
BEGIN
assign(input,'message.in');reset(input);
assign(output,'message.out');rewrite(output);
readln(n);
if n<=2500 then begin
for i:=1 to n do read(t[i]);
ans:=maxlongint;
for i:=1 to n do begin
fillchar(visited,sizeof(visited),false);
zz:=i;
tmp:=0;
visited[zz]:=true;
repeat
   visited[t[zz]]:=true;
   inc(tmp);
   if tmp>ans then break;
   zz:=t[zz];
until visited[t[zz]];
 if t[zz]=i then min[i]:=tmp else min[i]:=-1;
  if (min[i]<ans) and (min[i]<>-1) then ans:=min[i];
end;
writeln(ans+1);
end
else
begin
for i:=1 to n do begin
  read(t[i]);
  inc(rudu[t[i]]);
end;
for i:=1 to n do ava[i]:=true;
repeat
changed:=false;
for i:=1 to n do if (ava[i]) then begin
if rudu[i]=0 then begin
ava[i]:=false;
dec(rudu[t[i]]);
changed:=true;
end;
end;
until not changed;
ans:=maxlongint;
for i:=1 to n do begin
if ava[i] then begin
zz:=i;
ava[zz]:=false;
tmp:=0;
repeat
 zz:=t[zz];
 ava[zz]:=false;
 inc(tmp);
until zz=i;
if tmp<ans then ans:=tmp;
end;
end;
writeln(ans);
end;
close(input);close(output);
END.