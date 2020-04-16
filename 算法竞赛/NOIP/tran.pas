var
  a             : array[1..50,1..3] of word;
  c             : array[0..10] of word;
  m,n,k,i,j,s,t : word;

procedure solve(x,y : word);
var i,j : word; ok  : boolean;
begin
  if y > t then exit;
  if x > k
    then begin
           if y < t then t := y;
         end
    else begin
           for i := 1 downto 0 do
             if i = 1
               then begin
                      ok := true;
                      for j := a[x,1] to a[x,2]-1 do begin
                        c[j] := c[j]+a[x,3];
                        if c[j] > n then ok := false;
                      end;
                      if ok then solve(x+1,y);
                      for j := a[x,1] to a[x,2]-1 do c[j] := c[j]-a[x,3];
                    end
               else solve(x+1,y+a[x,3]*(a[x,2]-a[x,1]));
         end;
end;

begin
  assign(input,'tran.in'); reset(input);
  assign(output,'tran.out'); rewrite(output);
  readln(n,m,k);
  fillchar(a,sizeof(a),0);
  fillchar(c,sizeof(c),0);
  s := 0;
  for i := 1 to k do begin
    readln(a[i,1],a[i,2],a[i,3]);
    s := s+a[i,3]*(a[i,2]-a[i,1]);
  end;
  t := maxint;
  solve(1,0);
  writeln(s-t);
  close(input); close(output);
end.