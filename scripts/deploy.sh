#Paramos y eliminamos contenedores de despliegues anteriores
docker stop mysql_c tomcat_c nginx_c

# Trabajamos en un fichero temporal
cd /tmp

# Descargamos el código de la aplicación de GitHub
git clone https://github.com/javiermmIBQ/BibliotecaJPA.git

# Movemos la carpeta css al entorno nginx
cp -r BibliotecaJPA/WebContent/css/ BibliotecaJPA/scripts/docker_biblioteca/nginx/

# Movemos las carpetas necesarias al entorno tomcat
mkdir BibliotecaJPA/scripts/docker_biblioteca/tomcat/BibliotecaJPA
cp -r BibliotecaJPA/build/ BibliotecaJPA/scripts/docker_biblioteca/tomcat/BibliotecaJPA/
cp -r BibliotecaJPA/src/ BibliotecaJPA/scripts/docker_biblioteca/tomcat/BibliotecaJPA/
cp -r BibliotecaJPA/WebContent/ BibliotecaJPA/scripts/docker_biblioteca/tomcat/BibliotecaJPA/
rm -rf BibliotecaJPA/scripts/docker_biblioteca/tomcat/BibliotecaJPA/WebContent/css

#Creamos red
docker network create network_biblioteca

#Construimos imagenes
docker build -t mysql_img /tmp/BibliotecaJPA/scripts/docker_biblioteca/mysql
docker build -t tomcat_img /tmp/BibliotecaJPA/scripts/docker_biblioteca/tomcat
docker build -t nginx_img /tmp/BibliotecaJPA/scripts/docker_biblioteca/nginx

#Creamos contenedores
docker run -d --rm --name mysql_c --network network_biblioteca -e MYSQL_ROOT_PASSWORD=profesor mysql_img
docker run -d --rm --name tomcat_c --network network_biblioteca tomcat_img
docker run -d --rm --name nginx_c --network network_biblioteca -p 80:80 -p 443:443 nginx_img

#Borramos carpeta temporal
rm -rf /tmp/BibliotecaJPA
