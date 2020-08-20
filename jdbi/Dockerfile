FROM docker-registry.cwi.com.br:5000/marketplace/java:11
RUN apt-get update && apt-get install -y wget
RUN wget http://download-keycdn.ej-technologies.com/jprofiler/jprofiler_linux_11_1_2.tar.gz -P /tmp/ && tar -xzf /tmp/jprofiler_linux_11_1_2.tar.gz -C /usr/local &&  rm /tmp/jprofiler_linux_11_1_2.tar.gz
COPY target/poc-jdbi*.jar poc-jdbi.jar
CMD java -agentpath:/usr/local/jprofiler11.1.2/bin/linux-x64/libjprofilerti.so=port=8092,nowait -jar poc-jdbi.jar
EXPOSE 8082 8092