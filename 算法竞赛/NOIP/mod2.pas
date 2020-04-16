var  a,b,n,m,t:longint;
procedure e(a,b:longint;var x,y:longint);
begin
  if b=0 then begin
                x:=1;
                y:=0;
                exit;
              end
         else begin
                e(b,a mod b,x,y);
                t:=x;
                x:=y;
                y:=t-(a div b)*y;
              end;
end;
begin
  assign(input,'mod.in');reset(input);
  assign(output,'mod.out');rewrite(output);
  read(a,b);
  e(a,b,n,m);
  if n<0 then n:=n+b;
  writeln(n);
  close(input);close(output);
end.
