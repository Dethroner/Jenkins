import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.plugins.credentials.impl.*
import hudson.util.Secret
import java.nio.file.Files
import jenkins.model.Jenkins
import net.sf.json.JSONObject
import org.jenkinsci.plugins.plaincredentials.impl.*

// parameters
def jenkinsMasterKeyParameters = [
  description:  'git-master-key',
  id:           'gitlab',
  secret:       '',
  userName:     'appuser',
  key:          new BasicSSHUserPrivateKey.DirectEntryPrivateKeySource('''-----BEGIN RSA PRIVATE KEY-----
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
-----END RSA PRIVATE KEY-----
''')
]

// get Jenkins instance
Jenkins jenkins = Jenkins.getInstance()

// get credentials domain
def domain = Domain.global()

// get credentials store
def store = jenkins.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

// define private key
def privateKey = new BasicSSHUserPrivateKey(
  CredentialsScope.GLOBAL,
  jenkinsMasterKeyParameters.id,
  jenkinsMasterKeyParameters.userName,
  jenkinsMasterKeyParameters.key,
  jenkinsMasterKeyParameters.secret,
  jenkinsMasterKeyParameters.description
)

// add credential to store
store.addCredentials(domain, privateKey)

// save to disk
jenkins.save()
