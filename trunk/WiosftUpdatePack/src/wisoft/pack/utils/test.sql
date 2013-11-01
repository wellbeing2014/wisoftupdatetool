CREATE OR REPLACE FUNCTION "FORMATDATE" (dt date)
return string
as
begin
return to_char(dt,'MM-DD');
end;