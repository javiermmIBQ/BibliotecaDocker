FROM tomcat:9.0

COPY BibliotecaJPA /BibliotecaJPA

RUN cd /BibliotecaJPA &&\
cd build &&\
mv classes ../WebContent/WEB-INF &&\
cd .. &&\
cd src &&\
mv META-INF/* ../WebContent/WEB-INF/classes/ &&\
cd .. &&\
sed -i 's/.*javax.persistence.jdbc.url.*/<property name="javax.persistence.jdbc.url" value="jdbc:mysql:\/\/mysql_c:3306\/biblioteca"\/>/' WebContent/WEB-INF/classes/META-INF/persistence.xml &&\
cd WebContent &&\
jar -cvf /usr/local/tomcat/webapps/Biblioteca.war * &&\
rm -rf /BibliotecaJPA
