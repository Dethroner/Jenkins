#!/bin/bash
##################################################
# Install Oracle Java +Ant                       #
# Author by Dethroner, 2020                      #
##################################################

apt update -y

echo "Installing wget.."
apt install wget -y

echo "Installing Java 8.."
cd /opt/
wget https://mirrors.huaweicloud.com/java/jdk/7u80-b15/jdk-7u80-linux-x64.tar.gz
tar xzf jdk-7u80-linux-x64.tar.gz
cd /opt/jdk1.7.0_80/
update-alternatives --install /usr/bin/java java /opt/jdk1.7.0_80/bin/java 2
update-alternatives --install /usr/bin/jar jar /opt/jdk1.7.0_80/bin/jar 2
update-alternatives --install /usr/bin/javac javac /opt/jdk1.7.0_80/bin/javac 2
update-alternatives --set jar /opt/jdk1.7.0_80/bin/jar
update-alternatives --set javac /opt/jdk1.7.0_80/bin/javac
echo "Java Version: "
java -version
echo "Setting JAVA_HOME Variable.."
export JAVA_HOME=/opt/jdk1.7.0_80
echo "Settting JRE_HOME Variable..."
export JRE_HOME=/opt/jdk1.7.0_80/jre
echo "Setting PATH Variable"..
export PATH=$PATH:/opt/jdk1.7.0_80/bin:/opt/jdk1.7.0_80/jre/bin

echo "Installing ant"
apt install -y snapd
snap install ant --channel=1.9/stable --classic