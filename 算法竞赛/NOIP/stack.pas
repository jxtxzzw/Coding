var    n,sum:longint;
procedure dfs(top,head:longint); {topΪջ��ָ�룬headΪ�Ѿ��ж��ٸ�Ԫ�����ջ}
var
    i,t:longint;
 begin
   if  head=n then  begin inc(sum); exit; end;
   if top>0 then begin writeln(top-1,head);dfs(top-1,head);end;
   if head<n+1 then begin writeln(top+1,head+1);dfs(top+1,head+1);end;
 end;
 begin
    assign(output,'stack.in');reset(input);   
    assign(output,'stack.out');rewrite(output);
   readln(n);
   sum:=0;
   dfs(0,0);
   writeln(sum);
   close(input);close(output);
 end.
