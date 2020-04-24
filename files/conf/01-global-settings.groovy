import jenkins.model.*
import org.jenkinsci.plugins.*
import hudson.security.csrf.DefaultCrumbIssuer
import hudson.plugins.locale.PluginImpl

def instance = Jenkins.getInstance()

println("--- Configuring global getting")
instance.setNumExecutors(5)
instance.setCrumbIssuer(new DefaultCrumbIssuer(true))
instance.setNoUsageStatistics(true)
instance.save()

println("--- Configuring locale")
PluginImpl localePlugin = (PluginImpl)instance.getPlugin("locale")
localePlugin.systemLocale = "en_US"
localePlugin.@ignoreAcceptLanguage=true
