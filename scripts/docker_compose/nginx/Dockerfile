FROM nginx

COPY WebContent/css /css

COPY scripts/docker_compose/nginx/tomcat-basic.conf /etc/nginx/conf.d

COPY scripts/docker_compose/nginx/misitio.crt /etc/ssl/certs/
COPY scripts/docker_compose/nginx/misitio.key /etc/ssl/private/
