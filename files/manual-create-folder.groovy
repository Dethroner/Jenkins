import com.cloudbees.hudson.plugins.folder.*
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import jenkins.model.Jenkins

Jenkins jenkins = Jenkins.instance

def fn = [
    "MTS",
    "abrakadbra"
]

fn.each { folderName->

    String folderNameTemp = folderName + "-folder"

    def folder = jenkins.getItem(folderName)
    if (folder == null) {
    folder = jenkins.createProject(Folder.class, folderName)
    } else {
        if (folder.getClass() != Folder.class) {
        folder = jenkins.createProject(Folder.class, folderNameTemp)
        Item[] items = jenkins.getItems(WorkflowJob.class)

        def job_regex = "^" + folderName
        items.grep { it.name =~ job_regex }.each { job ->
            Items.move(job, folder)
            }

        folder.renameTo(folderName)
        }
    }
}