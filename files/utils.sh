#!/bin/bash
##################################################
# Install Java + Ant + Maven + Gradle + Tomcat   #
# Author by Dethroner, 2020                      #
##################################################

### Vars
JDK="http://ghaffarian.net/downloads/Java/jdk-8u202-linux-x64.tar.gz"
ANT="https://downloads.apache.org/ant/binaries/apache-ant-1.10.7-bin.tar.gz"
MAVEN="https://mirror.datacenter.by/pub/apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz"
GRADLE="https://services.gradle.org/distributions/gradle-4.4.1-bin.zip"
TOMCAT="https://mirror.datacenter.by/pub/apache.org/tomcat/tomcat-7/v7.0.103/bin/apache-tomcat-7.0.103.tar.gz"

##################################################
### PreInstall
apt update -y
apt install -y wget unzip

##################################################
### Download & Unpack
cd /tmp
wget $JDK
wget $ANT
wget $MAVEN
wget $GRADLE
wget $TOMCAT
unzip *.zip
tar xzf jdk*.tar.gz
tar xzf apache-ant-*.tar.gz
tar xzf apache-maven-*.tar.gz
tar xzf apache-tomcat-*.tar.gz
rm -rf *.tar.gz *.zip


################################################
# Install Java
mv jdk*/ /opt/jdk
ln -s /opt/jdk/bin/java /usr/bin/java
touch /etc/profile.d/java.sh
echo "#!/bin/bash
JAVA_HOME=/opt/jdk/
PATH=$JAVA_HOME/bin:$PATH
export PATH JAVA_HOME
export CLASSPATH=." >> /etc/profile.d/java.sh
chmod +x /etc/profile.d/java.sh
source /etc/profile.d/java.sh
# update-alternatives --install /usr/bin/java java /opt/jdk/bin/java 2
# update-alternatives --install /usr/bin/jar jar /opt/jdk/bin/jar 2
# update-alternatives --install /usr/bin/javac javac /opt/jdk/bin/javac 2
# update-alternatives --set jar /opt/jdk/bin/jar
# update-alternatives --set javac /opt/jdk/bin/javac
# echo "Java Version: "
# java -version
# echo "Setting JAVA_HOME Variable.."
# export JAVA_HOME=/opt/jdk
# echo "Settting JRE_HOME Variable..."
# export JRE_HOME=/opt/jdk/jre
# echo "Setting PATH Variable"..
# export PATH=$PATH:/opt/jdk/bin:/opt/jdk/jre/bin

################################################
# Install ant
mv apache-ant-*/ /opt/ant
ln -s /opt/ant/bin/ant /usr/bin/ant
touch /etc/profile.d/ant.sh
echo "#!/bin/bash
ANT_HOME=/opt/ant
PATH=$ANT_HOME/bin:$PATH
export PATH ANT_HOME
export CLASSPATH=." >> /etc/profile.d/ant.sh
chmod +x /etc/profile.d/ant.sh
source /etc/profile.d/ant.sh

################################################
# Install Maven
mv apache-maven-*/ /opt/maven
ln -s /opt/maven/bin/mvn /usr/bin/mvn
touch /etc/profile.d/maven.sh
echo "#!/bin/bash
MAVEN_HOME=/opt/maven
PATH=$MAVEN_HOME/bin:$PATH
export PATH MAVEN_HOME
export CLASSPATH=." >> /etc/profile.d/maven.sh
chmod +x /etc/profile.d/maven.sh
source /etc/profile.d/maven.sh
mkdir -p ~/.m2
touch ~/.m2/settings.xml
echo "<settings>
	<mirrors>
		<mirror>
			<id>mavenCentralHttps</id>
			<mirrorOf>central</mirrorOf>
			<name>Maven central https</name>
			<url>https://repo1.maven.org/maven2</url>
		</mirror>
	</mirrors>
</settings>" >> ~/.m2/settings.xml

################################################
# Install Gradle
mv gradle-*/ /opt/gradle
ln -s /opt/gradle/bin/gradle /usr/bin/gradle
touch /etc/profile.d/gradle.sh
echo "#!/bin/bash
GRADLE_HOME=/opt/gradle
PATH=$GRADLE_HOME/bin:$PATH
export PATH GRADLE_HOME
export CLASSPATH=." >> /etc/profile.d/gradle.sh
chmod +x /etc/profile.d/gradle.sh
source /etc/profile.d/gradle.sh

################################################
# Install Tomcat
mv apache-tomcat-*/ /opt/tomcat
touch /etc/profile.d/tomcat.sh
echo "#!/bin/bash
CATALINA_HOME=/opt/tomcat
PATH=$CATALINA_HOME/bin:$PATH
export PATH CATALINA_HOME
export CLASSPATH=." >> /etc/profile.d/tomcat.sh
chmod +x /etc/profile.d/tomcat.sh
source /etc/profile.d/tomcat.sh
chmod +x $CATALINA_HOME/bin/startup.sh
chmod +x $CATALINA_HOME/bin/shutdown.sh
chmod +x $CATALINA_HOME/bin/catalina.sh
sh $CATALINA_HOME/bin/startup.sh