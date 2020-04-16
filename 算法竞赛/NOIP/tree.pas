var
  a : array[1..6,1..6] of byte;
  t : longint;
  h,z : array[1..6] of byte;

procedure sub(s,y,e : byte);
var
  x,i,j : byte;
begin
  if s > 24
    then begin
           inc(t);
           writeln('No . ',t);
           for i := 1 to 6 do begin
             for j := 1 to 6 do write(a[i,j]:4);
             writeln;
           end;
           readln;
         end
    else for x := e to 6 do
           if (a[y,x] = 0) and (h[y] < 4) and (z[x] < 4) then begin
             a[y,x] := 1; inc(h[y]); inc(z[x]);
             if s mod 4 = 0
               then sub(s+1,y+1,1)
               else sub(s+1,y,x+1);
             a[y,x] := 0; dec(h[y]); dec(z[x]);
           end;
end;

begin
  t := 0;
  fillchar(a,sizeof(a),0);
  fillchar(h,sizeof(h),0);
  z := h;
  sub(1,1,1);
  writeln('Total = ',t);
end.