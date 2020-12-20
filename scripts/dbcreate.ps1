$server = '192.168.17.154'
$user   = 'root'
$pass   = 'profesor'

$cs = "server=$server;user id=$user;password=$pass;pooling=false"

[void][Reflection.Assembly]::LoadWithPartialName('MySQL.Data')

$cn = New-Object MySql.Data.MySqlClient.MySqlConnection
$cn.ConnectionString = $cs
$cn.Open()

$cmd= New-Object MySql.Data.MySqlClient.MySqlCommand
$cmd.Connection  = $cn
$cmd.CommandText = 'create database biblioteca;create user bibliotecario identified by `"bibliotecario`";
grant all privileges on biblioteca.* to `"bibliotecario`";
use biblioteca;
create table libros (id INT(6) primary key, titulo varchar(30) not null, autor varchar(30) not null);'
$reader = $cmd.ExecuteReader()

$tbl = New-Object Data.DataTable
$tbl.Load($reader)
$reader.Close()
$cn.Close()

$tbl | Format-Table -AutoSize


