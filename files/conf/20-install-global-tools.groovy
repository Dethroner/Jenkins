import jenkins.model.*
import hudson.model.*
import hudson.tasks.*
import hudson.tools.*

println("--- Configuring jdk")
def descriptor = new JDK.DescriptorImpl();
def List<JDK> installations = []
javaTools=[['name':'jdk8', 'url':'http://ghaffarian.net/downloads/Java/jdk-8u202-linux-x64.tar.gz', 'subdir':'jdk8'],
      // ['name':'jdk7', 'url':'file:https://mirrors.huaweicloud.com/java/jdk/7u80-b15/jdk-7u80-linux-x64.tar.gz', 'subdir':'jdk7'],
      ['name':'jdk11', 'url':'https://download.java.net/java/GA/jdk11/13/GPL/openjdk-11.0.1_linux-x64_bin.tar.gz', 'subdir':'jdk11']]
javaTools.each { javaTool ->
    println("Setting up tool: ${javaTool.name}")
    def installer = new ZipExtractionInstaller(javaTool.label as String, javaTool.url as String, javaTool.subdir as String);
    def jdk = new JDK(javaTool.name as String, "/var/lib/jenkins/tools/hudson.model.JDK/"+javaTool.subdir as String, [new InstallSourceProperty([installer])])
    installations.add(jdk)
}
descriptor.setInstallations(installations.toArray(new JDK[installations.size()]))
descriptor.save()

println("--- Configuring Maven")
mavenName = "maven3"
mavenVersion = "3.6.0"
println("Checking Maven installations...")
mavenPlugin = Jenkins.instance.getExtensionList(hudson.tasks.Maven.DescriptorImpl.class)[0]
maven3Install = mavenPlugin.installations.find {
  install -> install.name.equals(mavenName)
}
if(maven3Install == null) {
  println("No Maven install found. Adding...")
  newMavenInstall = new hudson.tasks.Maven.MavenInstallation('maven3', null,
   [new hudson.tools.InstallSourceProperty([new hudson.tasks.Maven.MavenInstaller(mavenVersion)])]
)

  mavenPlugin.installations += newMavenInstall
  mavenPlugin.save()
}

