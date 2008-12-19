#!/bin/sh
cd /home/keith/work/eclipse/mifos-cheetah
mvn -o clean package
rm -r /home/keith/dist/tomcat6/webapps/mifos
cd /home/keith/work/eclipse/mifos-cheetah/webapp/target
cp mifos.war /home/keith/dist/tomcat6/webapps
