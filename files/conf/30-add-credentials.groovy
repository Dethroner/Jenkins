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
MIIG5AIBAAKCAYEAn4Y4NB8uq4pWYYjXTdT9JMVKXb4vpXJBFAS1EMR7RLrZZ/Oy
YeyuELUTqWqGSVilCIay1kK4kX1JYb1x4cgejbDqmGYNsOkxbaqfDX+GJTD/TiXK
GwbUPNTwteLiEu1b5lilVCNrUqOYDHfP5ni+f90+zehk1UXwW4CJUF4nGNAmMP3M
4c5RWIs0bywOuFdx18qzlqvUl9gGojJEnaBH9GT9wWf9YTGsMzdfu2y5Vh0lm2x8
pYrhYzVf/hPU2AH5gbx/sHfziwhUlNuHhSFhxFW5ZPRE52iQbhYoAiFetUyci1ak
1tJYQDYhYzcE0j3N4ecAYWo7IzaxqUc41EafdSYHH/JU/4sDn+EPlbFxScpp4jWH
XsIvQOXYilQr/ojPaNZSHBnOzdq7r/TCvx09Aq+h20/fD4aeF7I+zIIlGwGqyF6V
0Sj4mSkiclGtB/GswJAmz2bSGw8NFcWBCG5l6+pEW7NSmazgUPJdf2q/MxeOqHuH
23ODy9TWsq+50o/5AgMBAAECggGAOysZZZu7P70L1RlgYmoBisXyk1qbaY47PSWR
0ypNXmy/fqMRIukalu/S3x+3tzS94a+0XWxZ8Py7XxlZSBll28AnA2e25EtaHgcO
/36xTtIhn+a8U9Hwnq+MqKoCmGqZj5KJn++TOw+aseUuo5ldbyesx8rOu5w5Tnca
d0i0z6tEvx8kAwz3dxQK2sFIpG8qtpNkkRoivD9QFpD2ilCM38UlqbrcbFR0Pnrn
cykchShCbqeKhAxDFP0TIbZRV91DDEXxsydRbka1bmwPj3TWNt8O50yo2HZVt5af
mumFetEeiqItetIR03G05QBZ7ecSm2ZKh0KfBDNPWv7i7k9RpldzrA==
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
