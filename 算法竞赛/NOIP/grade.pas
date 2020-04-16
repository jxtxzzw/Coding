const
  a : array[1..5,1..10] of byte = ((0,0,0,0,0,0,0,0,0,0),
                                   (0,0,1,0,1,0,0,1,0,0),
                                   (0,1,1,1,0,1,0,1,1,1),
                                   (0,1,1,1,0,0,0,1,0,1),
                                   (0,0,1,1,1,0,0,1,1,1));
var
  b : array[2..5] of byte;

procedure check;
var j,k : byte;
begin
  fillchar(b,sizeof(b),0);
  for j := 2 to 5 do
    for k := 1 to 10 do
      if a[j,k] = a[1,k] then inc(b[j]);
  if (b[2] <> 7) or (b[3] <> 5) or (b[4] <> 3) then exit;
  writeln(b[5]*10); readln; halt
end;

procedure sub(s : byte);
var i : byte;
begin
  if s > 10
    then check
    else for i := 0 to 1 do begin
           a[1,s] := i;
           sub(s+1);
         end;
end;

begin
  sub(1);
end.