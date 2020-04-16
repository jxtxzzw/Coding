var
  g         : array[0..7,0..7] of integer;
  p         : array[0..36,1..4] of integer;
  t         : array[0..36] of boolean;
  n,m,i,j,k : integer;
  s         : string[4];

function can(x,y,i : integer) : boolean;
begin
  can := false;
  if p[i,1]+p[g[x-1,y],3] <> 0 then exit;
  if p[i,4]+p[g[x,y-1],2] <> 0 then exit;
  if (y = m) and (p[i,2] <> 0) then exit;
  if (x = n) and (p[i,3] <> 0) then exit;
  can := true;
end;

procedure sub(x,y : integer);
var i : integer;
begin
  if x > n
    then begin
           writeln('YES'); close(input); close(output); halt;
         end
    else for i := 1 to n*m do
           if (not t[i]) and (can(x,y,i)) then begin
             t[i] := true;
             g[x,y] := i;
             if y <> m then sub(x,y+1)
                       else sub(x+1,1);
             t[i] := false;
           end;
end;

begin
  assign(input,'puzzle.in'); reset(input);
  assign(output,'puzzle.out'); rewrite(output);
  readln(n,m);
  fillchar(p[0],sizeof(p[0]),0);
  for i := 1 to n*m do begin
    readln(s);
    for j := 1 to 4 do begin
      if s[j] = 'F' then p[i,j] := 0;
      if s[j] = 'O' then p[i,j] := 1;
      if s[j] = 'I' then p[i,j] := -1;
    end;
  end;
  fillchar(g,sizeof(g),0);
  fillchar(t,sizeof(t),false);
  t[0] := true;
  sub(1,1);
  writeln('NO');
  close(input); close(output);
end.
